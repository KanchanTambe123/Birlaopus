package commonutilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    public WebDriver driver;

    // ===================== DRIVER & DEVTOOLS =====================
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<DevTools> tlDevTools = new ThreadLocal<>();

    // ===================== STATIC DATA =====================
    private static volatile String leadRequestPayload;
    private static volatile Integer leadStatusCode; // ✅ NEW

    private static final ConfigReader config = new ConfigReader();

    // ===================== DRIVER GET/SET =====================
    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // ===================== INIT DRIVER =====================
    public void init_driver(String browser) {

        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        // Headless config
        opt.addArguments(config.getProb("runHeadless").replace("--headless", "--headless=new"));
        opt.addArguments("--" + config.getProb("headlessBrowserSize"));
        opt.addArguments("--no-sandbox");
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--incognito");
        opt.addArguments("--disable-popup-blocking");
        opt.addArguments("--disable-geolocation");
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-infobars");
        opt.addArguments("--disable-dev-shm-usage");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.media_stream", 2);
        opt.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(opt);
        setDriver(driver);
        getDriver().manage().deleteAllCookies();

        initDevTools();
    }

    // ===================== DEVTOOLS =====================
    public static void initDevTools() {
        ChromeDriver chromeDriver = (ChromeDriver) getDriver();
        DevTools devTools = chromeDriver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        // ===================== REQUEST CAPTURE =====================
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();

            if (url.contains("/lead/leadForm")) {
                System.out.println("Lead API matched (Request)");

                request.getRequest().getPostData().ifPresent(payload -> {
                    System.out.println(" Payload captured");
                    leadRequestPayload = payload;
                });
            }
        });

        // ===================== RESPONSE CAPTURE (NEW) =====================
        devTools.addListener(Network.responseReceived(), response -> {
            String url = response.getResponse().getUrl();

            if (url.contains("/lead/leadForm")) {
                System.out.println("Lead API matched (Response)");

                leadStatusCode = response.getResponse().getStatus().intValue();

                System.out.println(" Status Code: " + leadStatusCode);
            }
        });

        tlDevTools.set(devTools);
    }

    // ===================== GETTERS =====================
    public static String getLeadRequestPayload() {
        return leadRequestPayload;
    }

    public static Integer getLeadStatusCode() {
        return leadStatusCode;
    }

    // ===================== WAIT METHODS =====================
    public static String waitForLeadPayload(int timeoutSeconds) throws InterruptedException {
        int attempts = 0;
        while (leadRequestPayload == null && attempts < timeoutSeconds * 2) {
            Thread.sleep(500);
            attempts++;
        }
        return leadRequestPayload;
    }

    public static int waitForLeadStatusCode(int timeoutSeconds) throws InterruptedException {
        int attempts = 0;
        while (leadStatusCode == null && attempts < timeoutSeconds * 2) {
            Thread.sleep(500);
            attempts++;
        }
        return leadStatusCode != null ? leadStatusCode : -1;
    }

    // ===================== QUIT DRIVER =====================
    public static synchronized void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
            tlDevTools.remove();
            leadRequestPayload = null;
            leadStatusCode = null; // ✅ reset
        }
    }
}