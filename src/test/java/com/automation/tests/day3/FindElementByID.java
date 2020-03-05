package com.automation.tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementByID {
    public static void main(String[] args) throws Exception{

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/login");
        driver.findElement(By.name("username")).sendKeys("tomsmith") ;
        //above is in one shot

        Thread.sleep(2000);
        WebElement password = driver.findElement(By.name("password"));
        //finding the element

        password.sendKeys("SuperSecretPassword");
        //input is done with sendKeys


        //this is syntax for clicking
        driver.findElement(By.id("wooden_spoon")).click();

        Thread.sleep(2000);

        String message = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.tagName("h4")).getText(); //getText was added to make this outcome as a String


        if (actual.equals(message)) {
            System.out.println("test passes");
        } else {
            System.out.println("test failed");
        }

        //finding an element by <a hyperlink
        //logout button : it looks like a button , but it is actually a link
        //every eleent with a tag is a link

        //if you have couple space in the text , just use partialLinkTest instead of linkedtest
        //linkedtest - equals()
        //partialLinktest - contains()

        WebElement logout = driver.findElement(By.partialLinkText("Logout") );
        // we removed the space

        String href = logout.getAttribute("href");
        //finding attributes using element (?) i think
        String className = logout.getAttribute("class");

        System.out.println(href);
        System.out.println(className);


        logout.click();

        Thread.sleep(3000);

        //let's enter invalid credentials

        driver.findElement(By.name("username")).sendKeys("wrong");
        driver.findElement(By.name("password")).sendKeys("wrong");
        driver.findElement(By.id("wooden_spoon")).click();



        Thread.sleep(2000);



        WebElement errorMessage = driver.findElement(By.id("flash-messages"));

        System.out.println(errorMessage.getText());

        Thread.sleep(2000);



        driver.quit();


        //searching for tag --> //
        //searching for attribute with element --> [name="q"]
        //regular search of h4

        //no such element exception occurs when locator is wrong

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //it waits until page is loaded for 10 seconds max

    }
}
