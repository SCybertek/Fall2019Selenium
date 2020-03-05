package com.automation.tests.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

    /**
     * This method return WebDriver object based on browser type
     * If you want to use chrome browser , just provide chrome as a parameter
     * @param browserName
     * @return Webdriver object
     *
     *
     * HOMEWORK: improve this method!
     */
    public static WebDriver createDriver (String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            //we added this version to get rid of Timed out recieving message
            WebDriverManager.chromedriver().version("79.0").setup();
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.chromedriver().setup();
            return new FirefoxDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new InternetExplorerDriver();
        }
    }
}
