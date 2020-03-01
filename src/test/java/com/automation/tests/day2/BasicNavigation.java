package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BasicNavigation {
    public static void main(String[] args) {
        //to start selenium script we need
        //setUp webdriver (browserdriver) and create webdriver object

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //in selenium everything starts from WebDriver interface

        //RemoteWebDriver driver2 = new SafariDriver();
        //this is a parent and we applied polymorphism

        //get
        //it is a key to open the door
        driver.get("http://google.com");


//WebDriver interface : Remore WEBDRIVER IS implementing class
        //Remote WEB DRIVER is parent class
        //Edge driver chrome driver firefox driver all siblings
        //interface WEBDRIVER has abstract method (since it is interface)
        //we uset .get () method that is implemented by Chrome Browser that we used.
        //we used to open the website

    }
}
