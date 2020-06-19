package com.automation.tests.lastDay;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class ExcelIO {

    @Test
    public void readData() throws Exception {
        //using try with resources to auto close input stream and workbooks and output stream
        try (FileInputStream fileInputStream = new FileInputStream("VytrackTestUsers.xlsx");
             Workbook workbook = WorkbookFactory.create(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream("VytrackTestUsers.xlsx")) {

            Sheet workSheet = workbook.getSheetAt(0); //getting the first spreadsheet

            Row row1 = workSheet.getRow(0);//to get first row

            for (int rowIndex = 0; rowIndex < workSheet.getPhysicalNumberOfRows(); rowIndex++) {
                //outer loop changes rows
                Row row = workSheet.getRow(rowIndex);

                for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                    //inner loop changes columns
                    Cell cell = row.getCell(cellIndex);
                    System.out.print(cell + " ");
                }
                System.out.println();
            }


            row1.createCell(4);//we create a cell in the first row
            row1.getCell(4).setCellValue("Test Results");//we set a value

            workbook.write(fileOutputStream);//to apply changes to the file
            //don't open file manually, otherwise file will be corrupted!
        }
    }


    @Test
    public void createExcelFile() throws Exception{
        Date date = new Date();
        String fileName = date+"_test_results.xlsx";
        File file = new File(fileName);
        file.createNewFile();//will create a file with a specified name

        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            xssfWorkbook.createSheet("test_results");

        }
    }
}
