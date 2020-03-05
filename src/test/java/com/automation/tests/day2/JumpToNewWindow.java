package com.automation.tests.day2;

import com.google.gson.internal.$Gson$Preconditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class JumpToNewWindow {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");


        Thread.sleep(5000);

        //every window has some ID and it is called WindowHandle
        //based on window handle we can switch in between Windows
        //this is creation of window handel
        String windowHandle = driver.getWindowHandle();
        //this method returns ID of window that is open right now

        System.out.println("windowHandle id of current window is = " + windowHandle);

        //get all window handles : MULTI
        Set<String > windowHandles = driver.getWindowHandles();

        System.out.println("windowHandles = " + windowHandles);

        System.out.println("BEFORE SWITCH = " + driver.getCurrentUrl());
        // of the first page we opened using our driver object pointer

        //to switch we can use LOOP :
        //idea : if this string window ID is not matching to windowHandle then switch method is called

        for (String windowID : windowHandles) {
            if (!windowID.equals(windowHandle)) {
                driver.switchTo().window(windowID);
            }
        }

        Thread.sleep(5000);

        System.out.println("after switch = " + driver.getCurrentUrl());


       // driver.close(); this will only close the current tap
        driver.quit(); // will close the window




    }

    /**
     * This method helps to swiitch in between windows based on page title
     * @param pageTitle
     * @param driver
     */
    public static void switchToWindowBasedOnTitle (String pageTitle,WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        for (String eachId : windows) {
            driver.switchTo().window(eachId);
            if ( driver.getTitle().equals(pageTitle)) {
                break;
            }
        }

    }
}
