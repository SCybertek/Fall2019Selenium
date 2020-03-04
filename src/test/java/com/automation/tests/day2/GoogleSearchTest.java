package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {
    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://google.com");
        //1.manually look at the source code : in our case it is google

        // choose locator
        Thread.sleep(2000);

        //By.name"q" --> name "q"
        //name = q aprears one on this page and attribute- is name in here
        //it is unique
        //name - it sone of the seleniu locators
        //ther are 8 locators
        //we use them to find elements

        //2. to choose locator , just use By.locatorNAME

        WebElement search = driver.findElement(By.name("q"));
        //WebElement is interface
        //driver=>findElement (By. this locator ()

        //found by name parameter
        //once we found an element how to enter text ?
        // to enter use sendKeys() method
        //how to press enter after entering text?
        //3. use below:
        //KEYS.ENTER - perform keyboard click
        search.sendKeys("Java", Keys.ENTER);

        Thread.sleep(3000);
        driver.quit();

    }
}
