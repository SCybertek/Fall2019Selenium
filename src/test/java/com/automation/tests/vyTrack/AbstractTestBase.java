package com.automation.tests.vyTrack;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.automation.utilities.ExcelUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.imageio.IIOException;
import java.io.IOException;

public abstract class AbstractTestBase {
    //base class for other classes
    //it has to be extended
    //we cannot use it on its own/ by definition it is NOT concrete

    protected WebDriverWait wait; //for explicit wait ( explicit i and implicit cannot be used in the same task - can be in the same class)
    //if this variable needs to be visible in same class and subclasses make it protected
    //if anywhere then public

    //we can also insert Actions in base class
    protected Actions actions;
    //you cannot create a generic Select class!! it needs to be created for specific element!

// * The ExtentReports report client for starting reporters and building reports.
// * For most applications, you should have one ExtentReports instance for the
// * entire JVM.
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

    protected static int row = 1;
    protected ExcelUtil excelUtil;

    @BeforeTest //used to create the report
    @Parameters("reportName") //parameters comes from .xml ..and we need to specify this in order to get different reports (not override the same one all the time
    public void setUpTest(@Optional String reportName){ //here we are providing parameter for tests (?)
        //@Optional - to make parameter optional
        //if you do not specify it, testing will require to specify this parameter for every test, in XML runner

        reportName = reportName == null ? "report.html" : reportName + ".html"; //making our report dynamic
        report = new ExtentReports(); //one report for everyone

        String reportPath = "";
        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }

        System.out.println("Report name: " +reportName);

        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Results");

    }

    @AfterTest
    public void afterTest() {
        report.flush();//to release a report
    }


    @BeforeMethod
    public void setup(){
        String URL = ConfigurationReader.getProperty("qa3");
        Driver.getDriver().get(URL);// open URL = q1 in the browser
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),25);
        actions = new Actions(Driver.getDriver());
    }

    /**
     * we changed our after method :
     * added screenshot , so that if test fails then it should take screen shot after failed test
     */

    @AfterMethod
    //This interface ITestResult - describes the result of a test.
    public void teardown(ITestResult iTestResult) throws IOException {
        //if test failed, take screenshot
        if (iTestResult.getStatus()==ITestResult.FAILURE){
            //screenshot will have a name of the test
            String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
            test.fail(iTestResult.getName());//attach test name that failed
           // BrowserUtils.wait(2);
            test.addScreenCaptureFromPath(screenshotPath, "Failed");//attach screenshot
            test.fail(iTestResult.getThrowable());//attach console output

            //if excelUtil object was created
            //set value of the test as failed

            if (excelUtil != null) {
                excelUtil.setCellData("FAILED", "result", row++);
            }

        }
        //BrowserUtils.wait(1);
        Driver.closeDriver();
        //any webdriver object open web browser
        //when we use this singleton webDriver we are ensuring that it is always the same web driver

    }

    //page class - we create page object , every page object contains web elements .. and we store locators in these classes .
    //this pattern is called test page object model
    //well organized , easily to maintain
    //every page that will be tested need to have page object class

    // configuration.properties = does not have locators ..but some people do that too

    //VASYL SAYS keep elements inside the class because
    //page class broken down into 2 piece then you have to jump back and forth between classes and config file


    //package package represents collection of page classes
}
