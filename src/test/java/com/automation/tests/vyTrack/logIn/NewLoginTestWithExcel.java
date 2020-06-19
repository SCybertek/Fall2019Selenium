package com.automation.tests.vyTrack.logIn;

import com.automation.pages.LoginPage;
import com.automation.tests.vyTrack.AbstractTestBase;
import com.automation.utilities.ExcelUtil;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class  NewLoginTestWithExcel extends AbstractTestBase {

//              main goal is :
//            1- getting all data from excel while testing login with different user,
//            2- then writing updated test status into excel file
//            3- if fails it will go to after method and there we would make a record to excel file as Fail

    // to use data provider we have to have a balanced data sheet : if we have several cells empty it is not working :(
    //the only place we can read determined test status is in AfterMethod

    @Test(dataProvider = "credentialsFromExcel")
    public void loginTestWithExcel2(String execute, String username, String password, String firstname, String lastname, String result) {
        //we need username and password columns
        String path = "VytrackTestUsers.xlsx";
        //spreadsheet name
        String  spreadSheet = "QA3-short";
        //create object of excel utility class so we can write into the file
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        //we have to read the file one more time in here because we want to SET new values in the excel file

        test = report.createTest("Login test for username : " + username); // for every username will be dif. report

        if ( execute.equals("y")) {

            LoginPage loginPage = new LoginPage();
            loginPage.logIn(username,password);
            test.info("Login as " + username);//log some steps
            test.info(String.format("First name: %s, Last name: %s, Username: %s", firstname, lastname, username));
            test.pass("Successfully logged in as " + username);
            excelUtil.setCellData("PASSED", "result", row++);
        } else if (execute.equals("n")) {

            test.skip("Test was skipped for user : " + username);
            //write into excel that test was skipped, because first column has value "n"
            excelUtil.setCellData("SKIPPED", "result", row++);
            //to skip some test in testNg
            throw new SkipException("Test was skipped for user: " + username);
            //this exception is coming from testNG
        }
        // we got the else part out because the test failure will only be seen in the after method of our test base class
    }
    /**
     * we have these columns :
     *  //execute	username	password	firstname	lastname	result
     */
    @DataProvider
    public Object[][] credentialsFromExcel(){
        //we need username and password columns
        String path = "VytrackTestUsers.xlsx"; // we may have multiple excel sheets we need to read from
        String  spreadSheet = "QA3-short";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        return excelUtil.getDataArray();
    }
}
