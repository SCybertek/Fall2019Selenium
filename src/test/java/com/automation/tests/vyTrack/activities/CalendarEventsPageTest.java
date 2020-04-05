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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 * Scenario: Verify for store manager
 *
 * Login as story manager
 * Go to Activities --> Calendar Events
 * Verify that Create Calendar Event button is displayed
 */

public class CalendarEventsPageTest {

    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private WebDriver driver;
    private Actions actions;

    private String storeManagerUserName="storemanager85";
    private String storeManagerPassword="UserUser123";
    private By activitiesBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Activities')]");
    private By createCalendarEventBtnBy = By.cssSelector("a[title='Create Calendar event']");


    private By currentUserBy = By.cssSelector("#user-menu > a");
    private By ownerBy = By.className("select2-chosen"); //now we are using classname , which would be more specific probably
           // By.id("s2id_oro_calendar_event_form_calendar");
    //we are using the ID of grandparent in here

    //how to fix space ? trim()
    private By titleBy = By.cssSelector("[name='oro_calendar_event_form[title]']");
    //There is not TagName in our css selector! you can do that
    //input boxes only have value NO TEXT ==> so getAttribute("value").isEmpty
    private By startDateBy = By.cssSelector("[id*='date_selector_oro_calendar_event_form_start-uid']");
    //* => means contains in css selector
    private By startTimeBy = By.cssSelector("[id*='time_selector_oro_calendar_event_form_start-uid']");
   // private

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();

        actions = new Actions(driver);

        BrowserUtils.wait(3);

        driver.findElement(usernameBy).sendKeys(storeManagerUserName);
        driver.findElement(passwordBy).sendKeys(storeManagerPassword, Keys.ENTER);

        BrowserUtils.wait(5);

        //hover over Activities
        actions.moveToElement(driver.findElement(activitiesBy)).perform();

        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Calendar Events")).click();

        BrowserUtils.wait(5);
    }

    @Test
    public void verifyCreateButton(){
        WebElement createCalendarEventBtn = driver.findElement(createCalendarEventBtnBy);
        Assert.assertTrue(createCalendarEventBtn.isDisplayed());
    }

    /**
     * //in the @BeforeMethod
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     *
     *
     * Click on Create Calendar Event
     * Default owner name should be current user
     * Default title should be blank
     * Default start date should be current date
     * Default start time should be current time
     */
    @Test(description = "Default options")
    public void verifyDefaultValues(){
        //Click on Create Calendar Event
        driver.findElement(createCalendarEventBtnBy).click();
        BrowserUtils.wait(4);
        //Default owner name should be current user
        String currentUserName = driver.findElement(currentUserBy).getText().trim();
        String defaultOwnerName = driver.findElement(ownerBy).getText().trim();
        Assert.assertEquals(currentUserName, defaultOwnerName);
//        Default title should be blank
        WebElement titleElement = driver.findElement(titleBy);
        Assert.assertTrue(titleElement.getAttribute("value").isEmpty());
        // Default start date should be current date
        //dare time syntax : https://www.journaldev.com/17899/java-simpledateformat-java-date-format
        //LocalDate.now() ==> returns date right now
        String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")); //the pattern should be specified !
        String actualDate = driver.findElement(startDateBy).getAttribute("value");
        Assert.assertEquals(expectedDate,actualDate);

        //String expectedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("h:m a")); //refer to website for the pattern
        String expectedTime = LocalTime.now(ZoneId.of("GMT-7")).format(DateTimeFormatter.ofPattern("h:m a"));
        String actualTime = driver.findElement(startTimeBy).getAttribute("value");
        Assert.assertEquals(expectedTime,actualTime);


    }
    //TEST FAILS : this is a bug
//    java.lang.AssertionError: expected [6:48 PM] but found [9:48 PM]
//    Expected :6:48 PM
//    Actual   :9:48 PM
//    <Click to see difference>


    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
