package commonutilities;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ReportUtil {
	
	 public static int passed = 0;
	    public static int failed = 0;

	    public static int stepsPassed = 0;
	    public static int stepsFailed = 0;
	    public static int stepsSkipped = 0;

	    public static void readCucumberReport() throws Exception {

	        passed = 0;
	        failed = 0;
	        stepsPassed = 0;
	        stepsFailed = 0;
	        stepsSkipped = 0;

	        String content = new String(Files.readAllBytes(Paths.get("target/cucumber.json")));
	        JSONArray features = new JSONArray(content);

	        for (int i = 0; i < features.length(); i++) {

	            JSONArray elements = features.getJSONObject(i).getJSONArray("elements");

	            for (int j = 0; j < elements.length(); j++) {

	                JSONObject scenario = elements.getJSONObject(j);

	                // ❌ Skip background
	                if (scenario.getString("type").equalsIgnoreCase("background")) {
	                    continue;
	                }

	                boolean isFailed = false;

	                JSONArray steps = scenario.getJSONArray("steps");

	                for (int k = 0; k < steps.length(); k++) {

	                    JSONObject step = steps.getJSONObject(k);

	                    String status = step.getJSONObject("result").getString("status");

	                    // ✅ Count actual step
	                    countStatus(status);

	                    if (status.equalsIgnoreCase("failed") || status.equalsIgnoreCase("undefined")) {
	                        isFailed = true;
	                    }

	                    // ✅ Count ONLY ONE postEachStep hook
	                    if (step.has("after")) {
	                        JSONArray afterArr = step.getJSONArray("after");

	                        if (afterArr.length() > 0) {
	                            String hookStatus = afterArr.getJSONObject(0)
	                                    .getJSONObject("result")
	                                    .getString("status");

	                            countStatus(hookStatus);
	                        }
	                    }
	                }

	                // ✅ Scenario result
	                if (isFailed) {
	                    failed++;
	                } else {
	                    passed++;
	                }
	            }
	        }
	    }

	    private static void countStatus(String status) {
	        if (status.equalsIgnoreCase("passed")) stepsPassed++;
	        else if (status.equalsIgnoreCase("failed") || status.equalsIgnoreCase("undefined")) stepsFailed++;
	        else if (status.equalsIgnoreCase("skipped")) stepsSkipped++;
	    }
	}