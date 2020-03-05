package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BasicNavigation {
    public static void main(String[] args) throws Exception {
        //to start selenium script we need
        //setUp webdriver (browserdriver) and create webdriver object
        //SELNIUM REQUIRES webdriver setup to run tests on specific browser!!
        //WebDriverManager helps to setup Browser driver
        WebDriverManager.chromedriver().setup();

        //or
        //manualy setting up . fist need to download chrome driver
        //this is little bit more headache ecpesially when you put in path
        //so we do not use it
        //System.setProperty("webdriver.chrome.driver","chromedriver");

        WebDriver driver = new ChromeDriver();
        //in selenium everything starts from WebDriver interface

        //RemoteWebDriver driver2 = new SafariDriver();
        //this is a parent and we applied polymorphism

        //get
        //it is a key to open the door
        driver.get("http://google.com");//to open window

        //we are addinhg it to maximize window
        driver.manage().window().maximize();

        driver.navigate().to("http://facebook.com"); //go to another page :

        //diffrent from full screen : when your all screen is closed

        //full screen

        driver.manage().window().fullscreen();

        if (driver.getTitle().toLowerCase().contains("facebook")) {
            System.out.println("TEST PASSED if page is facebook right now");
        } else {
            System.out.println("TEST FAILED if page is facebook right now");
        }

        Thread.sleep(3000);

        driver.navigate().back();

        //.you can also see page title 

        String title = driver.getTitle(); // will return title of the page

        String expectedTitle = "Google";

        System.out.println("title = " + title);


        if (expectedTitle.equals(title)) {
            System.out.println("Test PASSED if expceted title is equal Title");
        } else {
            System.out.println("Test FAILED if expected title equal Title");
        } // we also created amethod doing exactly same thing like above code


        verifyEquals(driver.getTitle(),"Google");

        Thread.sleep(3000);

//move forward in the browser history
        //again, going to amazon
        driver.navigate().forward();
        Thread.sleep(3000);//for demo, wait 3 seconds

        System.out.println("driver = " + driver.getTitle());
    //returns title of the page

        System.out.println("current URL " + driver.getCurrentUrl());

        driver.navigate().refresh();//to refresh the page - reload
        Thread.sleep(3000); //for demo , wait 3 seconds




        driver.close();//to close browser

        //WebDriverManager is abstract class
        //WebDriver is interface -- RemoteWebDriver(parentClass) --> ChromeDriver implementing class

        //CHROME driver is a class


        //WebDriver interface : Remote WebDriver IS implementing class
        //Remote WEB DRIVER is parent class
        //Edge driver chrome driver firefox driver all siblings
        //interface WEBDRIVER has abstract method (since it is interface)
        //we uset .get () method that is implemented by Chrome Browser that we used.
        //we used to open the website


        //red code in th eoutput is because of some IntelliJ practice

    }

    public static void verifyEquals(String arg1, String arg2) {
        if (arg1.equals(arg2)) {
            System.out.println("TEST PASSED  from method!!");
        } else {
            System.out.println("TEST FAILED  from method!");
        }
    }
}