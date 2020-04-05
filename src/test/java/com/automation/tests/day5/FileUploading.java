package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploading {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/upload");

        BrowserUtils.wait(5);
//choose file button : file-upload
        WebElement upload = driver.findElement(By.id("file-upload"));
        //path of file we want to upload :

        ////https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

        String filePath = System.getProperty("user.dir" ) + "/pom.xml";

        //user.dir = returns : /Users/beglight/IdeaProjects/Fall2019Selenium
        //user.dir comes from system class!

        String filePathPicture = "/Users/beglight/Downloads/6_Only Java/Selenium-Cheat-Sheet.jpg";

        //System.out.println(filePath);

        upload.sendKeys(filePathPicture);

        driver.findElement(By.id("file-submit")).click();

        //file from cloud did not work


        BrowserUtils.wait(3);

        driver.quit();

    }
}
