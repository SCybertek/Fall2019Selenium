package com.automation.tests.day4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FIndElementsTest {
    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().version("79").setup();

        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/");

        Thread.sleep(3000);


        //how to collect all links from the page ?
        // how to collect all links ?
        //every link has ???
          List<WebElement> links = driver.findElements(By.tagName("a"));

          for (WebElement eachLink : links) {
              //lets get names
              System.out.println(eachLink.getText());
              //lets also get attribute
              System.out.println(eachLink.getAttribute("href"));
              System.out.println();//for space
              //below gave staleElment Exception
//              eachLink.click(); //click on each link
//              Thread.sleep(2000);
//              driver.navigate().back(); //goes back to main page
          }


          //trying to fix the exception:

        //for loop :

        for (int i = 1; i < links.size(); i++) {
            links.get(i).click(); //starting from the second link..since first link is HOME button
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
            //refresh list
            driver.findElements(By.tagName("a"));
            //once you leave the page the element gets lost
            //we need to refresh to find elements again and again
        }
        driver.quit();

    }

    //StaleElementReferenceException :
    //means that Selenium cannot find previously located element
    //it happens when you are trying to interact with element after page refresh or navigation

    //to fix we nee dto refresh our collection!
}
