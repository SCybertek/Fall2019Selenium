package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

// select class ONLY from Selenium!!!
public class SelectByText {

    public static void main(String[] args) {


        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        //how to identify drop down?
        //based on locator!
        //it has ID = "dropdown" - to allocate this element

        WebElement simpleDropDown = driver.findElement(By.id("dropdown"));

        //webelement will go inside the constructor
        Select selectSimpleDropDown = new Select(simpleDropDown);
        selectSimpleDropDown.selectByVisibleText("Option 2");
        //visible text is inside the drop down
        selectSimpleDropDown.selectByVisibleText("Option 1");

        //Select selectSimpleDropdown = new Select(driver.findElement(By.id("dropdown"))); one shot is ok as well ..no need tocreate select objcet

        //next drop down with year/month/day (DOB)
        //each has its own ID in here
        //if you want to find a new element inside drop down you have to create a new select object

        Select selectYear = new Select(driver.findElement(By.id("year")));
        Select selectMonth = new Select(driver.findElement(By.id("month")));
        Select selectDay = new Select(driver.findElement(By.id("day")));

        selectYear.selectByVisibleText("2003");
        selectMonth.selectByVisibleText("April"); //NoSuchElementException: Cannot locate element with text: 1
        //we have to enter MONth name since it is the visible text.Value is 0-11 .. but not visible text
        selectDay.selectByVisibleText("3");

        BrowserUtils.wait(3);

        //select all months one by one

        List<WebElement> months = selectMonth.getOptions();//all options from drop down
        for (WebElement each : months) {
            //get month name and select based on that
            selectMonth.selectByVisibleText(each.getText());
            BrowserUtils.wait(1);
        }


        BrowserUtils.wait(3);

        //state selection :

        Select selectState = new Select(driver.findElement(By.id("state")));
        selectState.selectByVisibleText("District Of Columbia"); //in HTML it is marked with black inc

        //option that is currently selected
        //.getFirstSelcetdOption () ==> will return webelement
        // ==> .getText() will returns visible text of the webelement as a String


        String selected = selectState.getFirstSelectedOption().getText();


        if (selected.equals("District Of Columbia")) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }


        List<WebElement> states = selectState.getOptions();
        for (WebElement eachState : states) {
            System.out.println(eachState.getText());
            //System.out.println(eachState.getAttribute(ea);
            //only printing
        }



        //we can retrieve values of states as well !
        //getAttribute() method will help for that! //pass href as attribute (ex)
        //value="AL" for alabama for example == value in here is attribute (brownish color in HTML)

        BrowserUtils.wait(3);





        driver.quit();


    }
}
