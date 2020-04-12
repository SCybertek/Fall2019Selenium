package com.automation.tests.day25_excel_io;


import com.automation.utilities.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ReadDataFromExcel {

    @Test
    public void readExcelFileTest() throws Exception{

        //we need to get a file as an object
        File file = new File("VytrackTestUsers.xlsx");
        //object that represents Excel file - specific for excel
        Workbook workbook = WorkbookFactory.create(file); // create method throws IOException
        Sheet workSheet = workbook.getSheet("QA1-short"); // only one worksheet
        //you can read several, by iterating for example

        //getting first row
        Row firstRow = workSheet.getRow(0);
        
        //getting the first cell in the first row
        Cell firstCell = firstRow.getCell(0);
        
        //Get string value 
        String value = firstCell.getStringCellValue();
        
        String secondCellValue = firstRow.getCell(1).getStringCellValue(); 
        
        System.out.println("value = " + value);
        System.out.println("secondCellValue = " + secondCellValue);

        System.out.println("####################");

        int lastCell = firstRow.getLastCellNum();

        for (int i = 0; i < lastCell; i++) {
            System.out.print(firstRow.getCell(i) + " | "); //getCell will return Cell as an object
            //when we want String then you can use .getStringValue()
            //there is also getAddress method
            //in getCell it will automatically call some toString method. but if you need string use .getStringValue() method
        }

        //printing first row using Iterator :
//        Iterator<Cell> myIter = firstRow.cellIterator();
//        while (myIter.hasNext()){
//            System.out.println(myIter.next().getStringCellValue() + "+");
//        }


        //last row is 16th --> index is 15

        //index of last row
        int numberOfRows = workSheet.getLastRowNum();
        //returns how many rows at all
        int numberOfRows2 = workSheet.getPhysicalNumberOfRows();

        System.out.println("\nIndex of last row   : " + numberOfRows);
        System.out.println("\nNumber of rows      : " + numberOfRows2);


        System.out.println ("##############");

        //below to print every single row in the sheet :

        for (int row = 0; row < workSheet.getPhysicalNumberOfRows(); row++) {
            for (int cell = 0; cell < workSheet.getRow(row).getLastCellNum(); cell++) {
                String cellValue = workSheet.getRow(row).getCell(cell).getStringCellValue();
                System.out.println(cellValue + " | ");
            }
            System.out.println();
        }

        //printing all sheet using Lambda expression :
        //workSheet.forEach(each-> {each.forEach(eachcell-> System.out.print(eachcell.getStringCellValue()+" | "));System.out.println();});


    }

    @Test (description = "printing all values in the sheet")
    public void excelUtilityTest() {
        String path = "VytrackTestUsers.xlsx";
        String spreadSheet = "QA1-all";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        //creating object of ExcelUtil and provide constructor info
        //https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
        //excelUtil.getDataList().forEach(System.out::println); //method reference

        // {password=UserUser123, firstname=John, username=user1, lastname=Doe}
        System.out.println("######################");
        //or using for each loop
        for (Map<String, String> record : excelUtil.getDataList()) {
            System.out.println(record);
        }
    }

    @Test
    public void getColumnNamesTest() {
        String path = "VytrackTestUsers.xlsx";
        String spreadSheet = "QA1-short";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);

        System.out.println(excelUtil.getColumnsNames());
    }

}
