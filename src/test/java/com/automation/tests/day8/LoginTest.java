package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest {

    /**
     * go to Form Authentication page
     * tomsmith
     * SuperSecretPassword
     * enter valid credentials
     * verify that following sub-header message is displayed :
     * "Welcome to the Secure Area.When you are done click logout below
     *
     *
     */

private WebDriver driver;


@Test
public void loginTest(){
    //to click on the form
    driver.findElement(By.linkText("Form Authentication")).click();
    BrowserUtils.wait(2);
//                                  by.name(username)
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("tomsmith");
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("SuperSecretPassword");

    driver.findElement(By.id("wooden_spoon")).click();
    //driver.findElement(By.tagName("h4")).getText();//classname is subheader

    String expected = "Welcome to the Secure Area. When you are done click logout below.";
    String actual = driver.findElement(By.className("subheader")).getText();
//if assertion fails - it will throw exception and message will be printed
    //3 parameters : expected, actual, message in case of error) message is not a must but gives more detailed exception output
    Assert.assertEquals(actual,expected,"Sub-header message is not matching");
}


    /**
     * Given user is on the practice page
     * when user navigates to forgot password page
     * then user enters his email
     * and clicks Retrieve Password button, then user verifies the message
     * "You email has been sent!" is displayed
     */

    @Test
    public void forgotPasswordTest() {
        driver.findElement(By.linkText("Forgot Password")).click();
        BrowserUtils.wait(2);
//name(email)
        driver.findElement(By.tagName("input")).sendKeys("blabla@gmail.com", Keys.ENTER);
        String expected = "Your e-mail's been sent!";
        String actual = driver.findElement(By.name("confirmation_message")).getText();
        Assert.assertEquals(expected,actual,"Actual does not match expected result");
    }

    /**
     * * TASK for 5 minutes
     *  * Given user is on the practice landing page
     *  * When user navigates to "Checkboxes" page
     *  * And clicks on checkbox #1
     *  * Then user verifies that checkbox #1 is selected
     *  */

    @Test
    public void checkBoxes(){
        driver.findElement(By.linkText("Checkboxes")).click();
        BrowserUtils.wait(2);

        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        checkboxes.get(0).click();

        //driver.findElement(By.linkText("checkbox1")).click();
//(By.cssSelector("input[type='checkbox']"));
        //xpath //input[@type=“checkbox”][1]
        Assert.assertTrue(checkboxes.get(0).isSelected(), "Checkbox number 1 is not selected");



    }
    @BeforeMethod //dry principle
    public void setUP() {
        //setting up WebDriver
        WebDriverManager.chromedriver().version("79.0").setup();
        //HOW TO HANDLE SSL ISSUES IN SELENIUM? (secure sockets layer
        //ChromeOptions - used to customize browser for tests (change behaviour of the class)
        //website is not trusted since no Certificate
        //info is transparent not incripted
        //that makes it sensitive
        ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore SSL certificate based NOt secure message
        chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromeDriver constructor
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    //Assertion : helps us finding bugs!
    //they fail in test case if condition is falls
    //it is all about meeting expected result => if it fails then it is a BUG
    //most popular assertions:
    //assertTrue and assertEquals (rely on equals method of the class! in string it will check the content ( not the address in the heap)
    //assertNotTrue or assertNotEqual


    //static IMPORT and static instance


    //if we have BeforeMethods we do NOT need to have AfterMethods at the same time. we have to have @Test
//static block executed once when the class is loading !
    //Aftermethod, will give opportunity to run test separately(?)

    //before method run automatically before test! the same for after methods

    //Interview Question:
    //if you have 500 tests but you want to run only 40 for smoke test .how will you do it?
    //testsuite = XML runners are used to create suite of tests that specify which class or which tests from these classes you want to run
    //Modules = this is the way we organize (or group) the test cases (or test scripts ) usually
                //all test cases related to one will be inside it
    // when we will need to run suite of test we can point to that (module)package or several packages
    //we keep smoke test cases in one class ( or one package)
    //So we use XML runners t create suite of tests and we already have xml runner for the smoke test. In my framework, we group test scripts based o n modules. So for every module we have a corresponding package with test scripts.
    //Usually we create separate classes for smoke classes, and store all the smoke test in one place to manage easily.

    //TestNG
    //testing framework : we can execute batch of test cases
    //text file : the test data is coming from excel spreadsheet
    //and pull the data from there (apache UI)
    //

    //regression with 15000 test cases ( vasyl's experience)
    //In computing, a log file is a file that records either events that occur in an operating system
    // or other software runs, or messages between different users of a communication software.
    // Logging is the act of keeping a log. ... Many operating systems, software frameworks and programs include a logging system


    //default order of execution is Alphabetic ( runs in order)
}
