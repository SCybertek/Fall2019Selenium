package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;



public class SearchTests {

    private WebDriver driver;


    @Test
    public void googleSearchTest(){
        driver.get("http://www.google.com/");
        //we are trying to verify that all search terms contains word Java when we search for Java
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        BrowserUtils.wait(2);

        //since every item has a tag name <h3>
        //it is the easiest way to collect all items
        List<WebElement> searchItems = driver.findElements(By.tagName("h3"));
        for (WebElement searchItem : searchItems) {
            String var = searchItem.getText();
            //if there is a text - print it
            //(Test ing and JUnit is only for Java!!)
            if (!var.isEmpty()){
                System.out.println(var);
                //verify that every search result contains java
                Assert.assertTrue(var.toLowerCase().contains("java"));
            }
            //System.out.println(searchItem.getText());
//            if (searchItem.getText().contains("Java")){
//
//            WITHOUT ASSERTION TEST IS NOT A TEST!!!

        }

    }

    /**
     * Given user is on the amazon.com page
     * When user enters "java" as a search item
     * Then user clicks on the search button
     * and user clicks on the first search item
     * and user verifies that title of the search item contains "Java"
     * (we will learn more about test cases when we will use Cucamber)
     */
    @Test (description = "Search for Java book on amazon")
    public void amazonSearchTest() {

        driver.get("http://amazon.com");
        //there is a chance that item is not visible

        driver.manage().window().maximize();
        //maximizing did not help


        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java", Keys.ENTER);
        BrowserUtils.wait(5);
        //it does not click
//find all links inside h2 elements because h2 element was not clickable itself
        List<WebElement> searchItems = driver.findElements(By.xpath("//h2//a"));

        //h2 elements are not clickable(for selenium), even though they contain links
        //that's why instead of collecting all h2 elements
        //we collected all hyperlinks
        //every hyperlink represent some search item
        for(WebElement each : searchItems) {
            System.out.println(each.getText());
        }

        searchItems.get(0).click();
        BrowserUtils.wait(5);

        WebElement productTitle = driver.findElement(By.id("title"));

        String productTitleString = productTitle.getText();
        System.out.println(productTitleString);

        Assert.assertTrue(productTitleString.contains("Java"));
    }

            @BeforeMethod //dry principle
    public void setUP() {
        //setting up WebDriver
                WebDriverManager.chromedriver().version("79.0").setup();
                driver = new ChromeDriver();
            }

            @AfterMethod
    public void tearDown() {
                driver.quit();
            }
}
