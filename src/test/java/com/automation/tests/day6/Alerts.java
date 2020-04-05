package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Alerts {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        BrowserUtils.wait(3);

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        //there are 3 buttons
        //now lest click the first one :

        buttons.get(0).click();
        BrowserUtils.wait(3);

        //alert pops up :

        String popUpText= driver.switchTo().alert().getText(); //will return Pop up text
        System.out.println(popUpText);

        driver.switchTo().alert().accept(); // to click OK

        String expected = "You successfully clicked an alert";
        String actual = driver.findElement(By.id("result")).getText();
//this test will fail since there is a typo
        if (expected.equals(actual)){
            System.out.println("Test Passed");
        } else {
            System.out.println("test Failed");
            System.out.println("Expected : " + expected);
            System.out.println("Actual : " + actual);
        }

//clicking on second button

        buttons.get(1).click();
        BrowserUtils.wait(3);

        String popUp2 = driver.switchTo().alert().getText();
        System.out.println(popUp2);

        driver.switchTo().alert().dismiss();//to click cancel

        String expected2 = "You clicked: Cancel";
        String actual2 = driver.findElement(By.id("result")).getText();

        if (expected2.equals(actual2)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
            System.out.println("actual2 = " + actual2);
            System.out.println("expected2 = " + expected2);
        }

        //button 3
        //TASK:
        //Enter some text : Hello, World!
        //verify that result text ends with Hello, World!

        BrowserUtils.wait(3);

        buttons.get(2).click();

       // System.out.println(driver.switchTo().alert().getText()); // will print

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Hello, World!");
        buttons.get(1).click();
        alert.accept(); //clicking OK !!!

        String actual3 = driver.findElement(By.id("result")).getText();
        String expected3 = "Hello, World!";

        if (actual3.equals(expected3)){
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
            System.out.println("expected3 = " + expected3);
            System.out.println("actual3 = " + actual3);
        }


        BrowserUtils.wait(3);
        driver.quit();
    }

    //Alert is class in java that helps with POP up messages
    // not ALL pop up messages are ALERT !
    //sometimes it is system generated - selenium canot handle
    //or it can be another Window in HTML


    //ALERT:
    //messages with one button (OK)
    //with two buttons (OK and CANCEL)
    //with buttons and TEXT entry

    //you cannot inspect them!!
    //to handle them :
    //we have to switch to alert !!
}






