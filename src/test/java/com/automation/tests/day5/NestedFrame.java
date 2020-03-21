package com.automation.tests.day5;

import com.automation.tests.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrame {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().version("79.0").setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/nested_frames");

        BrowserUtils.wait(2);

        driver.switchTo().frame("frame-top");
        //switching to top frame

        driver.switchTo().frame("frame-left");
        //switching to left

        WebElement body = driver.findElement(By.tagName("body"));
        //since left frame does not have content then wit gives us NoSuchElementException
        //lets serach element by body
        System.out.println(body.getText());

        driver.switchTo().parentFrame(); // swtichung to top frame
        driver.switchTo().frame("frame-middle");
        BrowserUtils.wait(2);

//id="content" == you haveto search by id
        WebElement content = driver.findElement(By.id("content"));

        System.out.println(content.getText());

        //swtiching to default in order to go back to top frame

        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame-bottom");

//<body == you have to search by tagName
        WebElement body2 = driver.findElement(By.tagName("body") );

        System.out.println(body2.getText());


        BrowserUtils.wait(2);

        driver.quit();







    }
}
