package com.automation.tests.vyTrack.logIn;

import com.automation.pages.AbstractPageBase;
import com.automation.pages.LoginPage;
import com.automation.tests.vyTrack.AbstractTestBase;
import com.automation.utilities.Driver;
import com.automation.utilities.JavaFakerAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class NewLoginUSingJavaFaker extends AbstractTestBase {

    JavaFakerAPI jf = new JavaFakerAPI();
    SoftAssert softAssert = new SoftAssert();



    /**
     * Login and verify that page title is a "Dashboard"
     */
    @Test //(groups = "smoke")
    public void verifyPageTitle(){
        //test is coming from ExtendTest object
        //this line should be added to every test at the beginning
        test = report.createTest("Verify that login was unsuccessful"); //create a test
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(jf.getUserName(),jf.getPassword());
        String  actual = loginPage.getLoginAlert();
        String expected = "Invalid user name or password.";
        Assert.assertEquals(actual,expected);
        softAssert.assertEquals(actual,expected);

    }
}
