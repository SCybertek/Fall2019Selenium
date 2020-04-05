package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWait {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");

    }


    @Test
    public void waitForTitle() {
        driver.get("http:google.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        //we have to specify which driver has to wait! inside the WebDriverWait object!

        //calling until() method and include ExpectedConditions class and use methods that you need
        //stale element => old element after we refresh the connection is lost => staleElementException
        //stalenessOff method should solve that but it did not

        //wait up to 10 seconds until title contains Google
        //.titleIs() => matching
        //.titleContains() => contains
        wait.until(ExpectedConditions.titleContains("Google"));
        //explicit wait for specific element! = have to specify condition + wait time + for which element
        //wait up to 10 seconds until title contains google
        //ExpectedConditions is a class , we use it for explicit wait;
        //ExpectedCondition is interface it's usage is different, we will use it in fluent wait
        wait.until(ExpectedConditions.titleContains("Google"));
        //when condition fails => we will get exception.
        //by default it will check every 500 milliseconds => means that it checked 20 times until find the element
        //When condition fails :
        //org.openqa.selenium.TimeoutException:
        // Expected condition failed: waiting for title to contain "Amazon".
        // Current title: "Google" (tried for 10 second(s) with 500 milliseconds interval)


        //WebDriverWait wait2 = new WebDriverWait(driver, 23);
        //worked with adding another wait object as well
        driver.navigate().to("https:amazon.com");
        //using the same WebDriverWait object in different condition
        wait.until(ExpectedConditions.titleContains("Amazon"));
    }


    @Test
    public void waitForVisibility(){
        driver.get("http://practice.cybertekschool.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.findElement(By.tagName("button")).click();

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submitB = driver.findElement(By.cssSelector("button[type='submit']"));
        //if there are 2 elements one visible and another with the same class name but invisible
        //the program will select the first one
        //thats why we better specify extra info about that element: button[type='submit']

        //visibility of username and password
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys("tomsmith");
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("SuperSecretPassword");
        wait.until(ExpectedConditions.elementToBeClickable(submitB)).click();

        //we can send keys right there because .until() method returns WebElement object


        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void elementToBeClickableTest(){
        driver.get("http://practice.cybertekschool.com/dynamic_loading/5");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.tagName("button"));

        //ElementClickInterceptedException => means something else was clicked instead of your element
        //LoadingOverlay is present ..we need to wait until it becomes invisible
        //if app has overlay screens this is a very common problem

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));
        //the POWER of explicit wait
        wait.until(ExpectedConditions.elementToBeClickable(button));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword");
        button.click();



        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();
        Assert.assertEquals(expected,actual);

    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }


}
