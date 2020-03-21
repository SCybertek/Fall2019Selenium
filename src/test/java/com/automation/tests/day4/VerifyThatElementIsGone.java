package com.automation.tests.day4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class VerifyThatElementIsGone {
    public static void main(String[] args) throws Exception {

        /**
         * How to verify if element does not exist any more
         * DOM (Document Objcet Model) = referring to the HTMl document
         */
        //element can be hidden : it is there but not visible
        //element is gone : when the code is removed form HTML

        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        driver.findElement(By.id("disappearing_button")).click();

        Thread.sleep(2000);
        //below is two step :
      //  List<WebElement> list = driver.findElements(By.id("disappearing_button") );
       // if (list.size() == 0)
        if (driver.findElements(By.id("disappearing_button")).size() == 0 ) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        Thread.sleep(2000);
        //find and inlcude all buttons in the list

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for ( WebElement eachButton : buttons) {
            eachButton.click();
            Thread.sleep(2000);
        }
        //no need to refresh since we are on the same main page all the time ( no navigation)

        driver.quit();

    }
}
