package com.automation.tests.day7;

public class xPathNotes {

        //TagName is usually not for <div or <class ..it is used for headings
//lint text == equal == even space sensitive
//partial linkText = like contains

//.XML document is not rendered people deal with it .. it is used by developers..
// HTML - is a document and ..

//xpass works everywhere .. pom file and html as well

// absolute = when you expect an error in one place - in specific place ..sequence is sabit...
//absolute pass depends on NOdes
//you need to start from root element
//in case of HTML == it is <html>
// beginning ===> / always starts with front slash

//if the element contains only dynamic values oyu can use ablosute Xpass (?)
// relative xPass ==> starts with .. // 2 forward slashes
//ex: //tagName[contains(@attribute,'value')] --> case and space sensitive
// single quotes mostly prefered

//className doe snot allow spaces = we can use (.) instead of space (????
//HOW TO find parent from child:
// when you have child element BUT you need a parent == :
// you can use xPass :::
/// //button[text()='Button1']/..
//  ////button[text()='Button1']/../.. ==> to goto grandparent

//Xpath - over  scc Selector : we can move forward and backwards
//find element by text  ( it available only in xPath)


        //!!!!!!!finding sibling: (in general input and label goes hand in hand = you can create method to find label, and put that into xpath)
//        <label for="username">Username</label>
//        <input type="test" name="username">
        //get input using its sibling LABel     text()='Username'
        //driver.findElement(By.xpath("//label[@for='username']/following-sibling::input"))
        //for other way around:
        //driver.findElement(Byu.xpath("//input[@name='username']/preceding-sibling::label"))

        }
