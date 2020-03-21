package com.automation.tests.vyTrack.fleet;

import com.automation.tests.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VehiclesTests {

    //create Before and After method with teardown part (like in LoginInPage class)
    //create a test called verifyPageSubTitle (All Cars )
    //we need to verify that subtitle is equal to "All Cars"
    //use assertion for validation


    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";

    private String username = "storemanager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
    //xpath allows to specify multiple search criteria in this locator
    // we are looking for element that has a class name and contains text
    private By subtitleBy = By.className("oro-subtitle");

    @Test
    public void verifyPageSubtitle() {


        //click on fleet
       // driver.findElement(fleetBy).click();
//Actions class is used for more advanced browser interactions
        //move to element instead of click
       // Actions actions = new Actions(driver);
        //actions to hover over to do drag and drop
        //to do right click
        //move to coordinate
        //to chain methods!

       // actions.moveToElement(driver.findElement(fleetBy)).perform();
        //perform to execute commands / perform is CLICK run button
        //every action SHOULD end with perform

      //  BrowserUtils.wait(3);
        //click on vehicles
       // driver.findElement(By.linkText("Vehicles")).click();
        //put more wait time if you are getting Cars, Dashboard...
        //this application is slooooow...
       // BrowserUtils.wait(3);
        String actual = driver.findElement(subtitleBy).getText();

        System.out.println(actual);

        String expected = "All Cars";
        Assert.assertEquals(actual,expected);


    }
//
// *     ################ TASK 5 minutes
// *
//         *     Given user is on the vytrack landing page
// *     When user logs on as a store manager
// *     Then user navigates to Fleet --> Vehicles
// *     And user verifies that page number is equals to "1"
    @Test
    public void verifyPageNumber(){

                                                    //input[type='number']
        String actual = driver.findElement(By.xpath("//label[text()='Page:']/..//input")).getAttribute("value");
        //get text does not work!! since it is attribute
        //input boxes does not have a text they have VALUE!!!
        String expected = "1";
        Assert.assertEquals(actual,expected,"Not the same number displayed");
    }


    @BeforeMethod

    public void setUp() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(3);
        //we need wait since every time selenium script is running it is like opening the page for thr first time
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtils.wait(3);
    }

    @AfterMethod

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
