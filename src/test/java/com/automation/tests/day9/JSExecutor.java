package com.automation.tests.day9;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor {

    private RemoteWebDriver driver; //this is a class
    //WebDriver is interface

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
        driver.manage().window().maximize();

       // driver.executeScript("window.scrollBy(0, 250)");
        // .executeScript(): method that takes JavaScript code to execute
        //scrollBy(0 ==> left or right : 250 ==> for scrolling down )
        //x, y coordinates


        BrowserUtils.wait(2);

        //we can loop it to make it more visible

        for ( int i = 0; i < 10 ; i++) {
            driver.executeScript("window.scrollBy(0, 250)");
            BrowserUtils.wait(3);
            //the purpose of scrolling down => is to make the web element intractable/make it visible
        }


        // we use WebDriver as reference type usually because
        // JS has lots of methods that we don't need


    }

    @Test
    public void scrollToElementTest(){
        //scrolling down until element is visible !! to certain element :
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        //argument[0] ==> means the elemnet : link
        //if we are to have more than one element then it will go like argument[1] etc.
        // scrollIntoView = > javascript method
        // .scrollIntoView(true) => scroll down until the element is in the VIEW! (visible and interactable)
        driver.executeScript("arguments[0].scrollIntoView(true)", link);
        //here passing script as a String to execute it



    }
}
