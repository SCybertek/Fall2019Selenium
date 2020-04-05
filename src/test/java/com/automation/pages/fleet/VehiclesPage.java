package com.automation.pages.fleet;

import com.automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//every page has its own page class
//based on  tab name

public class VehiclesPage extends AbstractPageBase {

    @FindBy(partialLinkText = "Create Car")
    private WebElement createCar;


    public void clickToCreateCar(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();
    }

}
