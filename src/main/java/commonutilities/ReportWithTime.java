package commonutilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ReportWithTime {

    private static final String TIMESTAMP =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_hh-mm-a"));

    public static final String HTML_REPORT =
            "./report/html/Birlaopus_" + TIMESTAMP + ".html";

    public static final String PDF_REPORT =
            "./report/pdf/Birlaopus_extent_" + TIMESTAMP + ".pdf";

    public static final String FAILED_REPORT =
            "./report/pdf/FailedScenarioReport_" + TIMESTAMP + ".pdf";

    /**
     * Rename reports with timestamp.
     */
    public static void createTimestampReports() throws IOException {

        File htmlReport = new File("./report/html/Birlaopus.html");
        File pdfReport = new File("./report/pdf/Birlaopus_extent.pdf");
        File failedReport = new File("./report/pdf/FailedScenarioReport.pdf");

        // Delete old timestamped reports
        deleteIfExists(HTML_REPORT);
        deleteIfExists(PDF_REPORT);
        deleteIfExists(FAILED_REPORT);

        // Rename (Move) reports
        if (htmlReport.exists()) {
            Files.move(
                    htmlReport.toPath(),
                    new File(HTML_REPORT).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        if (pdfReport.exists()) {
            Files.move(
                    pdfReport.toPath(),
                    new File(PDF_REPORT).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        if (failedReport.exists()) {
            Files.move(
                    failedReport.toPath(),
                    new File(FAILED_REPORT).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
    }

    private static void deleteIfExists(String filePath) {

        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Email attachment paths.
     */
    public static List<String> getAttachmentPaths() {

        return Arrays.asList(
                PDF_REPORT,
                HTML_REPORT,
                FAILED_REPORT
        );
    }
}