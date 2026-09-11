package commonutilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v146.network.Network;
import org.openqa.selenium.devtools.v146.network.model.RequestId;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * DriverManager - Manages WebDriver instances with DevTools support
 * Provides thread-safe WebDriver and DevTools access
 * Captures API request/response data including payload, status code, response time
 */
public class DriverManager {

    // ThreadLocal to handle WebDriver instances in a thread-safe manner
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<DevTools> tlDevTools = new ThreadLocal<>();

    // ===================== API MONITORING DATA =====================
    private static volatile String leadRequestPayload;
    private static volatile String leadResponsePayload;
    private static volatile Integer leadStatusCode;
    private static volatile long requestStartTime;
    private static volatile long responseEndTime;
    private static volatile long responseTime;
    private static volatile RequestId leadRequestId;
    private static volatile String capturedApiUrl;  // Track actual API URL for debugging
    
    private static final String SHORT_FORM_API = "/lead/shortForm";
    private static final String LEAD_FORM_API = "/lead/leadForm";

    // Path to project directory
    private static final String project_directory_path = System.getProperty("user.dir");

    // ConfigReader to read the configuration properties
    private static final ConfigReader config = new ConfigReader();

    /**
     * Set WebDriver instance in ThreadLocal
     * @param driver WebDriver instance
     */
    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    /**
     * Get WebDriver instance from ThreadLocal
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Set DevTools instance in ThreadLocal
     * @param devTools DevTools instance
     */
    public static void setDevTools(DevTools devTools) {
        tlDevTools.set(devTools);
    }

    /**
     * Get DevTools instance from ThreadLocal
     * @return DevTools instance
     */
    public static DevTools getDevTools() {
        return tlDevTools.get();
    }

    /**
     * Initialize the WebDriver based on the browser type
     * Also initializes DevTools for Chrome with API monitoring
     * @param browser The browser type (e.g., "chrome")
     */
    public void init_driver(String browser) {

        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException(" Unsupported browser: " + browser);
        }

        WebDriver driver = null;

        // Setup Chrome driver using WebDriverManager
        WebDriverManager.chromedriver().clearDriverCache().setup();

        //System.out.println("Launching Chrome browser...");

        // Set Chrome Options
        ChromeOptions opt = new ChromeOptions();

        // Headless configuration
       opt.addArguments(config.getProb("runHeadless"));			
	    opt.addArguments(config.getProb("headlessBrowserSize"));
 
        // Performance and stability options
        opt.addArguments("--no-sandbox");
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--incognito");
        opt.addArguments("--disable-popup-blocking");
        opt.addArguments("--disable-geolocation");
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-infobars");
        opt.addArguments("--disable-dev-shm-usage");

        // Preferences for blocking popups and notifications
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // Block location
        prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
        prefs.put("profile.default_content_setting_values.media_stream", 2); // Block media stream
        opt.setExperimentalOption("prefs", prefs);

        // Initialize Chrome driver with options
        driver = new ChromeDriver(opt);
        setDriver(driver);

        // Configure browser
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));

        // ✅ Initialize DevTools with API monitoring
        initDevTools();

       // System.out.println("✅ Chrome browser initialized successfully");
    }

    /**
     * Initialize DevTools with network monitoring for API capture
     * Captures request payload, response payload, status code, and response time
     */
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

       // System.out.println("✅ DevTools Network monitoring enabled");

        // ===================== REQUEST CAPTURE =====================
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();

            if (url.contains(SHORT_FORM_API) || url.contains(LEAD_FORM_API)) {

                //System.out.println(" Lead API matched (Request): " + url);
                capturedApiUrl = url;  // Store for debugging

                // Capture request ID for response tracking
                leadRequestId = request.getRequestId();
                //System.out.println(" Request ID stored: " + leadRequestId);

                // Start Time
                requestStartTime = System.currentTimeMillis();

                // Capture request payload
                request.getRequest().getPostData().ifPresent(payload -> {
                    leadRequestPayload = payload;
                    //System.out.println(" Request Payload Captured (" + payload.length() + " characters)");
                });
                
                if (!request.getRequest().getPostData().isPresent()) {
                   // System.out.println(" No POST data in request (might be GET request)");
                }
            }
        });

        // ===================== RESPONSE CAPTURE =====================
        devTools.addListener(Network.responseReceived(), response -> {
            String url = response.getResponse().getUrl();

            if (url.contains(SHORT_FORM_API) || url.contains(LEAD_FORM_API)) {

               // System.out.println(" Lead API matched (Response): " + url);
                
                RequestId currentRequestId = response.getRequestId();
                
                // Update request ID in case it wasn't set
                if (leadRequestId == null) {
                    leadRequestId = currentRequestId;
                   // System.out.println(" Request ID set from response: " + leadRequestId);
                }

                // Capture Status Code
                leadStatusCode = response.getResponse().getStatus().intValue();

                // End Time
                responseEndTime = System.currentTimeMillis();

                // Calculate Response Time
                responseTime = responseEndTime - requestStartTime;

               // System.out.println(" Status Code: " + leadStatusCode);
               // System.out.println(" Response Time: " + responseTime + " ms");
                
                // ⭐ TRY TO CAPTURE RESPONSE BODY IMMEDIATELY (Strategy 1)
                //System.out.println(" Attempting immediate response capture...");
                try {
                    String responseBody = devTools.send(Network.getResponseBody(currentRequestId)).getBody();
                    if (responseBody != null && !responseBody.isEmpty()) {
                        leadResponsePayload = responseBody;
                       // System.out.println(" Response Payload Captured IMMEDIATELY (" + responseBody.length() + " characters)");
                    } else {
                       // System.out.println(" Response body is empty (immediate capture)");
                    }
                } catch (Exception e) {
                    //System.out.println(" Immediate capture failed: " + e.getMessage());
                    //System.out.println(" Will wait for loadingFinished event...");
                }
            }
        });

        // ===================== RESPONSE BODY CAPTURE (Fallback) =====================
        devTools.addListener(Network.loadingFinished(), loading -> {
            RequestId requestId = loading.getRequestId();
            
            //System.out.println(" loadingFinished fired for RequestId: " + requestId);

            // Only try if we haven't captured response yet
            if (leadResponsePayload == null || leadResponsePayload.startsWith("Unable to capture")) {
                // Check if this is the lead API request we're tracking
                if (requestId != null && leadRequestId != null && requestId.equals(leadRequestId)) {
                    //System.out.println(" RequestId matched! Capturing response body...");
                    
                    try {
                        // Capture response body
                        String responseBody = devTools.send(Network.getResponseBody(requestId)).getBody();
                        if (responseBody != null && !responseBody.isEmpty()) {
                            leadResponsePayload = responseBody;
                            //System.out.println(" Response Payload Captured via loadingFinished (" + responseBody.length() + " characters)");
                        } else {
                            leadResponsePayload = "Empty response body (204 No Content or similar)";
                            //System.out.println(" Response body is empty - this may be expected for this API");
                        }
                    } catch (Exception e) {
                        leadResponsePayload = "Unable to capture response body: " + e.getMessage();
                        //System.err.println(" Error capturing response body: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else if (requestId != null && leadRequestId != null) {
                    //System.out.println(" RequestId mismatch - Expected: " + leadRequestId + ", Got: " + requestId);
                } else {
                    //System.out.println(" Skipping - leadResponsePayload: " + (leadResponsePayload != null ? "already captured" : "null"));
                }
            } else {
                //System.out.println(" Response already captured, skipping loadingFinished");
            }
        });

        setDevTools(devTools);
       // System.out.println(" DevTools initialized successfully with API monitoring");
    }

    /**
     * Quit the WebDriver and clean up ThreadLocal
     */
    public static synchronized void quitDriver() {

        WebDriver driver = getDriver();

        // Check if WebDriver instance exists
        if (driver != null) {
            try {
                // Close DevTools first
                DevTools devTools = getDevTools();
                if (devTools != null) {
                    try {
                        devTools.send(Network.disable());
                        //System.out.println("DevTools disabled");
                    } catch (Exception e) {
                        //System.err.println(" Error closing DevTools: " + e.getMessage());
                    }
                }

                // Quit the WebDriver instance
                driver.quit();
               // System.out.println(" Browser closed successfully");

                // Cleanup ThreadLocal variables to prevent memory leaks
                tlDriver.remove();
                tlDevTools.remove();

                // Reset all API data
                resetApiData();

            } catch (Exception e) {
                //System.err.println(" Error closing WebDriver: " + e.getMessage());
            }
        }
    }

    // ===================== API DATA GETTERS =====================
    
    /**
     * Get captured lead request payload
     * @return Request payload as JSON string
     */
    public static String getLeadRequestPayload() {
        return leadRequestPayload;
    }

    /**
     * Get captured lead response payload
     * @return Response payload as JSON string
     */
    public static String getLeadResponsePayload() {
        return leadResponsePayload;
    }

    /**
     * Get captured lead API status code
     * @return HTTP status code
     */
    public static Integer getLeadStatusCode() {
        return leadStatusCode;
    }

    /**
     * Get calculated response time in milliseconds
     * @return Response time in ms
     */
    public static long getResponseTime() {
        return responseTime;
    }

    // ===================== WAIT METHODS =====================
    
    /**
     * Wait for lead request payload to be captured
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Captured payload or null if timeout
     * @throws InterruptedException If thread is interrupted
     */
    public static String waitForLeadPayload(int timeoutSeconds) throws InterruptedException {
        int attempts = 0;
        while (leadRequestPayload == null && attempts < timeoutSeconds * 2) {
            Thread.sleep(500);
            attempts++;
        }
        return leadRequestPayload;
    }

    /**
     * Wait for lead status code to be captured
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Status code or -1 if timeout
     * @throws InterruptedException If thread is interrupted
     */
    public static int waitForLeadStatusCode(int timeoutSeconds) throws InterruptedException {
        int attempts = 0;
        while (leadStatusCode == null && attempts < timeoutSeconds * 2) {
            Thread.sleep(500);
            attempts++;
        }
        return leadStatusCode != null ? leadStatusCode : -1;
    }

    /**
     * Wait for lead response payload to be captured
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Response payload or null if timeout
     * @throws InterruptedException If thread is interrupted
     */
    public static String waitForLeadResponsePayload(int timeoutSeconds) throws InterruptedException {
        int attempts = 0;
        int maxAttempts = timeoutSeconds * 2;
        
        //System.out.println(" Waiting for response payload (max " + timeoutSeconds + " seconds)...");
        
        while (leadResponsePayload == null && attempts < maxAttempts) {
            Thread.sleep(500);
            attempts++;
            
            // Log progress every 2 seconds
            if (attempts % 4 == 0) {
                //System.out.println(" Still waiting... (" + (attempts / 2) + "s elapsed)");
            }
        }
        
        if (leadResponsePayload == null) {
            //System.err.println(" Response payload not captured after " + timeoutSeconds + " seconds");
           // System.err.println(" Debug info:");
           // System.err.println("   - Request ID: " + leadRequestId);
          //  System.err.println("   - Status Code: " + leadStatusCode);
           // System.err.println("   - Request Payload: " + (leadRequestPayload != null ? "Captured" : " Not captured"));
        } else {
            //System.out.println(" Response payload ready after " + (attempts / 2.0) + " seconds");
        }
        
        return leadResponsePayload;
    }

    // ===================== UTILITY METHODS =====================
    
    /**
     * Reset all captured API data
     * Call this before starting a new test scenario
     */
    public static void resetApiData() {
       /* System.out.println(" Resetting API capture data...");
        System.out.println("   Previous state:");
        System.out.println("   - Request: " + (leadRequestPayload != null ? " (" + leadRequestPayload.length() + " chars)" : "❌"));
        System.out.println("   - Response: " + (leadResponsePayload != null ? " (" + leadResponsePayload.length() + " chars)" : "❌"));
        System.out.println("   - Status: " + (leadStatusCode != null ? " " + leadStatusCode : ""));
        System.out.println("   - URL: " + (capturedApiUrl != null ? " " + capturedApiUrl : ""));*/
        
        leadRequestPayload = null;
        leadResponsePayload = null;
        leadStatusCode = null;
        requestStartTime = 0;
        responseEndTime = 0;
        responseTime = 0;
        leadRequestId = null;
        capturedApiUrl = null;
        
        //System.out.println(" API capture data reset complete");
    }

    /**
     * Check if driver is initialized
     * @return true if driver exists
     */
    public static boolean isDriverInitialized() {
        return getDriver() != null;
    }

    /**
     * Check if DevTools is initialized
     * @return true if DevTools exists
     */
    public static boolean isDevToolsInitialized() {
        return getDevTools() != null;
    }
    
    /**
     * Print debug information about current API capture state
     * Use this to troubleshoot capture issues
     */
    public static void printApiCaptureDebugInfo() {
       /* System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" API CAPTURE DEBUG INFO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" Monitored API Patterns:");
        System.out.println("   - " + SHORT_FORM_API);
        System.out.println("   - " + LEAD_FORM_API);
        System.out.println("");
        System.out.println(" Captured URL:");*/
        if (capturedApiUrl != null) {
           // System.out.println("    " + capturedApiUrl);
            boolean matches = capturedApiUrl.contains(SHORT_FORM_API) || capturedApiUrl.contains(LEAD_FORM_API);
           // System.out.println("   Pattern Match: " + (matches ? "YES" : "NO"));
        } else {
            //System.out.println("    No API URL captured yet");
            //System.out.println("    This means no request matching patterns was detected!");
        }
       /* System.out.println("");
        System.out.println(" Capture Status:");
        System.out.println("   Request Payload:  " + (leadRequestPayload != null ? 
            " Captured (" + leadRequestPayload.length() + " chars)" : " Not captured"));
        System.out.println("   Response Payload: " + (leadResponsePayload != null ? 
            "Captured (" + leadResponsePayload.length() + " chars)" : " Not captured"));
        System.out.println("   Status Code:      " + (leadStatusCode != null ? 
            " " + leadStatusCode : " Not captured"));
        System.out.println("   Response Time:    " + (responseTime > 0 ? 
            " " + responseTime + " ms" : " Not captured"));
        System.out.println("   Request ID:       " + (leadRequestId != null ? 
            " " + leadRequestId : " Not set"));
        System.out.println("");
        System.out.println(" DevTools Status:");
        System.out.println("   Driver:   " + (getDriver() != null ? " Initialized" : "Not initialized"));
        System.out.println("   DevTools: " + (getDevTools() != null ? " Initialized" : " Not initialized"));
        System.out.println("");
        System.out.println(" Troubleshooting Tips:");*/
        if (capturedApiUrl == null) {
           /* System.out.println("    No API detected - Check if:");
            System.out.println("      1. Your API URL contains '/lead/shortForm' or '/lead/leadForm'");
            System.out.println("      2. The API call was made after DevTools initialization");
            System.out.println("      3. The API call completed successfully");*/
        } else if (leadResponsePayload == null) {
            //System.out.println("    Response not captured - Try:");
           // System.out.println("      1. Add Thread.sleep(3000) AFTER form submission");
            //System.out.println("      2. Increase wait time to 20+ seconds");
           // System.out.println("      3. Check console for 'Response Payload Captured' message");
            //System.out.println("      4. Verify API returns body (not 204 No Content)");
        }
      //  System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }
    
    /**
     * Get the captured API URL for debugging
     * @return Captured API URL or null
     */
    public static String getCapturedApiUrl() {
        return capturedApiUrl;
    }
}
