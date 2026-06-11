package commonutilities;

import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class ApiLoggingFilter {

    public static void logFullApiDetails(
            String url,
            String method,
            Map<String, String> headers,
            Map<String, String> queryParams,
            String requestBody,
            String encryptedRequest,
            int statusCode,
            String decryptedResponse,
            String encryptedResponse
    ) {

        StringBuilder log = new StringBuilder();

        log.append("<b>===== API REQUEST =====</b><br>");
        log.append("<b>URL:</b> ").append(url).append("<br>");
        log.append("<b>Method:</b> ").append(method).append("<br><br>");

        log.append("<b>Headers:</b><br>");
        if (headers != null && !headers.isEmpty()) {
            headers.forEach((k, v) ->
                    log.append(k).append(": ").append(v).append("<br>")
            );
        } else {
            log.append("No Headers<br>");
        }

        log.append("<br><b>Query Params:</b><br>");
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((k, v) ->
                    log.append(k).append(": ").append(v).append("<br>")
            );
        } else {
            log.append("No Query Params<br>");
        }

        log.append("<br><b>Request Body:</b><br><pre>")
                .append(requestBody != null ? requestBody : "NULL")
                .append("</pre>");

        log.append("<br><b>Encrypted Request:</b><br><pre>")
                .append(encryptedRequest != null ? encryptedRequest : "NULL")
                .append("</pre>");

        log.append("<br><b>===== API RESPONSE =====</b><br>");
        log.append("<b>Status Code:</b> ").append(statusCode).append("<br>");

        log.append("<br><b>Decrypted Response:</b><br><pre>")
                .append(decryptedResponse != null ? decryptedResponse : "NULL")
                .append("</pre>");

        log.append("<br><b>Encrypted Response:</b><br><pre>")
                .append(encryptedResponse != null ? encryptedResponse : "NULL")
                .append("</pre>");

        ExtentCucumberAdapter.getCurrentStep()
                .log(Status.INFO, log.toString());
    }
}