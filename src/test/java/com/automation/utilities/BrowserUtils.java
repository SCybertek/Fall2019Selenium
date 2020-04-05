package com.automation.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BrowserUtils {



    public static void wait(int seconds ) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); //this is a message that shows where exception occurred

        }
    }

    /**
     *
     * @param elements takes any WebElement
     * @return elements inside a List<String> collection </String>
     */
        public static List<String> getTextFromWebElements(List< WebElement> elements) {
            List<String> textValues = new ArrayList<>();
            for (WebElement element : elements) {
                if (!element.getText().isEmpty()) {
                    textValues.add(element.getText());
                }
            }
            return textValues;
        }


    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

        /**
         * Clicks on an element using JavaScript
         *
         * @param element
         */
        public static void clickWithJS(WebElement element) {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
        }


    /**
     * Scroll to element using JavaScript
     *
     * @param element
     */
    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     *
     * @param name screenshot name
     * @return path to the screenshot
     */
    public static String getScreenshot(String name){

        //adding date and time to screenshot name, to make screenshot unique
       // name = name + "_" + LocalDateTime.now();
       name = new Date().toString().replace(" ","_").replace(":","-") + "_" + name;

        //this is where we are going to store screenshot
        //   current location of project             +  path for screenshot folder (which is created auto) + extension
        String path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        //path where we are saving
        System.out.println("Screenshot is here: " + path);

        //for Windows users there were some problems with path solution :
        // String path = "";
        //        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
        //            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        //        } else {
        //            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        //        }
        //        System.out.println("OS name: " + System.getProperty("os.name"));


        //since our reference type is WebDriver
        //we cannot see methods from TakeScreenshot interface => and we cast
        // Takescreenshot is not visible remote WebDriver (our reference type) and to make it visible we cast
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        //it is taking the Screen Shot itself
        //takes and saves as a File (returns as File object)
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //it takes a screenshot and returns it as a FILE object!
        //import java.io.File; => default java library not from somewhere else

        File destination = new File(path); //it is where we will store the screen shot

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
        //later we will add date and time so images saved will not overwrite each other
        //for not we left it without it
    }
    //copy file to the previously specified location
    //FileUtils class comes from java, to work with the files, general file manipulation properties
    //It stores methods to work with file
    //takes 2 argument,where is your file and where to save it


/**
 *  In Selenium Webdriver there is an interface that is responsible for screenshots.
 *  TakesScreenshot --> only one method, getScreenshotAs.
 *  mostly used for failing tests, very useful during regression
 *  This method, will be used to take a screenshot whenever something fails in our code.
 *  We can put a condition into @Aftermethod --> if test fails -> take a screenshot,
 *  save it and later on attach to the HTML report. Report it's our evidence file.
 *  Especially, when we are running big suits of tests, like regression,
 *  it's gonna be very difficult to demonstrate test results without HTML report.
 */

    }


//MULTITHERADING
    //multipple tasks can be performed in the program ..not sequentional but in parallel
    //java supports multithreading


    //printStackTrace() : it's a message in console showing the entire history
    // of what exceptions occurred and what classes were involved

