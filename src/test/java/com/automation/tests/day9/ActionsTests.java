package com.automation.tests.day9;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTests {

    private WebDriver driver;
    private Actions actions;
    // this is important !!

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver("chrome");
        actions = new Actions(driver);
    }

    @Test
    public void hoverOnImage() {
        driver.get("http://practice.cybertekschool.com/hovers");
        BrowserUtils.wait(3);

        ////img[1] it does not work like that
        //we added parenthesis for the locator to pint to 1 of the img
        WebElement img1 = driver.findElement(By.xpath("(//img)[1]")); //index starts with 1
        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
        WebElement img3 = driver.findElement(By.xpath("(//img)[3]"));


        actions.moveToElement(img1).
                pause(1000). //similar to thread sleep
                moveToElement(img2).
                pause(1000).moveToElement(img3).
                build().perform(); //we are chaining inhere => this is what is called builder pattern

        //moveToElement returns instance of action class that's why we can chain them
        //hover on the first image
        //verify that "name: user1" is displayed
        //hover over image to make text visible

        BrowserUtils.wait(2);
        actions.moveToElement(img1).perform();
        WebElement imgText1 = driver.findElement(By.xpath("//h5[text()='name: user1']"));
        //verify that webelement that contains the text is visible

        Assert.assertTrue(imgText1.isDisplayed());


        //move to the second image and verify if name: user2 is displayed
        BrowserUtils.wait(2);
        actions.moveToElement(img2).perform();
        WebElement imgText2 = driver.findElement(By.xpath("//h5[text()='name: user2']"));
        Assert.assertTrue(imgText2.isDisplayed());

        actions.contextClick(); // This is RIGHT CLICK
        //keyDown() ==> key press
        //keyUp()==> key release

        //we can do it separately

        //another way :
        //List<WebElement> images = new ArrayList<>(Arrays.asList(driver.findElement(By.xpath("(//img)[1]")),
        //        driver.findElement(By.xpath("(//img)[2]")),
        //        driver.findElement(By.xpath("(//img)[3]"))));
        //builder = new Actions(driver);
        //for(WebElement each : images){
        //    builder.moveToElement(each).pause(1000).build().perform();
        //}
        //BrowserUtils.wait(3);

        //build() is needed when you have couple of actions
        //always end with perform()
        //perform is not click : it is what we need to do with the action
        //we added pause just to show
        //builder pattern => you put one method then you can take action
        //builder pattern == chaining methods
        //what is build?
        //if you have multiple actions you have to put build
        //to combine a couple of actions.
        //build() is needed when you have couple of actions
        //build combines the action; perform; starts the action
        //in this example; first we move to one image then second so we used build
        //always end with perform
        //perform does not click, it starts the action, execute the event
        //you can perform click, drag and drop etc
        //actions class has different implementations
        //moveToElement returns instance of action class that's why we can chain them



    }

    @Test
    public void jqueryMenuTest(){
        driver.get("http://practice.cybertekschool.com/jqueryui/menu");
        //Task:
        //hover on enabled
        //hover on downloads
        //click on PDF

        WebElement enabled = driver.findElement(By.id("ui-id-3"));
        WebElement downloads = driver.findElement(By.id("ui-id-4"));
        WebElement pdf = driver.findElement(By.id("ui-id-5"));

        actions.moveToElement(enabled).//hovering over enabled
                pause(1000).
                moveToElement(downloads).//hovering over downloads
                pause(1000).
                click(pdf).build().perform(); //no need to hover over pdf! it is already visible

    }

        //Task : drag and drop
        @Test
        public void dragAndDrop(){
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        BrowserUtils.wait(3);

        WebElement earth = driver.findElement(By.id("droptarget"));
        WebElement moon = driver.findElement(By.id("draggable"));
            /**
             * A convenience method that performs click-and-hold at the location of the source element,
             * moves to the location of the target element, then releases the mouse.
             *
             * @param source element to emulate button down at.
             * @param target element to move to and release the mouse at.
             * @return A self reference.
             *
             */
            //dragAndDrop method explanation above

            //second way longer:
//            actions.clickAndHold(moon).
//                    pause(2000).moveToElement(earth).pause(1000).release().build().perform();

        actions.dragAndDrop(moon,earth).perform();



        String expected = "You did great!";
        String actual = earth.getText();
        Assert.assertEquals(actual,expected);

        }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
//Selenium can sometimes execute JavaScript code
//every single website support java script! (HTML/CSS and JS)
//in selenium web  driver there is JavaScriptExecutor interface
//we can click on elements/ we can write functions(methods) very useful javascript will be scrolling
//only 1 line of code , because JS is UI developer and it has everything
//you can work around with Java as well = for alternative solution
// click () has problems and we have to substitute that method with JS event()
//When do we use JS Executor ?
//when selenium methods doesn't work !! use JS method (JavaScriptExecutor's method)
//Visual Studio code : IDE for JS


