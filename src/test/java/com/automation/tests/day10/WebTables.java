package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTables {
    private WebDriver driver;

    //TABLE:
    //td=> table data / cells
    //tr=> table row
    //th=> table head

    @BeforeMethod
    public void setup(){

        //headless mode makes execution faster
        //in headless mode FILE uploading cannot be done!
        WebDriverManager.chromedriver().version("79").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //headless mode makes execution twice faster
        //it does everything except file uploading
        //set it to tru to make it work
        chromeOptions.setHeadless(false);//to run browser without GUI. Makes browser invisible.
        driver = new ChromeDriver(chromeOptions);
        //driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }
    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test (description = "Getting all column names")
    public void getColumnNames(){
        //th - represents table header cells
        List<String> expected = Arrays.asList("Last Name", "First Name", "Email", "Due", "Web Site", "Action");
        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));
        for(WebElement columnName : columnNames) {
            System.out.println(columnName.getText());

        }

        Assert.assertEquals(BrowserUtils.getTextFromWebElements(columnNames),expected);
    }
    // go to first table and get ALL Table heads -->//table[1]//th
    // table[@id='table1']//tbody//tr[1]//td[3]
    // '//' --> go very down
    // '/' --> go one step down

    @Test
    public void verifyRowCount(){
        // //tbody//tr - to get all rows from table body , excluding table header
        List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
        //if we will get a size of this collection , it automatically equals to number of elements
        Assert.assertEquals(rows.size(),4);
    }

    //we cannot select by visible text
    // it is not like select class

    /**
     * To get specific column, skip row index ( to include all of the rows!),
     * and just provide td index
     */
    @Test
    public void getSpecificColumn(){ //                         //table[1]/tbody/tr/td[5]
        List<WebElement> links = driver.findElements(By.xpath("//table[1]//tbody//tr//td[5]"));
        System.out.println(BrowserUtils.getTextFromWebElements(links));
    }


    public void getElementThroughSibling() {
        //   //table[1]//td[text()='jdoe@hotmail.com'] --> to find the email element
        // now going to another element = "delete"

        // going to the sibling => //table[1]//td[text()='jdoe@hotmail.com']//following-sibling::td/a[text()='delete']

        //to make it easier :
//go back to parent and find link that has text delete
//td is child of tr
////td[text()='fbach@yahoo.com']/..//a[text()='delete']
//even more simple way :
//it is more hardcoded! but easiest => you provide index so it s not flexible, if index is change locator will never find it
//go to find email in the first table go to parent go to second link inside this element

        ////table[1]//td[text()='jsmith@gmail.com']/..//a[2]
        //meaning : find email and then go to parent (..) then go to child/grandchild at index 2


        //galiba bunu kulanmamak daha iyi: this is hardcode -- > //table[1]//a[text()='delete'][1]
        // if order changes you can delete wrong element by mistake

        //hardcoded: meaning based n index.

    }
/** TASK until 4:45
 * Go to tables example page
 * Delete record with jsmith@gmail.com email
 * verify that number of rows is equals to 3
 * verify that jsmith@gmail.com doesn't exists any more in the table
 */

@Test
    public void deletingRecordTest(){
    driver.findElement(By.xpath("//table[1]//td[text()='jsmith@gmail.com']/..//a[2]")).click();
    List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
    //if we will get a size of this collection , it automatically equals to number of elements
    Assert.assertEquals(rows.size(),3);

    for (WebElement each: rows) {
    String eachEmail = driver.findElement(By.xpath("//table[1]//tbody//td[3]")).getText();  ;//checking every email
        Assert.assertFalse(eachEmail.contains("jsmith@gmail.com"));
    }
    //isDisplayed method works with webelement
    //if you dont have element = > No SuchElement exception

    Assert.assertTrue(driver.findElements(By.xpath("//table[1]//td[text()='jsmith@gmail.com']")).isEmpty());

}
    /**
     * Let's write a function that will return column index based on column name
     */
    @Test
    public void getColumnIndexByName() {
        String columnName = "Email";
        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));
        int index = 0;
        for (int i = 0; i < columnNames.size(); i++) {
            String actualColumnName = columnNames.get(i).getText();
            System.out.println(String.format("Column name: %s, position %s", actualColumnName, i));
            //if we are to use Printf we do not need String format
            if (actualColumnName.equals(columnName)) {
                index = i + 1;
                break;
            }
        }

        Assert.assertEquals(index, 3);
    }
@Test
    public void getSpecificCell(){
        String expected = "http://www.jdoe.com";
      int row = 3;
      int column = 5;
      String xpath = "//table[1]//tbody//tr[" + row + "]//td[" + column + "]";
      //dynamic locator I presume

      WebElement cell = driver.findElement(By.xpath(xpath));
      Assert.assertEquals(cell.getText(), expected);

}




}
