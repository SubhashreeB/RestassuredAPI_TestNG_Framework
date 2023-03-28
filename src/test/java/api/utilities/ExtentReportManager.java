package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.SimpleDate;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    String reportName;


    @Override
    public void onStart(ITestContext testContext) {
        //time stamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-rport"+timeStamp+".html";
        //specify location of report
        sparkReporter= new ExtentSparkReporter(".\\reports\\"+reportName);
        //Title of report
        sparkReporter.config().setDocumentTitle("RestAssure-Testng-AutomationProject");
        //Name of the report
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Pet Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "Tester");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test= extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test= extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        test= extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
