package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForiFrame {

    //purple is a tag
    //black is a text
    //brown/orange is an attribute
    //blue is value of attribute

    public static void main(String[] args) {


        WebDriverManager.chromedriver().version("79.0").setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/iframe");

        BrowserUtils.wait(4);

        //before looking for that element we need to jump into the frame
        //for this we hav e a method .swithTo().frame(ID) //if not index or webElement can be used
        //you can specify name,id, index or webElement of the frame
        //MOST frames will have ID

        driver.switchTo().frame("mce_0_ifr");

        //now , this content will be visible
        WebElement iframeTextInput = driver.findElement(By.id("tinymce"));

        System.out.println(iframeTextInput.getText()); //to get what is text inside the element on the page
//we got NoSuchElementException: WHY?? because of iframe
//we have to switch to another frame first!

        //also, FRAME is not pop up !! it is something different


        BrowserUtils.wait(4);
        //now lest enter there different text
        //first stem is to delete the text

        iframeTextInput.clear(); //deleting content
        iframeTextInput.sendKeys("HELLO, WORLD"); //adding new one for display


        driver.switchTo().defaultContent(); //--> default content is QUITTING FROM ALL FRAMES
        // switching back to the orginal frame
        //no special need for swithcing back to orginal frame ..but he showed us that it is possible
        //you will stay in the closet..since we need to get out of here we HAVE to have it in here now!!

        BrowserUtils.wait(2);

//NoSuchElementException : if we are not quitting from the frame then the code cannot find H3
// we have to switch


        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println(heading.getText());

        driver.quit();
        //there could ne nested frames a well : it is like:

        //List<List<String>>


    }
}
