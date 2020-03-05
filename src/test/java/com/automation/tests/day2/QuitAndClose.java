package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class QuitAndClose {

    public static void main(String[] args) throws Exception{
        //quit will close every window (every tab)
        //close will close only one particular one
        //selenium works with multiple windows ( it can jump between windows)

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");

        Thread.sleep(4000);

        //driver.close(); //expected to close the original one
        driver.quit(); //all window will be closed




    }
}
