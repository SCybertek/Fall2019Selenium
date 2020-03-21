package com.automation.tests.day5;

import com.automation.tests.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxTest {

    public static void main(String[] args) {


        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/checkboxes");
        //#TASK
        //verify that 1st checkbox is not selected and 2nd is selected

        BrowserUtils.wait(3);
        //add wait time since page is loading asn if internet is slow
        // Selenium has synchronisation problem
        // when the code can fail due to page not being able lto load and selenium running not being able to find the element
        //as a result test fails

          List<WebElement> checkBoxes = driver.findElements(By.tagName("input") );

          if ( !checkBoxes.get(0).isSelected() && checkBoxes.get(1).isSelected()) {
              System.out.println("TEST PASSED");
          } else {
              System.out.println("TEST FAILED");
          }


          //lets click on the first checkbox and verify it is clicked


        BrowserUtils.wait(2);

        checkBoxes.get(0).click();
        BrowserUtils.wait(2);
        //or
//        WebElement checkbox1 = checkBoxes.get(0);
//        checkbox1.click();

        if ( checkBoxes.get(0).isSelected()) {
            System.out.println("TEST PASSED");
            System.out.println("checkbox 1 is selected");
        } else {
            System.out.println("TEST FAILED");
            System.out.println("checkbox 1 is NOT selected");
        }
        BrowserUtils.wait(2);

          driver.quit();


    }
}
