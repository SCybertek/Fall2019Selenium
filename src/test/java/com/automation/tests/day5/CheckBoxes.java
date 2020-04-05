package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxes {

    public static void main(String[] args) {


        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/checkboxes");

        BrowserUtils.wait(2);

        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        //no loop needed since we have only 2 elements


        // checkBox.get(0).click(); //click on first checkBox

        BrowserUtils.wait(2);

        //go over collection of checkboxes
        for(int i=0; i<checkBoxes.size(); i++) {
            //       if visible,                            eligible to click  and         not clicked yet
            if (checkBoxes.get(i).isDisplayed() && checkBoxes.get(i).isEnabled() && (!checkBoxes.get(i).isSelected())) {
                //if checkbox is not selected, click on it
                checkBoxes.get(i).click(); // click on the checkbox
                System.out.println(i+1 +" checkbox clicked!");
            } else{
                System.out.println(i+1 +" checkbox wasn't clicked!");
            }
        }
//        checkBox.get(1).click();
//
//        if ( checkBox.get(1).isDisplayed() && checkBox.get(1).isEnabled() && (!checkBox.get(1).isSelected())){
//            checkBox.get(1).click();
//        }

        BrowserUtils.wait(2);



        //2 ways to find these checkboxes
        //[type="checkbox"]
        //input


       driver.quit();
       //this red output if we have NO VERSION:

       //[main] INFO io.github.bonigarcia.wdm.WebDriverManager - Using chromedriver 80.0.3987.106 (since Google Chrome 80 is installed in your machine)
        //[main] INFO io.github.bonigarcia.wdm.WebDriverManager - Exporting webdriver.chrome.driver as /Users/beglight/.m2/repository/webdriver/chromedriver/mac64/80.0.3987.106/chromedriver
        //Starting ChromeDriver 80.0.3987.106 (f68069574609230cf9b635cd784cfb1bf81bb53a-refs/branch-heads/3987@{#882}) on port 30316
        //Only local connections are allowed.
        //Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
        //Mar 07, 2020 1:13:27 PM org.openqa.selenium.remote.ProtocolHandshake createSession
        //INFO: Detected dialect: W3C
        //[1583604807.920][SEVERE]: Timed out receiving message from renderer: 0.100
        //[1583604808.022][SEVERE]: Timed out receiving message from renderer: 0.100
        //[1583604808.123][SEVERE]: Timed out receiving message from renderer: 0.100
        //[1583604808.225][SEVERE]: Timed out receiving message from renderer: 0.100
        //[1583604808.326][SEVERE]: Timed out receiving message from renderer: 0.100
        //[1583604808.428][SEVERE]: Timed out receiving message from renderer: 0.100
    }
}
