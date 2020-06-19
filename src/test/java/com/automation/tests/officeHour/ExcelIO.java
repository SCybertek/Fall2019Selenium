package com.automation.tests.officeHour;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelIO {

    @Test //we tried to create a new Excle file ourselves..follow up with Vasyl
    public void createExcelFileTest() throws IOException {
        //you can use File.separator rather than / to use it with all operation systems
        //String filePath = System.getProperty("user.dir") + File.separator + "regression_test_result.xlsx";
        File file = new File("regression_test_results.xlsx");
        boolean isCreated = file.createNewFile();//to create new file
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.close();
        //new excel .xlsx file object
        Workbook workbook = new XSSFWorkbook();
        //new worksheet name
        Sheet sheet = workbook.createSheet("test_results");
        Row firstRow = sheet.createRow(0);//create first row
        Cell firstCell = firstRow.createCell(0);//create cell in the first column
        Row secondRow = sheet.createRow(1);//to create second row
        Cell secondCell = secondRow.createCell(0);//create cell in the first column
        firstCell.setCellValue("Test Status");
        secondCell.setCellValue("PASSED");
        //to write excel object into file
        FileOutputStream fileOutputStream = new FileOutputStream(new Date() + "_regression_test_results.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        //so, FileINPUtstream is used to INPUT that file we created inside our JAVA TEST?
        // and OoutputSTream to SEND our modification to that file? YES

        //XSSWORKBOOK is an interface .. for NEW EXCLE file.. WORKBOOK FACTORY can handle new excel and excle.xlxs file as well

        //creating and setting at the same time
        //Row firstRow = sheet.createRow(0);
        //for (int i = 0; i < 20; i++) {
        //    firstRow.createCell(i).setCellValue(i+1);
        //}
    }

    @Test //we tried to create a new Excle file ourselves..follow up with Vasyl
    public void modifyExcelFileTest() throws IOException {
        //you can use File.separator rather than / to use it with all operation systems
        String filePath = System.getProperty("user.dir") + File.separator + "VytrackTestUsers.xlsx";
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        file.close();//close input stream
        Sheet sheet = workbook.getSheet("QA3-short");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(5);
        System.out.println(cell);
        cell.setCellValue("FAILED"); // to modify cell value
        System.out.println(cell);
        //to write changes into the file
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

        //when we write into the file make sure you CLOSE the file!! or file will be destroyed
        //git checkout VytrackTestUsers.xlsx => to UNDO your changes in the Excel file

        //file output stream: will help to write changes into the file



    }


}
