package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTestPractice {

    public static void main(String[] args) {
        //unit test
        //to check if our method works properly
        //if assertion fails, that means our method does not work correctly
        //that means we have to fix the method
        System.out.println(reverseString("Apple"));
        String expected = "cba" ;
        String toReverse = "abc" ;
        String actual = reverseString(toReverse);

        //Assertion
        verifyEquals(expected, actual);


//        we can create 1 main method per class..
//        instead we gonna create methods that willbe marked as unit tests!

    }
//this way we do NOT need main method and we can create lots of tests in one class!!!
//this is annotation
    @Test (description = "Verify if method can reverse a String")
    //description is not working for junit , make sure you are using testNG (your import)
    public void test() {
        String expected = "elppa";
        String actual = reverseString("apple");
        //it is coming from testing, Junit also has this class (Assert)
        //to verify if expected result is equals to actual
        //you can compare if expected result is equal to actual
        Assert.assertEquals(actual,expected);
    }
    //you can compare to object , but you have to override equals method first
//if it will fails:
    //java.lang.AssertionError: expected [elpp] but found [elppa]
    //    Expected :elpp
    //    Actual   :elppa
    //    <Click to see difference>

    @Test (description = "Verify if method can reverse a String")
    public void test2() {
        String expected = "rac";
        String actual = reverseString("car");
        //TestNG method : .assertEquals()
        Assert.assertEquals(actual,expected);
    }



    public static boolean verifyEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("Test Passed");
            return true;
        } else {
            System.out.println("Test failed!!!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }
    }
    //we cannot run 2 tests at the same time in here since we have MAIN method

    /**
     * This method stands for reversing strings
     * @param str to reverse
     * @return reversed string
     */
    public static String reverseString(String str) {
        String reverse = "";
        for (int i  = str.length() -1 ; i>= 0 ; i-- ){
            reverse+= str.charAt(i);
        }
        return reverse;
    }

    //TestNG is another library with methods that can help us !
    // TestNG will help us to setUp our framework
    //some frameworks could be written totally using testNG and Selenium

    //in build reports are present in testNG


    //JUnit --> TestNG came out after and better than JUnit

}
