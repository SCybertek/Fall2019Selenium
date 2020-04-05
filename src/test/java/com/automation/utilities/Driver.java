package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//this is Singelton class
public class Driver {
    //same for everyone
    private static WebDriver driver;
    //So no one can create object of Driver Class
    //everyone should call static getter instead
    private Driver(){

    }

    public static WebDriver getDriver(){
       //if webDriver object does not exist ==> create it
        //we need to instantiate the driver

        //webdriver manager is not trusted everywhere ,
        // like government company
        if (driver== null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //customize browser => incognito window / headless mode/ maximize window/ disable features of the browser/ solve SSL issues
                    chromeOptions.addArguments("--start-maximized"); //alternative approach to maximize window ( when maximize method from selenium does not work)
                    driver = new ChromeDriver(chromeOptions); //passing inside our driver as a parameter
                    break;
                case "chromeheadless": //meaning browser is running in the background without Interface
                    //every browser name should be lowercase
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;
    }



    public static void closeDriver(){
        if (driver != null) {
            driver.quit(); //driver closed
            driver = null; //driver destroyed
        }
    }
}
