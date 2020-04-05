package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectByIndex {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        Select state = new Select(driver.findElement(By.id("state")) );

        state.selectByIndex(9);//position in the List -- not so accurate


        BrowserUtils.wait(3);
        //index starts from 0

        //select a state : in here is also an option and we are counting it as well


        state.selectByIndex(state.getOptions().size()-1);
        //getOption will return LIST of ALL WEBELEMENTS ..thats why we can use .size method


        BrowserUtils.wait(3);
        driver.quit();
    }
}
