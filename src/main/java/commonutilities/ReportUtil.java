package commonutilities;

import org.json.JSONArray;
import org.json.JSONObject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.awt.Color;
import java.awt.Font;

import com.itextpdf.kernel.colors.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.io.image.ImageDataFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * ReportUtil - Generates Extent-style PDF reports showing ONLY failed test cases
 * Reads cucumber.json and creates a comprehensive PDF with dashboard, charts, and failed scenario details
 */
public class ReportUtil {

    public static int passed = 0;
    public static int failed = 0;
    public static int featurePassed = 0;
    public static int featureFailed = 0;
    public static int stepsPassed = 0;
    public static int stepsFailed = 0;
    public static int stepsSkipped = 0;

    public static LocalDateTime startTime;
    public static LocalDateTime endTime;

    /**
     * Main method to read Cucumber report and generate PDF with only failed scenarios
     */
    public static void readCucumberReport() throws Exception {

        startTime = LocalDateTime.now();

        passed = 0;
        failed = 0;
        stepsPassed = 0;
        stepsFailed = 0;
        stepsSkipped = 0;
        featurePassed = 0;
        featureFailed = 0;
        // Check if cucumber.json exists
        File cucumberJsonFile = new File("target/cucumber.json");
        if (!cucumberJsonFile.exists()) {
            System.err.println(" Error: target/cucumber-reports/cucumber.json not found!");
            System.err.println("Please ensure Cucumber tests have been executed and JSON report is generated.");
            System.err.println("Add this to @CucumberOptions: plugin = {\"json:target/cucumber-reports/cucumber.json\"}");
            return;
        }

        System.out.println("Reading cucumber.json...");
        String content = new String(Files.readAllBytes(Paths.get("target/cucumber.json")));
        JSONArray features = new JSONArray(content);

        // Create report/pdf directory if it doesn't exist
        File reportDir = new File("report/pdf");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
            System.out.println("Created report/pdf directory");
        }

        System.out.println("Generating PDF report...");
        PdfWriter writer = new PdfWriter("report/pdf/FailedScenarioReport.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        int totalScenarios = 0;

        //  FIRST PASS - Count all scenarios and steps
        for (int i = 0; i < features.length(); i++) {

            JSONObject feature = features.getJSONObject(i);
            JSONArray elements = feature.getJSONArray("elements");

            boolean isFeatureFailed = false;
            boolean hasScenario = false;

            for (int j = 0; j < elements.length(); j++) {

                JSONObject scenario = elements.getJSONObject(j);
                String elementType = scenario.getString("type");

                // ✅ Count steps from Background elements (they run before each scenario)
                if (elementType.equalsIgnoreCase("background")) {
                    JSONArray backgroundSteps = scenario.getJSONArray("steps");
                    for (int k = 0; k < backgroundSteps.length(); k++) {
                        String status = backgroundSteps.getJSONObject(k)
                                .getJSONObject("result")
                                .getString("status");
                        countStatus(status);
                    }
                    continue; // Don't count background as a scenario
                }

                hasScenario = true;

                boolean isScenarioFailed = false;
                JSONArray stepsArray = scenario.getJSONArray("steps");

                // ✅ Count actual test steps (not @AfterStep hooks)
                for (int k = 0; k < stepsArray.length(); k++) {

                    JSONObject step = stepsArray.getJSONObject(k);

                    // Count actual test step ONLY
                    String status = step.getJSONObject("result").getString("status");

                    countStatus(status);

                    if (status.equalsIgnoreCase("failed")
                            || status.equalsIgnoreCase("undefined")) {
                        isScenarioFailed = true;
                    }

                    // Hooks are not counted as steps
                }

                if (isScenarioFailed) {
                    failed++;
                    isFeatureFailed = true;
                } else {
                    passed++;
                }
            }

            // ✅ FEATURE COUNT LOGIC
            if (hasScenario) {
                if (isFeatureFailed) featureFailed++;
                else featurePassed++;
            }
        }

        // Calculate actual test execution time from report data
        long totalDurationNanos = 0;
        for (int i = 0; i < features.length(); i++) {
            JSONArray elements = features.getJSONObject(i).getJSONArray("elements");
            for (int j = 0; j < elements.length(); j++) {
                JSONObject scenario = elements.getJSONObject(j);
                // ✅ Include background steps in duration (they execute before each scenario)
                JSONArray stepsArray = scenario.getJSONArray("steps");
                for (int k = 0; k < stepsArray.length(); k++) {
                	JSONObject step = stepsArray.getJSONObject(k);

                	JSONObject stepResult = step.getJSONObject("result");

                	if (stepResult.has("duration")) {
                	    totalDurationNanos += stepResult.getLong("duration");
                	}

                	// ✅ Include @AfterStep duration for timing (but NOT for step count!)
                	if (step.has("after")) {

                	    JSONArray afterArr = step.getJSONArray("after");

                	    for (int a = 0; a < afterArr.length(); a++) {

                	        JSONObject afterObj = afterArr.getJSONObject(a);

                	        if (afterObj.has("result")) {

                	            JSONObject hookResult = afterObj.getJSONObject("result");

                	            if (hookResult.has("duration")) {
                	                totalDurationNanos += hookResult.getLong("duration");
                	            }
                	        }
                	    }
                	}
                }
            }
        }
        
        long totalSeconds = totalDurationNanos / 1_000_000_000L;
        endTime = startTime.plusSeconds(totalSeconds);

        System.out.println("Test Summary: " + totalScenarios + " total scenarios | " + passed + " passed | " + failed + " failed");
        System.out.println(" Duration: " + totalSeconds + " seconds");

        // ✅ DASHBOARD
        addDashboard(document);

        // Add page break before Failed Test Cases section
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        // FAILED TEST CASES HEADER
        if (failed > 0) {
            document.add(new Paragraph("Failed Test Cases")
                    .setFontSize(24)
                    .setBold()
                    .setFontColor(new DeviceRgb(220, 53, 69))
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginTop(20)
                    .setMarginBottom(20));
        } else {
            Div successBanner = new Div()
                    .setBackgroundColor(new DeviceRgb(40, 167, 69))
                    .setPadding(20)
                    .setMarginTop(20)
                    .setMarginBottom(20);

            successBanner.add(new Paragraph(" All Tests Passed!")
                    .setFontSize(22)
                    .setBold()
                    .setFontColor(ColorConstants.WHITE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(0));

            document.add(successBanner);
        }

        // ✅ FAILED SCENARIOS ONLY
        for (int i = 0; i < features.length(); i++) {

            JSONObject feature = features.getJSONObject(i);
            String featureName = feature.getString("name");
            JSONArray elements = feature.getJSONArray("elements");

            for (int j = 0; j < elements.length(); j++) {

                JSONObject scenario = elements.getJSONObject(j);

                if (scenario.getString("type").equalsIgnoreCase("background"))
                    continue;

                boolean isFailed = false;
                JSONArray stepsArray = scenario.getJSONArray("steps");

                for (int k = 0; k < stepsArray.length(); k++) {

                    String status = stepsArray.getJSONObject(k)
                            .getJSONObject("result")
                            .getString("status");

                    if (status.equalsIgnoreCase("failed") || status.equalsIgnoreCase("undefined")) {
                        isFailed = true;
                    }
                }

                if (!isFailed) continue;

                // SCENARIO BOX - Enhanced Design
                Div box = new Div()
                        .setBackgroundColor(ColorConstants.WHITE)
                        .setPadding(12)
                        .setMarginBottom(12)
                        .setBorder(new SolidBorder(new DeviceRgb(231, 76, 60), 3));

                // Feature name banner
                Div featureHeader = new Div()
                        .setBackgroundColor(new DeviceRgb(52, 73, 94))
                        .setPadding(8)
                        .setMarginBottom(10);

                featureHeader.add(new Paragraph("Feature: " + featureName)
                        .setFontSize(10)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE)
                        .setMarginBottom(0));

                box.add(featureHeader);

                // Scenario name
                box.add(new Paragraph(" Scenario: " + scenario.getString("name"))
                        .setBold()
                        .setFontSize(12)
                        .setFontColor(new DeviceRgb(192, 57, 43))
                        .setMarginBottom(10)
                        .setMarginLeft(5));

                for (int k = 0; k < stepsArray.length(); k++) {

                    JSONObject step = stepsArray.getJSONObject(k);
                    String keyword = step.getString("keyword");
                    String name = step.getString("name");
                    String status = step.getJSONObject("result").getString("status");

                    Paragraph p;

                    if (status.equalsIgnoreCase("failed")) {
                        p = new Paragraph("❌ " + keyword + name)
                                .setBackgroundColor(new DeviceRgb(242, 215, 213))
                                .setFontColor(new DeviceRgb(120, 40, 31))
                                .setPadding(6)
                                .setPaddingLeft(10)
                                .setBold()
                                .setFontSize(10)
                                .setBorderLeft(new SolidBorder(new DeviceRgb(231, 76, 60), 4));
                                
                        // Add error message if available
                        if (step.getJSONObject("result").has("error_message")) {
                            String errorMsg = step.getJSONObject("result").getString("error_message");
                            p.setMarginBottom(5);
                            p.setMarginLeft(5);
                            box.add(p);

                            // Error box
                            Div errorBox = new Div()
                                    .setBackgroundColor(new DeviceRgb(253, 237, 236))
                                    .setPadding(8)
                                    .setMarginLeft(15)
                                    .setMarginTop(5)
                                    .setMarginBottom(8)
                                    .setBorder(new SolidBorder(new DeviceRgb(231, 76, 60), 1));

                            errorBox.add(new Paragraph("Error Details:")
                                    .setFontSize(9)
                                    .setBold()
                                    .setFontColor(new DeviceRgb(192, 57, 43))
                                    .setMarginBottom(3));

                            errorBox.add(new Paragraph(errorMsg)
                                    .setFontSize(8)
                                    .setFontColor(new DeviceRgb(120, 40, 31))
                                    .setItalic()
                                    .setMarginBottom(0));

                            box.add(errorBox);
                            p = null; // Don't add again
                        }
                    } else if (status.equalsIgnoreCase("skipped")) {
                        p = new Paragraph(" " + keyword + name)
                                .setBackgroundColor(new DeviceRgb(252, 243, 207))
                                .setFontColor(new DeviceRgb(133, 100, 4))
                                .setPadding(6)
                                .setPaddingLeft(10)
                                .setFontSize(10)
                                .setBorderLeft(new SolidBorder(new DeviceRgb(241, 196, 15), 3));
                    } else continue; // Skip passed steps

                    if (p != null) {
                        p.setMarginBottom(5);
                        p.setMarginLeft(5);
                        box.add(p);
                    }

                    // 📸 SCREENSHOT with enhanced design
                    if (status.equalsIgnoreCase("failed") && step.has("after")) {

                        JSONArray afterArr = step.getJSONArray("after");

                        for (int a = 0; a < afterArr.length(); a++) {

                            JSONObject afterObj = afterArr.getJSONObject(a);

                            if (afterObj.has("embeddings")) {

                                JSONArray embeds = afterObj.getJSONArray("embeddings");

                                for (int e = 0; e < embeds.length(); e++) {

                                    String base64 = embeds.getJSONObject(e).getString("data");
                                    byte[] imgBytes = Base64.getDecoder().decode(base64);

                                    // Screenshot section
                                    Div screenshotSection = new Div()
                                            .setBackgroundColor(new DeviceRgb(248, 249, 250))
                                            .setPadding(8)
                                            .setMarginTop(8)
                                            .setMarginLeft(5)
                                            .setMarginBottom(8)
                                            .setBorder(new SolidBorder(new DeviceRgb(206, 212, 218), 1));

                                    screenshotSection.add(new Paragraph("Screenshot Evidence:")
                                            .setFontSize(9)
                                            .setBold()
                                            .setFontColor(new DeviceRgb(73, 80, 87))
                                            .setMarginBottom(6));

                                    Image img = new Image(ImageDataFactory.create(imgBytes));
                                    img.scaleToFit(400, 300);
                                    img.setHorizontalAlignment(HorizontalAlignment.CENTER);
                                    img.setBorder(new SolidBorder(new DeviceRgb(173, 181, 189), 1));

                                    screenshotSection.add(img);
                                    box.add(screenshotSection);
                                }
                            }
                        }
                    }
                }

                document.add(box);
            }
        }

        document.close();
        System.out.println("Extent-style PDF with ONLY Failed Test Cases Generated Successfully!");
        System.out.println("Report Location: report/pdf/ExtentLike_FailedReport.pdf");
        System.out.println("Failed: " + failed + " | Passed: " + passed);
    }

    /**
     * Count step status for summary statistics
     */
    private static void countStatus(String status) {
        if (status.equalsIgnoreCase("passed")) {
            stepsPassed++;
        } else if (status.equalsIgnoreCase("failed") || status.equalsIgnoreCase("undefined")) {
            stepsFailed++;
        } else if (status.equalsIgnoreCase("skipped")) {
            stepsSkipped++;
        }
    }

    // ================= DASHBOARD =================
    public static void addDashboard(Document document) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm:ss a");
        DateTimeFormatter compactFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        String start = startTime.format(compactFormatter);
        String end = endTime.format(compactFormatter);
        String reportDate = endTime.format(compactFormatter);

        long mins = Duration.between(startTime, endTime).toMinutes();
        long secs = Duration.between(startTime, endTime).toSeconds() % 60;

        // TITLE BAR - Project name left, Date right
        Table titleBar = new Table(UnitValue.createPercentArray(new float[]{70, 30})).useAllAvailableWidth();
        titleBar.setMarginBottom(10);
        
        Cell titleCell = new Cell()
                .add(new Paragraph("BirlaOpus")
                        .setBold()
                        .setFontSize(28)
                        .setFontColor(ColorConstants.BLACK)
                        .setMarginBottom(0))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingLeft(10);
        
        Cell dateCell = new Cell()
                .add(new Paragraph(reportDate)
                        .setFontSize(11)
                        .setFontColor(new DeviceRgb(100, 100, 100))
                        .setMarginBottom(0)
                        .setMarginTop(0))
                .setBorder(new SolidBorder(new DeviceRgb(200, 200, 200), 1))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(8);
        
        titleBar.addCell(titleCell);
        titleBar.addCell(dateCell);
        document.add(titleBar);

        // INFO BAR - Start, End, Duration in red boxes with spacing
        Table info = new Table(UnitValue.createPercentArray(new float[]{32, 4, 32, 4, 28})).useAllAvailableWidth();
        info.setMarginBottom(15);
        info.setMarginTop(10);

        info.addCell(createRedInfoBox("Start : " + start));
        info.addCell(createSpacerCell());
        info.addCell(createRedInfoBox("End : " + end));
        info.addCell(createSpacerCell());
        info.addCell(createRedInfoBox("Duration : " + mins + " m " + secs + " s"));

        document.add(info);

        // LABELS ROW - Features, Scenarios, Steps with spacing
        Table labelsRow = new Table(UnitValue.createPercentArray(new float[]{32, 4, 32, 4, 28})).useAllAvailableWidth();
        labelsRow.setMarginTop(15);
        labelsRow.setMarginBottom(5);
        
        labelsRow.addCell(createLabelCell("Features"));
        labelsRow.addCell(createSpacerCell());
        labelsRow.addCell(createLabelCell("Scenarios"));
        labelsRow.addCell(createSpacerCell());
        labelsRow.addCell(createLabelCell("Steps"));
        
        document.add(labelsRow);

        // SUMMARY CARDS - Dark styled boxes with stats and spacing
        Table summary = new Table(UnitValue.createPercentArray(new float[]{32, 4, 32, 4, 28})).useAllAvailableWidth();
        summary.setMarginBottom(15);

        summary.addCell(createSummaryBox(featurePassed, featureFailed, 0));
        summary.addCell(createSpacerCell());
        summary.addCell(createSummaryBox(passed, failed, 0));
        summary.addCell(createSpacerCell());
        summary.addCell(createSummaryBox(stepsPassed, stepsFailed, stepsSkipped));

        document.add(summary);
        document.add(new Paragraph("\n").setMarginBottom(5));

        // LABELS ROW FOR CHARTS - Features, Scenarios, Steps with spacing
        Table chartLabels = new Table(UnitValue.createPercentArray(new float[]{32, 4, 32, 4, 28})).useAllAvailableWidth();
        chartLabels.setMarginTop(10);
        chartLabels.setMarginBottom(5);
        
        chartLabels.addCell(createChartLabelCell("FEATURES"));
        chartLabels.addCell(createSpacerCell());
        chartLabels.addCell(createChartLabelCell("SCENARIOS"));
        chartLabels.addCell(createSpacerCell());
        chartLabels.addCell(createChartLabelCell("STEPS"));
        
        document.add(chartLabels);

        // DONUT CHARTS ONLY (in boxes with spacing)
        Table charts = new Table(UnitValue.createPercentArray(new float[]{32, 4, 32, 4, 28})).useAllAvailableWidth();
        charts.setMarginBottom(15);

        charts.addCell(createChartInBox(generateDonutChart("Features", featurePassed, featureFailed, 0)));
        charts.addCell(createSpacerCell());
        charts.addCell(createChartInBox(generateDonutChart("Scenarios", passed, failed, 0)));
        charts.addCell(createSpacerCell());
        charts.addCell(createChartInBox(generateDonutChart("Steps", stepsPassed, stepsFailed, stepsSkipped)));

        document.add(charts);

        // SEPARATOR
        document.add(new Paragraph("\n")
                .setBorderTop(new SolidBorder(new DeviceRgb(220, 220, 220), 2))
                .setMarginTop(5)
                .setMarginBottom(10));
    }

    private static Cell createRedInfoBox(String text) {
        return new Cell()
                .add(new Paragraph(text)
                        .setFontSize(9)
                        .setBold()
                        .setFontColor(new DeviceRgb(220, 53, 69))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(0)
                        .setMarginTop(0))
                .setBackgroundColor(ColorConstants.WHITE)
                .setBorder(new SolidBorder(new DeviceRgb(220, 53, 69), 2))
                .setPadding(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMinHeight(40)
                .setMaxHeight(40);
    }

    private static Cell createLabelCell(String label) {
        return new Cell()
                .add(new Paragraph(label)
                        .setFontSize(12)
                        .setBold()
                        .setFontColor(ColorConstants.BLACK)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(0))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createSpacerCell() {
        return new Cell()
                .setBorder(Border.NO_BORDER)
                .setBackgroundColor(ColorConstants.WHITE);
    }

    private static Cell createTopInfoBox(String title, String value) {
        Paragraph titleP = new Paragraph(title)
                .setFontSize(11)
                .setFontColor(new DeviceRgb(100, 100, 100))
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(8);

        Paragraph valueP = new Paragraph(value)
                .setFontSize(13)
                .setBold()
                .setFontColor(ColorConstants.BLACK)
                .setTextAlignment(TextAlignment.CENTER);

        return new Cell()
                .add(titleP)
                .add(valueP)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBackgroundColor(new DeviceRgb(245, 245, 245))
                .setBorder(new SolidBorder(new DeviceRgb(220, 220, 220), 1))
                .setPadding(15)
                .setMinHeight(65);
    }

    private static Cell createBox(String t, String v) {
        return new Cell()
                .add(new Paragraph(t + "\n" + v)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER))
                .setPadding(10)
                .setBorder(new SolidBorder(1))
                .setBackgroundColor(new DeviceRgb(240, 240, 240));
    }

    private static Cell createSummaryBox(int p, int f, int s) {

        Paragraph stats = new Paragraph();
        stats.add(new Text("PASSED - " + p + "\n")
                .setFontColor(new DeviceRgb(40, 167, 69))  // Green
                .setBold()
                .setFontSize(11));
        stats.add(new Text("FAILED - " + f + "\n")
                .setFontColor(new DeviceRgb(220, 53, 69))  // Red
                .setBold()
                .setFontSize(11));
        stats.add(new Text("SKIPPED - " + s)
                .setFontColor(new DeviceRgb(255, 193, 7))  // Yellow
                .setBold()
                .setFontSize(11));
        stats.setTextAlignment(TextAlignment.CENTER);

        return new Cell()
                .add(stats)
                .setBackgroundColor(new DeviceRgb(52, 58, 64))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(12)
                .setMinHeight(70)
                .setBorder(Border.NO_BORDER);
    }

    private static Cell createChartLabelCell(String label) {
        return new Cell()
                .add(new Paragraph(label)
                        .setFontSize(14)
                        .setBold()
                        .setFontColor(ColorConstants.BLACK)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(0))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createChartInBox(String donutPath) throws Exception {

        // Donut chart
        Image donutImg = new Image(ImageDataFactory.create(donutPath));
        donutImg.setAutoScale(true);
        donutImg.setMaxWidth(200);
        donutImg.setMaxHeight(200);
        donutImg.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return new Cell()
                .add(donutImg)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(15)
                .setBorder(new SolidBorder(new DeviceRgb(220, 220, 220), 1))
                .setBackgroundColor(ColorConstants.WHITE)
                .setMinHeight(220);
    }

    public static String generateDonutChart(String name, int p, int f, int s) throws Exception {

        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Passed", p);
        dataset.setValue("Failed", f);
        if (s > 0) {
            dataset.setValue("Skipped", s);
        }

        JFreeChart chart = ChartFactory.createRingChart(
                name.toUpperCase(),
                dataset,
                false, // legend
                false, // tooltips
                false  // URLs
        );

        PiePlot plot = (PiePlot) chart.getPlot();

        // Colors: Green for passed, Red for failed, Yellow for skipped
        plot.setSectionPaint("Passed", new Color(40, 167, 69));    // Green
        plot.setSectionPaint("Failed", new Color(220, 53, 69));    // Red
        plot.setSectionPaint("Skipped", new Color(255, 193, 7));   // Yellow

        // Label configuration - labels outside with connecting lines
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}", 
                new java.text.DecimalFormat("0"), 
                new java.text.DecimalFormat("0%")
        ));
        plot.setLabelFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
        plot.setLabelBackgroundPaint(new Color(255, 255, 204));
        plot.setLabelOutlinePaint(new Color(100, 100, 100));
        plot.setLabelShadowPaint(null);
        plot.setLabelPaint(Color.BLACK);
        plot.setLabelLinkPaint(Color.BLACK);
        plot.setLabelLinkStroke(new java.awt.BasicStroke(1.2f));
        plot.setLabelLinkMargin(0.05);

        // Chart styling - bigger hole for donut effect
        plot.setInteriorGap(0.30);
        plot.setOutlineVisible(true);
        plot.setOutlinePaint(new Color(220, 220, 220));
        plot.setOutlineStroke(new java.awt.BasicStroke(1.5f));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setShadowPaint(null);

        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);

        // Ensure directory exists
        new File("report/pdf").mkdirs();

        String path = "report/pdf/" + name.toLowerCase().replace(" ", "_") + "_chart.png";
        ChartUtils.saveChartAsPNG(new File(path), chart, 300, 300);

        System.out.println("Generated chart: " + path);

        return path;
    }

    /**
     * Main method for standalone execution
     */
    public static void main(String[] args) {
        try {
            readCucumberReport();
        } catch (Exception e) {
            System.err.println(" Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
