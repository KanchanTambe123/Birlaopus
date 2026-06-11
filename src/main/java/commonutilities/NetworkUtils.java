package commonutilities;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.Response;
public class NetworkUtils {
	
	
	
	 private static DevTools devTools;
	    private static String responseBody = "";

	    public static void init(WebDriver driver) {

	        devTools = ((ChromeDriver) driver).getDevTools();
	        devTools.createSession();

	        Network.enable(
	        	    Optional.empty(),
	        	    Optional.empty(),
	        	    Optional.empty(),
	        	    Optional.empty(),
	        	    Optional.empty()
	        	);

	        devTools.addListener(Network.responseReceived(), response -> {

	            String url = response.getResponse().getUrl();

	            if (url.contains("pincode") || url.contains("serviceable")) {

	                try {
	                    responseBody = devTools.send(
	                            Network.getResponseBody(response.getRequestId())
	                    ).getBody();

	                    System.out.println("Captured API Response: " + responseBody);

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    public static String getResponse() {
	        return responseBody;
	    }

}
