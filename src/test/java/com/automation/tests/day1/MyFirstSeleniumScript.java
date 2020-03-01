package com.automation.tests.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstSeleniumScript {
    public static void main(String[] args) {

        //setUp chrome Driver
        WebDriverManager.chromedriver().setup();
        //create chromeDriver object
        ChromeDriver driver = new ChromeDriver();
        //open some website
        driver.get("http://google.com");
        //Error:java: error: release version 5 not supported
        //our code is complaining to fix it we will insert something in dependencies
    }
}
