package commonutilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    public WebDriver driver;

    // ===================== DRIVER & DEVTOOLS =====================
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<DevTools> tlDevTools = new ThreadLocal<>();

    // ===================== STATIC DATA =====================
    private static volatile String leadRequestPayload;
    private static volatile Integer leadStatusCode; //
 // ✅ ADD BELOW
    private static volatile long requestStartTime;
    private static volatile long responseEndTime;
    private static volatile long responseTime;
    
    private static final String SHORT_FORM_API = "/lead/shortForm";
    private static final String LEAD_FORM_API = "/lead/leadForm";

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
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
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

            if (url.contains(SHORT_FORM_API) || url.contains(LEAD_FORM_API)) {

                System.out.println(" Lead API matched (Request): " + url);

                // Start Time
                requestStartTime = System.currentTimeMillis();

                request.getRequest().getPostData().ifPresent(payload -> {
                    leadRequestPayload = payload;
                    System.out.println(" Request Payload Captured");
                });
            }
        });
        // ===================== RESPONSE CAPTURE (NEW) =====================
        devTools.addListener(Network.responseReceived(), response -> {
            String url = response.getResponse().getUrl();

            if (url.contains(SHORT_FORM_API) || url.contains(LEAD_FORM_API)) {

                System.out.println(" Lead API matched (Response): " + url);

                //  Status Code
                leadStatusCode = response.getResponse().getStatus().intValue();

                // End Time
                responseEndTime = System.currentTimeMillis();

                //  Calculate Response Time
                responseTime = responseEndTime - requestStartTime;

                System.out.println(" Status Code: " + leadStatusCode);
                System.out.println(" Response Time: " + responseTime + " ms");
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

            //  Reset all values
            leadRequestPayload = null;
            leadStatusCode = null;
            requestStartTime = 0;
            responseEndTime = 0;
            responseTime = 0;
        }
    }
    public static void resetApiData() {
        leadRequestPayload = null;
        leadStatusCode = null;
        requestStartTime = 0;
        responseEndTime = 0;
        responseTime = 0;
    }
    public static long getResponseTime() {
        return responseTime;
    }
}