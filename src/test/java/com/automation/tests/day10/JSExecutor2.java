package com.automation.tests.day10;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class JSExecutor2 {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void verifyTitle(){
        String expected = "Practice";
        //we create javascriptExecutor object by casting webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript - this method executes javascript code
        //we provide js code as a string
        //return document.title - javascript code
        //document - represents HTML page
        //.toString() - avoid additional casting from Object to String
        String actual = js.executeScript("return document.title").toString();

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void clickTest(){
        WebElement link = driver.findElement(By.linkText("Multiple Buttons"));
        //link.click();
        //we can also do it with JSExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        BrowserUtils.wait(2);
        //after doeuble quotes you can list webelemnts that will be used as part of you javascript code
        //it is var args, so you can list 0 + elements
        js.executeScript("arguments[0].click()",link);
        //varArgs : they work as an array. we specify the index of the element
        //WebElement arguments => {element, link ,link2} ;
        //left to right
        //class name... args ==>  like array
        //lets us specify 0 or more webElements there is no limit
        BrowserUtils.wait(2);
        WebElement button6 = driver.findElement(By.id("disappearing_button"));
        js.executeScript("arguments[0].click()",button6);
        // js.executeScript("arguments[0].click()",button6,link);
        // you can keep adding elements but you click on only one element
        // to click on multiple elements at a time => deeper knowledge of JS syntax is required

        BrowserUtils.wait(2);
        WebElement result = driver.findElement(By.id("result"));

        Assert.assertEquals(result.getText(), "Now it's gone!");
    }


    //how to enter the text with JSExecutor
    //input box = > has value attribute
    // .setAttribute('value','tomsmith')

    @Test
    public void textInputTest(){
        driver.findElement(By.linkText("Form Authentication")).click();

        BrowserUtils.wait(2);
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //to get text from input box - read attribute "value"
        //to enter text - set attribute "value"
        //.setAttribute('value', 'text') - enter some text
        js.executeScript("arguments[0].setAttribute('value', 'tomsmith')" , username);
        js.executeScript("arguments[0].setAttribute('value', 'SuperSecretPassword')", password);
        js.executeScript("arguments[0].click()", loginbtn);

        //document.getElementsByName('username')[0].setAttribute('value','tomsmith')
        //undefined
        //document.getElementsByName('password')[0].setAttribute('value','supersecretpassword')


        //getting text on JS document.getElementsByClassName('subheader')[0].textContent

        String subHeader = js.executeScript("return document.getElementsByClassName('subheader')[0].textContent").toString();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(subHeader,expected);
        //ChromeDriver = provided by google
        //they provide API - access to browser
        //JS code: is not very efficient through it
        //why? using JS ?
        // USE it when it is needed!
        // browser version is updated and WebDriver is not updated it is all about versions ..
        // how well the driver is optimized

    }

    @Test
    public void scrollToElement(){
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true)",link);
    }

    @Test
    public void infiniteScrollTest(){
       //driver.findElement(By.linkText("Infinite Scroll")).click();
        driver.navigate().to("http://practice.cybertekschool.com/infinite_scroll");
       JavascriptExecutor js = (JavascriptExecutor) driver;
       for (int j = 0 ; j < 13 ; j ++ ) {
           js.executeScript("window.scrollBy(0,1000)");
           BrowserUtils.wait(1);
       }


    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
