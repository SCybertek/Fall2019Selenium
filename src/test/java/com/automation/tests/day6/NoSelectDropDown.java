package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class NoSelectDropDown {
    public static void main(String[] args) {
        //this is popular question in Selenium interview: Who to handle drop downs

        //this dropdown should be clicked first in order to select!

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        //first find element of the button : DropDown LINk
        driver.findElement(By.id("dropdownMenuLink")).click();//expand dropdown
        //now you can see each option :


        //lets collect all links:
        //ever single link has dropdown class
        //how to get elements ( in our case it is links)
        //for class name : By.className
        //for href : getAttribute()

        List<WebElement> allLinks = driver.findElements(By.className("dropdown-item"));
        //every link has the same class name in this dropdown

        for ( WebElement link : allLinks) {
            System.out.println(link.getText() + " : " + link.getAttribute("href"));
            //getText== >Facebook (black ink)         .getAttribute ==> https://facebook.com
        }



        //every option is A link in here!
        //<a class ="dropdown-item"...
        driver.findElement(By.linkText("Amazon")).click();//click on option


        //click on the MENU -- to make it visible : then SELECT
        //or : use select class ( for select base drop down)
        // select based on : text / vale / index







        BrowserUtils.wait(3);
        driver.quit();

    }
}
