package com.automation.tests.day7;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class XPath {
// make this locator Public and use it in ALL classes        text()='Username' and text()='Password'
    static String userNameLocator = "//label[@for='username']/following-sibling::input";
    static String passwordLocator = "//label[@for='password']/following-sibling::input";
    static String button = "//button[contains(text(),'Login')]";

    public static void main(String[] args) {


        WebDriver driver = DriverFactory.createDriver("chrome");

        driver.get("http://practice.cybertekschool.com/login");
        BrowserUtils.wait(3);

        driver.findElement(By.xpath(userNameLocator)).sendKeys("tomsmith");
        driver.findElement(By.xpath(passwordLocator)).sendKeys("SuperSecretPassword");


        //driver.findElement(By.xpath("//button[text()='Login']")).click();
        //or contains as well in order to use partial match
        driver.findElement(By.xpath(button)).click();


        BrowserUtils.wait(3);
        driver.quit();
    }

    //To have both attributes
    ////button[@type='submit' and @id='wooden_spoon']
    //To have either one or another attribute
    ////button[@type='submit' or @id='wooden_spoon']
}
