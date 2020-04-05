package com.automation.pages.activities;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {

    //WebElement + locators are in page class

    @FindBy (css = "[title='Create Calendar event']")
    //FindBy = comes from PageFactory class - it is enhancement coming from Page Object Model
    private WebElement createCalendarEvent;

    @FindBy (className = "select2-chosen") //no need to add in here because: it is searching by class name!! (not CSS)
    private WebElement owner;

    //#user-menu > a => search by id (#) and go to the direct child ( > )
    //.select2-chosen => . is find as CSS selector ! no need for tag (like className or other stuff)
    //class name has space  = better CSS or Xpath .. use and - to have two different class names

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;
    // id^ => means id starts with
    //the id is dynamic that's why we have to have ^

    @FindBy (css = "[id^='time_selector_oro_calendar_event_form_start']")
    private  WebElement startTime;

    @FindBy (css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;


    //@CacheLookup == > you will find the element once and store it. by not going in search for element again and again
    //Vasyl never got any advantage from this approach
    //because of the fact that element can get stale. so , you might exception

    @FindBy (className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy (css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    // //*[text()='Description']/../following-sibling::div//iframe
    // find text = description..then go to parent and then switch to following iframe

    //now we got a different locator for iframe : you can use : index, id , label
    @FindBy (css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    //(xpath)[2] means find all xpaths that I typed and find 2nd one
    //xpath[2] means find xpath that I typed as a 2nd child
    @FindBy (xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy (xpath = "//label[text()='Description']/following-sibling::div//div") //depends on content we might have dif.locators
    private WebElement generalInfoDescription;



    public void enterCalendarEventTitle(String titleValue) {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    //With enterCalendarEventDescription method in page class;
//you do not need to switch frame inside test,
//everything is covered is covered inside page; all kind of page interactions

    public void enterCalendarEventDescription(String description) {
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
    }
    public void clickOnSaveAndClose() {
        //this one throws :ElementClickInterceptedException
//        BrowserUtils.waitForPageToLoad(10);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("btn-group pull-right'] > button") ) );
//        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", saveAndClose);
    }
    public String getGeneralInfoTitleText() {
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
    }
    public String getGeneralInfoDescriptionText() {
        BrowserUtils.waitForPageToLoad(20);
        //if u sure 100% that ur locator correct that the reason of fail is wait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }

    public List<String> getColumnNames(){
        BrowserUtils.waitForPageToLoad(20);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='grid-header-cell__label']")));
        return BrowserUtils.getTextFromWebElements(columnNames);
    }

    public String getOwnerName(){
        BrowserUtils.waitForPageToLoad(10);
       // wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        //this code above was added after somebody's code has failed ( for me works without it as well)
        //waiting for presents for this owner element in DOM
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    //why we put current user element under base page class and owner element under calendar event page class?
    //current owner element belongs to top menu.. COPY!!!


    public void clickToCreateCalendarEvent(){

        BrowserUtils.waitForPageToLoad(20);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        //BrowserUtils.waitForPageToLoad(10);

    }

    public String getStartDate(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        BrowserUtils.scrollTo(startDate); //this is in case start date is low in the page and you need to scroll down to get it
        return startDate.getAttribute("value");
    }

    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value"); //because it is input! input has value
    }

    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }


    //image is self closable element

    //ElementClickInterceptedException => if you get it..you have to add wait before the click. something was clicked instead of your element
    //NoSuchElementException => wait issue/ or locator issue
    //TimeOutException => you have explicit or implicit wait , but it was not enough and your condition failed anyways
    //SessionIsNull => you hve to initialize LogIn page object inside your class
    //NoSuchSessionException => webdriver object was called but not created /not instantiated (sol: create page object inside test)
    //AssertionError => happens when your assertion - expected and actual do not match

}
