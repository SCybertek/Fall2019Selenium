package com.automation.tests.utilities;

public class BrowserUtils {

    public static void wait(int seconds ) {
       try {
           Thread.sleep(seconds * 1000);
       } catch (InterruptedException e) {
           e.printStackTrace(); //this is a message that shows where exception occurred

       }
    }



//MULTITHERADING
    //multipple tasks can be performed in the program ..not sequentional but in parallel
    //java supports multithreading


    //printStackTrace() : it's a message in console showing the entire history
    // of what exceptions occurred and what classes were involved
}
