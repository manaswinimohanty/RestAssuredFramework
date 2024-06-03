package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportManager {
    public static ExtentReports extentReports;

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        String reportName = "TestReport" + formattedTime + ".html";
        return reportName;
    }

    public static void logPassDetails(String log) {
        ListenerTest.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    /**
     * This method is used to print the log in a label in red color
     * @param log
     */
    public static void logFailureDetails(String log) {
        ListenerTest.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logException(String log) {
        ListenerTest.extentTest.get().fail(log);
    }
    public static void logExceptionDetails(String log) {
        ListenerTest.extentTest.get().fail(log);
    }
    public static void logInfoDetails(String log) {
        ListenerTest.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log) {
        ListenerTest.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

    public static void logJson(String log){
        ListenerTest.extentTest.get().info(MarkupHelper.createCodeBlock(log, CodeLanguage.JSON));

    }

//map() is used to convert from one object to another applying function on each item

    /**
     * below function is used to print header and its corresponding value in tabular format.
     * @param headerList
     */
    public static void logHeaders(List<Header> headerList){
        String[][] arrayOfHeaders=headerList.stream().map(header -> new String[]{header.getName(),header.getValue()})
                        .toArray(String[][]::new);

        ListenerTest.extentTest.get().info(MarkupHelper.createTable(arrayOfHeaders,"table-sm"));
    }
}
