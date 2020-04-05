package com.automation.tests.day13;

import com.automation.utilities.ConfigurationReader;
import org.testng.annotations.Test;

public class ConfigurationReaderTest {

    @Test
    public void readProperties(){
            String browser = ConfigurationReader.getProperty("browser");
            String url = ConfigurationReader.getProperty("qa1");
            String color = ConfigurationReader.getProperty("color");

        System.out.println("url = " + url);
        System.out.println(browser);
        System.out.println("color = " + color);

        String storeManager = ConfigurationReader.getProperty("store_manager");
        String password = ConfigurationReader.getProperty("password");

        System.out.println("storeManager = " + storeManager);
        System.out.println("password = " + password);

        //design pattern => put together code to solve some problem
        //best coding practices that help to resolve design problems in software development
        //why we care?
        //to know how to put together your code and make it look meaningful
        //creational design pattern /structural/behavioral pattern
        //Singleton Pattern : ensure that we have one instance of the class
        //WebDriver object in one place with Singleton , makes sure every class will use only one particular webdriver object
        //plus we can access that single object from any place of our framework
        //Singelton class:
        //create private static object
        //make your constructor private/ so object cannot be instantiated
        //and make public .getInstance method to access instance of SingleObject(public getter for object)



    }
}
