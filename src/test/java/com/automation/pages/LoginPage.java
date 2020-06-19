package com.automation.pages;

import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

//this is a blueprint to page object that will be created out of it
public class LoginPage {
    //why not extends ? : (Vasyl :hello, it should extend))
    //AbstractPageBase mostly has methods associated with the Navigation bar in Vytrack. the navigaion bar doesnt exist in the login page

    //I think we dont need to extend.
    // LoginPage need just for login, read property and get method

    //yes @Sofiya Nuryyeva login is by isself basically,
    // does  not have common methods or locators like other pages so it has its own initElements methods


    @FindBy (id = "prependedInput") //you can put any locator
    private WebElement username ;
    //public WebElement username2 = Driver.getDriver().findElement(By.id("prependedInput"));
    //these above are the same
    //first code is cleaner though

    @FindBy (id = "prependedInput2")
    private WebElement password;

    @FindBy (id = "_submit")
    private WebElement login;

    @FindBy (linkText = "Forgot your password")
    private WebElement forgotPassword;

    @FindBy(css = "[class='alert alert-error']")
    private WebElement warningMessage;

    @FindBy(xpath = "//div[@class ='alert alert-error']//div")
    private WebElement loginAlert;

    public String getLoginAlert(){
        return loginAlert.getText();
    }

    //==> we made all webElements private so that they are not accessible anywhere else!
    //and we already have methods to get these
    //WebElement should not be visible in the test!:
    //if they are public you will need more time for loading and more wait time will be needed

    public LoginPage(){
        //for every page we have to create different class
        //to connect our webdriver, page class and page factory
        //PageFactory - used to use @FindBy annotations
        //PageFactory - helps to find elements easier without using .findElements()..
        PageFactory.initElements(Driver.getDriver(), this);
        //.init = enables to connect to our webdriver and find elements

    }

    //FIND BY is important since these elements do not exist without them
    /**
     * Method to login , version #1
     * login as a specific user
     * @param username
     * @param password
     */
    public void logIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password, Keys.ENTER);
    }

        //instead of creating separate code inside your test
        //we can reuse this method when we need to logIn
        //we can also add waits in here

        //we are hiding the implementation from the class..user will only call the method ?? how come it is public anyways
        //find webelements group them based on the page and ..?

    /**
     * Method to login , version #2
     * admin credentials or no dependency on the role
     * login as a default user
     *  Credentials will be retrieved from configuration.properties file
     */
    public void logIn() {

        this.username.sendKeys(ConfigurationReader.getProperty("store_manager"));
        this.password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }


    public String getWarningMessageText() {
        return warningMessage.getText();
    }


        /**
         * /*
         * VYTrack consist on bunch of pages, open app is one page, login is one page.. every page consist of elements,
         * every element can be taken by selenium webDriver, represent as webElement than we can do whatever we want.
         * It is not acceptable to continuously repeat to finding same webElement
         * We must have minimum code duplication
         * In terms of web elements ; up until now we did not have concept to store and manage locators efficiently
         * Every test class we repeat same web elements like login
         * We will come up with design approach that will allow us to create classes => based on pages
         * We will store locators that represent web elements
         * login page class => login page locators  => PAGE OBJECT MODEL
         * we are not gonna keep finding locators again and again!
         * POM => we will create page classes for every page that will be tested
         * POM => store locators
         * Configuration Properties file => store credentials - connection url's - info about servers
         * keep elements inside class not in properties
         * if you put them in properties file ; you have to always keep checking what is the keyname to use that locator, this isn't convenient
         * "so when you create a page class are you storing all the WebElement locators as variables,
         * and then have basic navigation methods built in as well that you can call upon? " -yes (Jordan)
         * Before page object we create page class
         * create class => give corresponding name (login page => LoginPage class)
         * instantiate that class
         * pages package => is collection of page classes  (com.automation.pages)
         * under pages package => we create LoginPage (corresponding name to the page that we will test)
         * finding web element in POM : selenium has sth to improve this process : Page Factory
         * helps to find element easier, syntax is shorter, more organized
         * @FindBy
         * @FindBys
         * @FindAll
         * we use this annotations and put the locator in parenthesis
         * to be able to use annotations :
         * first initialize page factory that comes from selenium
         * create constructor
         *     public LoginPage(){
         *             //to connect our webDriver, page class and page factory
         *             //PageFactory - used to use @FindBy annotations
         *             //PageFactory - helps to find elements easier
         *             PageFactory.initElements(Driver.getDriver(), this);
         *     }
         * and write locators - annotations on the top of page :
         *     //use these on top of instance variables
         *     @FindBy(id = "prependedInput")
         *     public WebElement username;
         *     @FindBy(id = "prependedInput2")
         *     public WebElement password;
         *     @FindBy(id = "_submit")
         *     public WebElement login;
         * */

}
