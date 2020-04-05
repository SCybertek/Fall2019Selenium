package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImplicitWait {

    /**
     * driver.manage().timeouts().implicitlyWait(time, time units);
     * driver.manage().timeouts().implicitlyWait(20, seconds);
     *
     * specified once in the setter and webdriver will be waiting for the element fro 20 seconds
     */


    private WebDriver driver ;

    @BeforeMethod
    private void setup(){
        driver = DriverFactory.createDriver("chrome");
        //element is not visible since the page need to load first
        driver.get("http://practice.cybertekschool.com/dynamic_loading/2");
        //this below is how we apply implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void waitTest(){
        driver.findElement(By.tagName("button")).click();
        WebElement finish = driver.findElement(By.id("finish"));
        System.out.println(finish.getText());

    }


    //NOTES:
    /**
     * Thread. sleep is stopping thread from running / from executing
     * we put manually pause in selenium using Threads.sleep()
     * disadvantage : you do not know how many seconds your script needs to wait!
     * it is not really accurate
     * we have to balance speed and achieve maximum performance and maximum sustainability/consistency
     * script has to run smoothly NOT fail!
     * Thread.sleep is easiest solution to give webpage time to get ready for the script
     * the page has to load and make all elements interactable and visible => or NoSuchElementException
     * WARNING: Threads.sleep is NOT SELENIUM method
     *
     * IMPLICIT and EXPLICIT wait and Fluent wait => from SELENIUM
     * this is a time range !
     * until condition is met the wait will happen
     * if condition is met BEFORE the time range specified the execution continues
     *
     * IMPLICIT : apply only once at the beginning of task execution. works before .findElement() automatically
     * if element is not found => NoSuchElementException . default implicit wait: 0
     * used once in your Before Method
     * ONLY 1 criteria/condition : if element is present in the DOM
     * EXPLICIT :
     * !conditional wait
     * Specific condition for specific webElement
     *
     * 1. An element not being present at all in the DOM.
     * 2. An element being present in the DOM but not visible.
     * 3. An element being present in the DOM but not enabled. (i.e. clickable)
     * alert presence/ attribute to be etc.
     *
     * polling time = 500 ms
     * you can use it many time and all over the test
     *
     * do not use IMPLICIT and EXPLICIT wait together
     * DOM: Document Object Model : a tree structure of HTML. Every element is a NODE
     *
     *
     * FLUENT WAIT :
     * it is custom explicit wait
     * with fluent wait we can define the max amount of wait time + condition and element as well as Frequency
     * with which to check for the condition (polling time = frequency )
     * here we can change polling time
     *
     */

}
