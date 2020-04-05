package com.automation.tests.officeHour;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class JavaScripExecutorReview {

    @Test (description = "Send text to search box on etsy")
    public void test1(){
        //driver instance is created
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("https://www.etsy.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //need to do so o allow JS to use driver we created
        js.executeScript("document.getElementById('global-enhancements-search-query').value='Hello World'");

                //we can pu all script itself

        driver.quit();
    }



}
