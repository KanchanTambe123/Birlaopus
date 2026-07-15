package commonutilities;

import java.util.Map;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.RequestId;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ApiUtil {
	private static volatile String leadRequestPayload;
	private static volatile String leadResponsePayload;
	private static volatile Integer leadStatusCode;

	private static volatile long requestStartTime;
	private static volatile long responseEndTime;
	private static volatile long responseTime;

	private static volatile RequestId leadRequestId;
	private static volatile String capturedApiUrl;

	private static final String SHORT_FORM_API = "/lead/shortForm";
	private static final String LEAD_FORM_API = "/lead/leadForm";
	
	
	
	
	public static void initNetworkListener(DevTools devTools) {
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

        
       // System.out.println(" DevTools initialized successfully with API monitoring");
    }

	}
