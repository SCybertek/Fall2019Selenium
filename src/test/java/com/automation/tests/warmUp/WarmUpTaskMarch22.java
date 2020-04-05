package com.automation.tests.warmUp;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WarmUpTaskMarch22 {



    /**
     * arm-up task for 15 minutes:
     * Go to http://practice.cybertekschool.com/tables
     * Click on "Last name" column name
     * Verfiy that table is sorted by last name in alphabetic order.
     * until 11:19
     */

    private WebDriver driver;


    @Test
    public void alphabetMyself(){

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.navigate().to("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();
        //locator for our last name header

        driver.findElement(By.xpath("//table[1]//*[text()='Last Name']") ).click();
        //now lets loop each last name
        List< WebElement> firstColumn = driver.findElements(By.xpath("//table[1]//td[1]"));
        for (int x = 0 ; x < firstColumn.size() - 1 ; x ++) {
            Assert.assertTrue(firstColumn.get(x).getText().compareTo(firstColumn.get(x + 1).getText()) < 0);
            //if the result is negative number it means the first value (.getText)
            // is alphabetically comes prior the second value ( .getText() )
        }
        driver.quit();

    }

            @Test
            public void alphabeticTest(){


        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.navigate().to("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();

        // locator for last name : //table[1]//*[text()='Last Name']
                //locator for first column : //table[1]//tbody//td[1]
                // here tbody is everything inside the table 1
                //td => test data ..then it goes one by one in each cell!!

                driver.findElement(By.xpath("//table[1]//*[text()='Last Name']")).click();
                //clicking to make it in alphabetic order

                List< WebElement > allLastNames = driver.findElements(By.xpath("//table[1]//tbody//td[1]"));
                for (int x = 0 ; x < allLastNames.size()-1 ; x ++) {
                       String value = allLastNames.get(x).getText();
                       String nextValue = allLastNames.get(x+1).getText();
                }

               // SortedSet< WebElement > allLastSorted = new TreeSet<>(allLastNames);
                //java.lang.ClassCastException: class org.openqa.selenium.remote.RemoteWebElement cannot be cast to class java.lang.Comparable
                //cannot cast thats why above does not work
                // the solution may be converting WebElements into String and then dumps List of Strings into sorted set ? maybe

/**
 * Hard-Code :
 * hard codes are the not dynamic code
 * if requirement changes you need to go back to your code and re-code it again
 * kogda ti propisivaesh detali, kotoriye mogut izmenitsya v budushem
 * i togda tvoy kod budet fail
 * naprimer index nomer v tablice
 * esli zavtra tuda dobavyat stolbik
 *
 *
 */

        driver.quit();
            }

    //or xpath when I want to go through the elements by index number with [1]
    // for example, sometimes we need to put xpath code into parathesis
    // then put index number but sometimes it works when we put just right after xpath code.
    // I don't know what is the issue ???
    //If all elements under same parents no parantheisis
    //if elements are not children of the same element ..then you need to surround the element in ()
    //
}
