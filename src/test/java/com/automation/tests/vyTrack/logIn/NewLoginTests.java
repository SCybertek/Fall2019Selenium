package com.automation.tests.vyTrack.logIn;

import com.automation.pages.LoginPage;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import com.automation.tests.vyTrack.AbstractTestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NewLoginTests extends AbstractTestBase {

    /**
     * Login and verify that page title is a "Dashboard"
     */
    @Test
    public void verifyPageTitle(){
        //test is coming from ExtendTest object
        //this line should be added to every test at the beginning
        test = report.createTest("Verify page title"); //create a test
        LoginPage loginPage = new LoginPage();
        loginPage.logIn();
        //we are not supposed to see any webElement in the test !
        //.info is just System.out but it also goes to report
        test.info("Login as store manager"); //log some steps
        String expected = "Dashboard";
        String actual = Driver.getDriver().getTitle();
        assertEquals(expected,actual, "Title is different");

        test.pass("Page title Dashboard was verified"); //this message will appear if Assertion passes

        //
    }

    /**
     * Enter wrong credentials and verify warning message
     */
    @Test
    public void verifyWarningMessage(){
        test = report.createTest("Verify warning message"); //create a test
        LoginPage loginPage = new LoginPage();
        loginPage.logIn("wrong", "wrong");
        assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");
        //take screenshot
        BrowserUtils.getScreenshot("login test");
        test.pass("Warning message was verified");
    }
//why we create object of LoginPage?
// in order just to use its method ? like to logIn()?
// is that the only reason we have that line inside every single test ?
//when we run 2 tests and we have

    //can we use map instead of two dimensional array to provide the data for the login functionality?
    //no , because there can be 3 columns, you will need List of Map
    //if you return Iterator then you can return List


    @Test(dataProvider = "credentials")
    public void loginWithDDT(String userName, String password) {
        test = report.createTest("Verify page title");
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(userName, password);
        test.info("Login as " + userName);//log some steps
        BrowserUtils.wait(2); //to get rid of : Actual   :Loading...
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        test.pass("Page title Dashboard was verified");
    }

    //data provider should have return type
    //it can return Object [][] or Object [] or Iterator<Object[]>
    //if there is only 1 column of data the single dimensional
    //if there is more than that then 2 D

    @DataProvider //this comes from TestNG
    public Object [][] credentials(){
        return new Object[][]{
                {"storemanager85", "UserUser123"},
                {"salesmanager110", "UserUser123"},
                {"user16", "UserUser123"}
                //here we have 3 sets of data so test will run exactly 3 times
                //if you have more input boxes you can add more info, more columns
        } ;
//later we will use Excel file instead of having data in Java
    }
}
