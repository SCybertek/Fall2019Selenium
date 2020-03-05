package com.automation.tests.warmUp;

import com.automation.tests.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WarmUpTaskMarch4 {

    static WebDriver driver;

    //Go to ebay
    //enter search term
    //click on search button
    //print number of results
    //go to amazon
    //Go to ebay
    //enter search term
    //click on search button
    //verify title contains search term
    //Go to wikipedia.org
    //enter search term `selenium webdriver`
    //click on search button
    //click on search result `Selenium (software)`
    //verify url ends with `Selenium_(software)`

    public static void main(String[] args) throws Exception {




        ebayTest();
        amazonTest();

    }

    /**
     * Go to ebay -- driver.get("link)
     * enter
     */
    public static void ebayTest() throws Exception{


//        WebDriverManager.chromedriver().setup();
  //      WebDriver driver = new ChromeDriver();


        WebDriver driver = DriverFactory.createDriver("chrome");

        driver.get("http://ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("java book");
        driver.findElement(By.id("gh-btn")).click();
        WebElement searchResults = driver.findElement(By.tagName("h1"));

        String[] searchSentence = searchResults.getText().split(" ");
        System.out.println(Arrays.toString(searchSentence));

        System.out.println(searchSentence[0]);
        driver.quit();


        }


        public static void amazonTest() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            driver.get("https://www.amazon.com/");
            //below searching for input box (searching place) then enters the value and then enters enter
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java book", Keys.ENTER);
            String title = driver.getTitle();
            if (title.contains("java book")) {
                System.out.println("TEST PASSED");
            } else {
                System.out.println("TEST FAILED");
            }

            driver.quit();

    }

    /**
     * Go to wikipedia.org
     * enter search term `selenium webdriver`
     * click on search button
     * click on search result `Selenium (software)`
     * verify url ends with `Selenium_(software)`
     */
    public static void wikiTest() throws Exception{

        driver = DriverFactory.createDriver("chrome");
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        driver.findElement(By.id("searchInput")).sendKeys("selenium webdriver", Keys.ENTER);
        driver.findElement(By.partialLinkText("Selenium (software)")).click();

        Thread.sleep(2000);

        String link = driver.getCurrentUrl(); // to get link as a String

        if(link.endsWith("Selenium_(software)")){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
        }

        driver.quit();
    }
    }







