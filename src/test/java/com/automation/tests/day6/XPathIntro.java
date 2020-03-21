package com.automation.tests.day6;

import com.automation.tests.utilities.BrowserUtils;
import com.automation.tests.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathIntro {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(3);

        //XPath is useful: we can use starts with contains etc..
        //we use it when we cannot rely on other locators

        //if you do not want to use tagname put : * (any tagname)

        WebElement btn1 = driver.findElement(By.xpath("//button[@onclick='button1()']"));
        btn1.click();


        WebElement result = driver.findElement(By.id("result"));
        System.out.println(result.getText());

        //click on Button2

        WebElement btn2 = driver.findElement(By.xpath("//button[@name='button2']"));
        btn2.click();

        System.out.println(result.getText());

        //button3
        //we can try matching only the beginning

        WebElement btn3 = driver.findElement(By.xpath("//button[starts-with(@id,'button_')]"));
        btn3.click();
        //start-with , find element that has a following value in the beginning only

        System.out.println(result.getText());

        //contains : to find the element that partially contains value
        //does not matter in the beginning or in the middle

        //for button 3 :
        //if we apply contains :
        // //button[contains(@id,'n_')]

        //XPass for index: it starts with 1 in xPass
        // //button[3]

        WebElement btn4 = driver.findElement(By.xpath("//button[contains(@id,'_button')][1]"));
        //partial ID : since here id= is changing every time we refresh the page
        btn4.click();

        System.out.println(result.getText());

        WebElement btn5 = driver.findElement(By.xpath("//button[contains(@id,'button')][3]"));
//or //button[contains(text(),'5')]
        btn5.click();
        System.out.println(result.getText());


        BrowserUtils.wait(3);
        driver.quit();

    }


    /**
     * XPath = locator for elements
     * we can use parent child relationship
     * it is slower than other locators (Css is faster)
     * but it can find anything on the page
     * we need to know it: it is used with HTML plu sXML documents as well
     * ABSOLUTE and RELATIVE
     * Absolute : starts with root elements --> from html / head/ body ..etc
     * Relative : can start in the middle of the document to find xPath
     * on chrome : right click and copy : XPath is relative / and Full XPath is absolute
     * DO NOT use copy paste != NOT reliable
     *
     * relative: //tagname[@attribute='value']
     * you can put * for tagname
     * single or double quotes for value of attribute is Mandatory!
     * //*[text()="Click for JS Prompt"]
     *
     */
}
