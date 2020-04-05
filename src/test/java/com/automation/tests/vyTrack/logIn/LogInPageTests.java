package com.automation.tests.vyTrack.logIn;

import com.automation.utilities.BrowserUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//static import for ALL ASSERTIONS:
import static org.testng.Assert.*;

public class LogInPageTests {

private WebDriver driver;
//https: is secure version of http protocol
    //http its hypertext transfer protocol that every single website is using nowadays
//https - data encrypted, no chance for hackers to retrieve info
//http - data as a plain text, very easy to hack it
private String URL = "https://qa2.vytrack.com/user/login";
//BY is a class

    //credentials for store manager :

    private String username = "storemanager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By warningMessageBy = By.cssSelector("[class='alert alert-error'] > div");

    //when we have space inside the class name :  alert : it can treat it as separate element : thats why we need css in here

//in scc : > means same thing as / in xpath - direct child
    // in CSS selector : you can move only one down from parent to child
    //we cannot go from child TO parent in CSS
    //to go to GRAND grandchild in CSS :
    //after css code put (SPACE) and grand grand child

    // in x path you can move to grandchild as well
    //we can go to parent too
    //





@Test (description = "verify that warning message displays when user enters invalid username")
public void invalidUsername(){//no need to make your method static to access static import !
    driver.findElement(usernameBy).sendKeys("invalidusername");
    driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
    BrowserUtils.wait(2);
    WebElement warningElement = driver.findElement(warningMessageBy);
    assertTrue(warningElement.isDisplayed());

    //if warning message is not visible test will fail
    String expected = "Invalid user name or password.";
    String actual = warningElement.getText();
    assertEquals(expected,actual);

    //warning message i an attribute : it is hidden ..but it exists in html
    ///*
    //html5 warning message attribute
    //this warning message can not be cached with locator
    //it some kind of attribute coming from html
    //you can not catch it, there is a method getAttribute
    //you can just read it
    // */

}

    @Test (description = "Login as store manager and verify that title is equals to Dashboard")
    public void loginAsStoreManager() {
    //to login : we already have the code from negative test above
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);

        BrowserUtils.wait(2);
        String expected = "Dashboard";
        String actual = driver.getTitle();
        assertEquals(expected,actual, "Title is different");
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        //if webdriver object alive
        if(driver !=null) {
            //close browser close session
            driver.quit();
            //destroy webdriver object for sure
            driver = null;
        }
    }
}
