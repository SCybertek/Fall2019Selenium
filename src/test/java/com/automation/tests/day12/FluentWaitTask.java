package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitTask {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");

    }


    @Test
    public void fluentWaitTest(){

        driver.get("http://practice.cybertekschool.com/dynamic_loading/6");
//10, TimeUnit.SECONDS = Duration.ofSeconds(10)
        //fluent object that supposed to wait for 10 seconds and check for condition every 3 seconds
        // ignoring Exceptions - specify
        //if exception specified happens it will ignore it and continue checking
        //other exceptions can come up and it will display it after failing the test

        Wait<WebDriver> wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(3)).
                ignoring(NoSuchElementException.class).
                ignoring(ElementClickInterceptedException.class);

        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));

         WebElement submitBtn = wait.until(new Function<WebDriver, WebElement>() {
             //function is anonymous class = without a name
             //it is also an interface ?
             // if we will be using interface method only once we can create an Anonymous class
             //and override the method from interface = ExpectedCondition ? I think apply method is coming from this guy :)

                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return driver.findElement(By.xpath("//button[text()='Submit']"));
                    }
                });

        //below is with Lambda Expression :
        //WebElement submitBtn = wait.until(webDriver -> driver.findElement(By.xpath("//button[text()='Submit']")));

        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");

        submitBtn.click();

    }
    @
    AfterMethod
    public void teardown() {
        driver.quit();
    }


}
