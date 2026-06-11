package commonutilities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApiUtil {

    // ================= LAST API DATA =================
    public static volatile String lastRequest;
    public static volatile String lastResponse;
    public static volatile Integer lastStatusCode;
    public static volatile String lastUrl;

    public static volatile String lastDecryptedRequest;
    public static volatile String lastDecryptedResponse;

    public static volatile String lastRequestTime;
    public static volatile String lastResponseTime;

    // ================= STORAGE MAP =================
    public static Map<String, String> apiRequests = new ConcurrentHashMap<>();
    public static Map<String, String> apiResponses = new ConcurrentHashMap<>();
    public static Map<String, Integer> apiStatusCodes = new ConcurrentHashMap<>();
    public static Map<String, String> requestMap = new ConcurrentHashMap<>();
    public static Map<String, String> responseMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> statusMap = new ConcurrentHashMap<>();

    // ================= STORE REQUEST =================
    public static void storeRequest(String url, String request) {
        apiRequests.put(url, request);
        lastRequest = request;
        lastUrl = url;
    }

    // ================= STORE RESPONSE =================
    public static void storeResponse(String url, String response) {
        apiResponses.put(url, response);
        lastResponse = response;
        lastUrl = url;
    }

    // ================= STORE STATUS =================
    public static void storeStatus(String url, int statusCode) {
        apiStatusCodes.put(url, statusCode);
        lastStatusCode = statusCode;
        lastUrl = url;
    }

    // ================= DECRYPT STORE =================
    public static void storeDecryptedRequest(String value) {
        lastDecryptedRequest = value;
    }

    public static void storeDecryptedResponse(String value) {
        lastDecryptedResponse = value;
    }

    // ================= GETTERS =================
    public static String getRequestByUrl(String url) {
        return apiRequests.get(url);
    }

    public static String getResponseByUrl(String url) {
        return apiResponses.get(url);
    }

    public static Integer getStatusByUrl(String url) {
        return apiStatusCodes.get(url);
    }

    public static void reset() {
        lastRequest = null;
        lastResponse = null;
        lastStatusCode = null;
        lastUrl = null;

        lastDecryptedRequest = null;
        lastDecryptedResponse = null;

        apiRequests.clear();
        apiResponses.clear();
        apiStatusCodes.clear();
    }
    
    public static String getLatestRequest() {

        return requestMap.values()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public static String getLatestResponse() {

        return responseMap.values()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public static Integer getLatestStatus() {

        return statusMap.values()
                .stream()
                .reduce((first, second) -> second)
                .orElse(-1);
    }
    public static boolean isValidResponse(String response) {

        return response != null
                && !response.isEmpty()
                && !response.equals("204_NO_BODY");
    }
}