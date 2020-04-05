package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will be extended by page classes
 * Ant common webelements/locators can be stored here
 * Since navigation menu doesn't belong to particular page
 * We cannot really create a dedicated page class to store
 * elements from that menu
 */
//Page classes will extend=> PageBase
//test classes will extend=> TestBase
    //we do not need to have By in here..it is not the rule for the base pages
public abstract class AbstractPageBase {

    protected WebDriver driver = Driver.getDriver();
    //driver is another reference for our same WebDriver
    protected WebDriverWait wait = new WebDriverWait(driver, 15);
    //we added it inhere so subclasses can reuse the same Wait object

    @FindBy (css = "#user-menu > a")
    protected WebElement currentUser;
    //we want them to be protected because it is an abstract class and we need them inherited (not private)

//passing Webdriver object inside the POM constructor
    //it is VERY important ! it serves to initialize the object and it finds the Elements!!
    //without it NO PAGE Object Model class will work !!!
//finding web elements in POM : selenium has sth to improve this process : Page Factory
//  helps to find element easier, syntax is shorter, more organized
    public AbstractPageBase(){
        PageFactory.initElements(driver, this );
    }

    //We come up with method that will wrap up locator not to have duplication
    // by using webElements directly in test class

    public String getCurrentUserName(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }

    /**
     * Method for VYtruck navigation. Provide tab name and module name to navigate
     * @param tabName like Dashboards, Fleet, or Customers
     * @param moduleName like Vehicles, Vehicles Odometer and Vehicles Costs
     *                   locator in here is flexible .it depends on what user wants to open
     */
    public void navigateTo(String tabName, String moduleName) {
        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'"+tabName+"')]";
        String moduleXpath = "//span[@class='title title-level-2' and text()='" + moduleName + "']";

        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(driver);
        BrowserUtils.wait(4);
        actions.moveToElement(tabElement).
                pause(2000).
                click(moduleElement).
                build().perform();

        //increase this wait rime if test is still failing
        BrowserUtils.wait(4);
    }

    //why we put current user element under base page class and
    // owner element under create calendar event page class?
    //"current owner" element belongs to the top menu.
    // Top menu  ( or navigation menu) doesn't belong to particular page.
    // Since top menu elements are shared, we can store them in the base page class.

    //object repository - pages package --> where is your page classes --> from page classes you create page objects

}
