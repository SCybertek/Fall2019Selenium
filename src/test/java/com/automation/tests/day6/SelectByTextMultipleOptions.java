package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByTextMultipleOptions {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        Select languagesSelect = new Select( driver.findElement(By.name("Languages")));
        //Whether this select element supporting multiple selection or not
        // this is not by cheking the value of the "multple" attribute

        boolean isMultiple = languagesSelect.isMultiple();
        System.out.println(isMultiple); //should return true

        //now lets select couple:
        languagesSelect.selectByVisibleText("Java");
        languagesSelect.selectByVisibleText("Python");
        languagesSelect.selectByVisibleText("JavaScript");

        //how to verify ? if they were selected?
        //get all selected options!!!

        List<WebElement> selectedLanguages = languagesSelect.getAllSelectedOptions();

        for ( WebElement eachSelected : selectedLanguages) {
            System.out.println(eachSelected.getText()); // to print text of selected webelements
            //System.out.println(eachSelected);//what will this print?
            //[[[[ChromeDriver: chrome on MAC (ea3c45cda1ca071582b48b4f8623402e)] -> name: Languages]] -> tag name: option]
            //this is WebElement to string I guess
        }

        BrowserUtils.wait(3);

        //you can de-select elements!:

        languagesSelect.deselectByVisibleText("Java");
        BrowserUtils.wait(3);

        languagesSelect.deselectAll(); //removes all selected !!



        BrowserUtils.wait(3);
        driver.quit();
    }
}
