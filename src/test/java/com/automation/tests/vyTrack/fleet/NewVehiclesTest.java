package com.automation.tests.vyTrack.fleet;

import com.automation.pages.LoginPage;
import com.automation.pages.fleet.VehiclesPage;
import com.automation.tests.vyTrack.AbstractTestBase;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewVehiclesTest extends AbstractTestBase {

    @Test
    public void verifyTitle(){
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();

        loginPage.logIn(); //logging in using name and pass from config.property file
        vehiclesPage.navigateTo("Fleet", "Vehicles");

        String expectedTitle = "All - Car - Entities - System - Car - Entities - System";
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
