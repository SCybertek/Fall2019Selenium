package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.*;


public class BasicTestNGTests {


    @BeforeTest
    //runs ONLY once before class and before method
    public void beforeTest(){
        System.out.println("Before test");
    }
    //runs ONLY once after class and after method
    @AfterTest
    public void afterTest(){
        System.out.println("After test");
    }

    //before class runs only once before ANY tests
    //regardless on number of tests, it runs only once per class
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }

    //if we need something to run before tests
    //it runs before every method! works as a pre-condition
    //some connection should happen
    @BeforeMethod
    public void setup() {
        System.out.println("Before Method");
    }


    //runs automatically after every test
    @AfterMethod
    public void tearDown(){
        System.out.println("After Method");
    }

    @Test //(priority = 1)
    public void test1() { //we dont have to make it static
        System.out.println("Test1");
        String expected = "apple";
        String actual = "apple";
        Assert.assertEquals(actual, expected);
    }

    //we cannot have parameters in Tests ? since we dont have values to test like this? or can we?
    //we will cover it later
    //it has to come from XMl file etc


    @Test //(priority = 2)
    public void test2() {
        System.out.println("Test2");
        int num1 = 5;
        int num2 = 10;
        //it is called hard assertion
        //if assertion fails = it stops the execution (due to exception)
        Assert.assertTrue(num1<num2);
       // throw new SkipException("TESTTESTTES");
        //this exception is needed in order to SKIP test = at least this is how I made it work :)
    }


    @Ignore
    @Test//(priority = 3)
    public void test3() {
        String w1 = "Java";
        String w2 = "Java";
        System.out.println("Test 1");
        Assert.assertEquals(w1, w2);
    }
    //ASSERTION
    //test NG has 2 types of test assertion
    //Assert Class provides hard assertions
    //AssertTrue ==> called HARD assertion
    //What is the difference? if hard assertion fails - test execution stops due to exception
    //In case of soft assertion test execution does not stop!!
    //for soft assertion you can use class- SoftAssert
    //but common practice is to use only hard assertions
    //JUNIt has only hard assertions

    //BeforeSuite : something that happens before regression ..before ALL tests and after.

    // runs before test and before class
}
