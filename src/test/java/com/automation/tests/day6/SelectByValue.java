package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectByValue {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        //value > used by computer
        //Visible Text > visible to user

        //lets find state by value:

        Select state = new Select(driver.findElement(By.id("state")) );

        state.selectByValue("DC"); //case-sensitive

        String expected = "District Of Columbia" ;
        String actual = state.getFirstSelectedOption().getText();
        //why getFirstSelectedOPtion() ==> because we already selected
        // sometime we select couple elements and in here we selected one and lets do .getFirstSelected()
        //if it was allowing multiple : then we can get all selcted and save them in the List

        //mostly we find by SELECT by VISIBLE text rather than value ( probably because we create test case according to end user)

        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }




        BrowserUtils.wait(3);
        driver.quit();
    }
}
