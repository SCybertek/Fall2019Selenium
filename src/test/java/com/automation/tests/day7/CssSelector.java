package com.automation.tests.day7;

import com.automation.tests.utilities.BrowserUtils;
import com.automation.tests.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CssSelector {


    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");

        WebElement heading = driver.findElement(By.cssSelector(".h3"));

        //. ==> means className in css selector syntax

        WebElement home = driver.findElement(By.cssSelector(".nav-link"));
        //the class is in orange color
        //this element represent <a ==> which will take you to home

        WebElement button1 = driver.findElement(By.cssSelector("[onclick='button1()']")); //we put these inside search place in inspect
        //how to check if value and attribute is unique ? we were putting in [] and it was cssSelector
        //we can skip tagName completely : button[onclick='button1()'"]
        button1.click();

        WebElement button2 = driver.findElement(By.cssSelector("[onclick='button2()']"));
        //this is searching by name using css
        button2.click();

        WebElement button3 = driver.findElement(By.cssSelector("[id^='button_'"));
        // ^ ==> means starts with ( only half of input is enough)
        button3.click();

        WebElement button4 = driver.findElement(By.cssSelector("[onclick='button4()']"));
        button4.click();
        //$ ==> ends with in css
        //* ==> contains ( you have to put whole sentence) ???

        WebElement button5 = driver.findElement(By.cssSelector("[onclick='button5()']"));
        button5.click();

        WebElement button6 = driver.findElement(By.cssSelector("#disappearing_button"));
        button6.click();


        BrowserUtils.wait(2);
        driver.quit();



        //css = is faster :
        //when you don't require back and forth navigation(parent to child or visa versa) or text requirements  (find element by TEXT) use SCC!!!
        // in CSS = . - class name
        //          # - id
        //          ^ - starts with (tagName[attribute^='value'] ) it is like contains in xpath
        //          $ - ends with (tagName[attribute$='_button'] ) XPATH does not support this!!

        //tagName[attribute='value']  == > no @ or // like in xPath
        // also we have contains
        // button[name='button2']

        //CssSelector: button[class="btn btn-primary"]:nth-of-type(2)
        //xpath:     //button[@class="btn btn-primary"][2]
    }
}
