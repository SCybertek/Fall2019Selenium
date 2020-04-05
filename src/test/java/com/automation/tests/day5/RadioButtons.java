package com.automation.tests.day5;

import java.util.*;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButtons {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().version("80").setup();
        WebDriver driver = new ChromeDriver();


        driver.get("http://practice.cybertekschool.com/radio_buttons");
        driver.manage().window().maximize();

        BrowserUtils.wait(2);

        //lets find all the buttons:
        //we searched by tag name INPUT

        //to collect all of them:

        List<WebElement> radioButtons = driver.findElements(By.tagName("input"));

        //since we do not have ANY other tag inputs we can use it
        //if there were more tag names with input we would have used Xpass or something else

        for (WebElement eachRadio : radioButtons) {


            //we cannot use Labels to get the element in here because it is little more complicated for now

            //we are introducing a new variable to reduce code repetition :
            String id = eachRadio.getAttribute("id");

            //this method : isSelected
            // to determine if button is already clicked
            boolean isSelected = eachRadio.isSelected();

            System.out.println(id + " is selected -" + isSelected);
            //be default on this page only 1 button will be selected since we are checking before our click method

            //to check if button is clickable  :
            if (eachRadio.isEnabled()) { //is enable returns Boolean
                //we can print ID next to the click action to understand what was clicked and what was not
                System.out.println("Clicked on: " + id);
                eachRadio.click(); //clicking on each button
                BrowserUtils.wait(1); //our thread.sleep method is called
            } else {
                System.out.println("Button is disabled, not clicked: " + id);
            }
        }

        driver.quit();




    }
}
