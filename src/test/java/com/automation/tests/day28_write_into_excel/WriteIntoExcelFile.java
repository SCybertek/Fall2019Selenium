package com.automation.tests.day28_write_into_excel;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class WriteIntoExcelFile {

    @Test
    public void writeIntoFileTest() throws IOException {
        FileInputStream inputStream = new FileInputStream("VytrackTestUsers.xlsx"); //path from Content Root
        Workbook workbook = WorkbookFactory.create(inputStream); // we can add String password in here as well to open secure Excel

        inputStream.close(); //we need to close the file after opening

        Sheet sheet = workbook.getSheet("QA3-short");
        Row row = sheet.getRow(1); //2nd row
        Cell cell = row.getCell(5); //last column

        System.out.println("Before : " + cell.getStringCellValue());
        //lets write in that cell
        cell.setCellValue("PASSED");//I am changing from n/a to passed
        System.out.println("After : " + cell.getStringCellValue());

        //adding new column name in the first row :
        Row firstRow = sheet.getRow(0); //get first row
        Cell newCell = firstRow.createCell(6); //create new cell
        newCell.setCellValue("Date of execution"); //give the name to this cell

        //write data and time info inside the last column

        Row secondRow = sheet.getRow(1);
        Cell newCell2 = secondRow.createCell(6); //create a cell !!!
        newCell2.setCellValue(LocalDateTime.now().toString()); //set current date and time info into new cell
        //output was : 43935.85652
        //we need to save it as a String : java.lang.IllegalStateException: Cannot get a STRING value from a NUMERIC cell

        //I crete if I want to write something into the file
        //don't forget to close excel file if you opened it
        FileOutputStream outputStream = new FileOutputStream("VytrackTestUsers.xlsx");
        workbook.write(outputStream);
        workbook.close(); //this will also close the input stream
        outputStream.close();
        /**
         * Close the underlying input resource (File or Stream),
         *  from which the Workbook was read.
         *
         * <p>Once this has been called, no further
         *  operations, updates or reads should be performed on the
         *  Workbook.
         */
    }

    //whenever you create a cell, it does not mean that all cell under neat will created automatically
    //So first we created object of second row then we created new cell and write date and time information
}
