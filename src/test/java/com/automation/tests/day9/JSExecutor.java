package com.automation.tests.day9;

import com.automation.tests.utilities.BrowserUtils;
import com.automation.tests.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor {

    private RemoteWebDriver driver;

    //Why do we need JavaScriptExecutor?
    //In Selenium Webdriver, locators like XPath, CSS, etc.
    // are used to identify and perform operations on a web page.
    //In case, these locators do not work you can use JavaScriptExecutor.
    // You can use JavaScriptExecutor to perform an desired operation on a web element.

    //Notes from seda:
    //how to use javaScriptExecutor?
//javaScriptExecutor; it is an interface we can not create object out of it.
//But javascript executor and webDriver are like siblings
//So we will cast driver to JavascriptExecutor
//we convert webDriver object into JavaScriptExecutor
//JavascriptExecutor js = (JavascriptExecutor) driver;
//interface => they don't have implementation
//if you have interface as reference type you can see methods only coming from that interface
//you can not see other methods that are in other interfaces
//so we will use remoteWebDriver class as reference type :
//if you use remoteWebDriver class as reference type you do not need to cast anymore, it has everything
//like this => private RemoteWebDriver driver;
    //driver.executeScript("window.scrollBy(0, 250)");
//you need to cast if your reference type is webDriver; like this =>  private WebDriver driver;
    //JavascriptExecutor js = (JavascriptExecutor) driver;


    @BeforeMethod
    public void setUp(){
     //   driver = DriverFactory.createDriver("chrome");
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();

    }

    @AfterMethod
    public void tearDown(){
        BrowserUtils.wait(3);
        driver.quit();
    }

    @Test
    public void scrollTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
        //JavaScriptExecutor is an Interface !! No object can be created
        //But WeBDriver and JSExectutor like sibling, pretty much and we can cast
        //JavascriptException js = (JavascriptException) driver;
        //it is an interface (no object can be created)
        // we are using Polymorphism and referring the driver object by its Super and
        //
        //RemoteWebdriver has ?? no casting needed any more

        //scroll down 250 pixel
        //x, y
        //for (int x = 0 ; x <10) !1 WE USED LOOP BUUT WHYY?? listen to the class again!! especially last 30 min!

        driver.executeScript("window.scrollBy(0, 250)");

        BrowserUtils.wait(3);



    }

    @Test
    public void scrollToElementTest(){
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        driver.executeScript("arguments[0].scrollIntoView(true)", link);
    }
}
