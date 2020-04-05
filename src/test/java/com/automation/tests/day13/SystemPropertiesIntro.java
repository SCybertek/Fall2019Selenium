package com.automation.tests.day13;

import org.testng.annotations.Test;

public class SystemPropertiesIntro {


    //get.property : test file in my workplace, I need to load the file somewhere, but how to specify the path?
    //every comp.has its own path ..and now we need to make sure the file will be working on other peoples comp.
    //to solve that issue : you use user directory and provide the directory
    //output flexible path !


    @Test
    public void test (){
        //for pom
        //System.getProperty("user.dir") + "/pom.xml"
        System.out.println(System.getProperty("user.dir")); // /Users/beglight/IdeaProjects/Fall2019Selenium
        System.out.println(System.getProperty("os.name")); //Mac OS X
        System.out.println(System.getProperty("user.home"));

        //Interview Question:
        //test file downloading --> go to downloads and verify that it is there
        // the username and server username is different..if I will specify mine path it will not work at his teammates comp
        //System.getProperty("user.dir") + "/Downloads" -->  to specify the path at file downloading test

        String pathToDownloads = System.getProperty("user.home") + "/Downloads";
        System.out.println("pathToDownloads = " + pathToDownloads);

        System.out.println(System.getProperty("os.arch")); //x86_64 - CPU architecture

        //When we need these?
        //if we have 10 or 10000 tests..it is difficult to manage these tests
        //we want them to execute in one particular environment without providing URL all the time
        //you can use path to define the path for all your classes at once
        //Browser Run => helps run your tests only in one Browser then on another
        //System class provides the help to organize and easily update your framework!
        //cross.browser test will be easy that way
        //Besides the system properties, you can also create one file and define our own custom properties to help configuration in our framework
        //the file is: TEXT file called: configurations.properties
        //properties is an extension for that file
        //we specify MOST important info about our pfoject
        //every test will take configuration from this file ! they will not exist separetely in your classes
        //this file should go to GitHub! it is an engine for our car :)
        //the more complex the project becomes the more complex your configuration file will become
    }
}
