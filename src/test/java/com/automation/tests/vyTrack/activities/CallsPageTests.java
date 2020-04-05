package com.automation.tests.vyTrack.activities;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CallsPageTests {


    //we are organizing our packages according to our Modules ( components )

    //writing each test case steps above each line of code,
    // do you recommend or we need to have a clean code ?
    //for now . yes, it will be advisable ..later at job it is not mandatory
    //at work people use LOG => that is recording the info and prints

    /**
     * Scenario: Verify for store manager
     * Login as story manager
     * Go to Activities --> Calls
     * Verify that Log Call button is displayed
     * Go to Activities --> Calendar Events
     * Verify that Create Calendar Event button is displayed (if it is visible)
     */

    private WebDriver driver;
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private String username = "storemanager85";
    private String password = "UserUser123";
    private By activitiesBy = By.xpath("//span[@class='title title-level-1' and contains(text(), 'Activities')]");
    //all modules have the same Class name and we are searching oly Activities and that's why we combine these two

    private Actions actions;


    @Test
    public void logCallButtonTest(){
        WebElement logCallButton = driver.findElement(By.cssSelector("[title='Log call']")); // it is a link thats why we used title
        Assert.assertTrue(logCallButton.isDisplayed());

    }

    /**
     * TASK
     *
     * Scenario: Verify for store manager
     *
     * Login as story manager
     * Go to Activities --> Calendar Events
     * Verify that Create Calendar Event button is displayed
     */



    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        //webdriver object should be created before we include driver as a parameter inside Actions
        //without driver we cannot use Actions class!! or NullPointer exception will be in the console
        actions = new Actions(driver);

        BrowserUtils.wait(3); // we will not use thread.sleep at work


        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(5);

        //hover over the activities drop down
        actions.moveToElement(driver.findElement(activitiesBy)).perform();

        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Calls")).click(); //these elements are links!
        BrowserUtils.wait(2);


    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }


}
