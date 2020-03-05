package com.automation.tests.day3;

import com.automation.tests.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementsPractice {

    public static void main(String[] args) throws Exception {

        //for now we are using main method

//        WebDriverManager.chromedriver().setup();
//        //instead of repeating this lets create a method!
//        WebDriver driver = new ChromeDriver();

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/sign_up");

        //our code : searching element by name attribute

        WebElement fullName = driver.findElement(By.name("full_name"));

        //we do NOT search element by TAG name because many elements can have the same TAG

        //how to enter the text inside:

        //fullName.sendKeys("Mister Twister");
        fullName.sendKeys("Mister ");
        Thread.sleep(2000);
        fullName.sendKeys("Twister");
        Thread.sleep(1000);

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("sdet@cybertek.com" );
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.name("wooden_spoon"));

        //signUp.click();
        signUp.submit();

        //when you see type="submit" you can use submit() instead of click()
        //it makes sense to use when click method does not work

        Thread.sleep(5000);
        //THANK YOU MESSAG APPEARS :
        //we can search by h3 and class="subheader" and also by name "signup_message"


        String expected = "Thank you for signing up. Click the button below to return to the home page.";

        WebElement messsage = driver.findElement(By.className("subheader") );



        String actual = messsage.getText();
        if (expected.equals(actual)){
            System.out.println("Test Passed");
        }else {
            System.out.println("Test Failed");
        }


        
        driver.quit(); //to close everything!








    }
}
