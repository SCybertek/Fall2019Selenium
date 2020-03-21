package com.automation.tests.practice;


import com.automation.tests.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.print.DocFlavor;

public class RegistrationForm {
//# --> refers to ID
//..--> refers to going up to the parent

    private WebDriver driver;
    private String URL = "http://practice.cybertekschool.com/registration_form";
    //p tag name of success message

    //private String  firstNameLocator = "firstname"; //by name
//lets have it more specific
    private By firstNameBy = By.name("firstname"); //Bellow is for one shot solution so to speak:
    private By lastNameBy = By.name("lastname"); //xpath : //label[text()='Last name']/..//input
    private By usernameBy = By.name("username"); //xpath: //label[text()='User name']/..//input
    private By emailAddressBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneNumberBY = By.name("phone");
    //gender
    private By maleBy = By.cssSelector("input[value='male']");
    private By femaleBy = By.cssSelector("input[value='female']"); //index is just the position (can be shuffled)/ so we use value
    private By otherBy = By.cssSelector("input[value='other']");

    private By dateOfBirthBy = By.name("birthday");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");

    //languages
    private By cplusplusBy = By.xpath("//label[text()='C++']/preceding-sibling::input");
    private By javaBy = By.xpath("//label[text()='Java']/preceding-sibling::input");
    private By javascriptBy = By.xpath("//label[text()='JavaScript']/preceding-sibling::input");

    //!!!dynamic elements can be handles by finding static siblings

    //button
    private By signUp = By.id("wooden_spoon");

    //better not to have auto import class because many classes from dif.library have the same name but different purpose

    @Test
    public void test1() {

        driver.findElement(firstNameBy).sendKeys("Patrick");
        driver.findElement(lastNameBy).sendKeys("white");
        driver.findElement(usernameBy).sendKeys("testuser");
        driver.findElement(emailAddressBy).sendKeys("test@gmail.com");
        driver.findElement(passwordBy).sendKeys("12345678");
        driver.findElement(phoneNumberBY).sendKeys("123-234-4566");
        driver.findElement(maleBy).click(); //we have to click to select this button

        driver.findElement(dateOfBirthBy).sendKeys("01/02/1980");

        //select class:
        //some drop downs are select base
        Select departmentSelect = new Select(driver.findElement(departmentBy));
        departmentSelect.selectByVisibleText("Department of Agriculture"); //now we are selecting
        //there are values as well - however they are invisible from user

        Select jobTitleSelect = new Select(driver.findElement(jobTitleBy));
        jobTitleSelect.selectByVisibleText("SDET");

        //programming language
        driver.findElement(javaBy).click();
        //signing up button
        driver.findElement(signUp).click();

        BrowserUtils.wait(5);
        String expected = "You've successfully completed registration!" ; // this comes from test cases / or you can create from AC
        String actual = driver.findElement(By.tagName("p")).getText();
        Assert.assertEquals(actual,expected);

    }
    @Test
    public void verifyNameLengthTest(){
        driver.findElement(firstNameBy).sendKeys("a");
        //if element is present : if there is an element in the list ( if null then not present )

        BrowserUtils.wait(3);
        WebElement warningMessage = driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(warningMessage.isDisplayed());
        //if element is visible : it will return true if the element is VISIBLE!!
        // NOT ONLY If it is present in HTML document

    }

    @Test
    public void verifyAlphabeticLettersOnlyTest(){
        driver.findElement(firstNameBy).sendKeys("1");

        BrowserUtils.wait(3);
        WebElement waringMessage = driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(waringMessage.isDisplayed()); //verifying if it is displayed
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


