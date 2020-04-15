package com.automation.utilities;
/*
 * This is a utility for reading from writing to excel files.
 * it works with xls and xlsx files.
 */

import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            // Open the Excel file - Input stream used whe we have alien file
            FileInputStream ExcelFile = new FileInputStream(path);
            // Access the required test data sheet
            workBook = WorkbookFactory.create(ExcelFile); // workbook factory reads old and new version of Excel files
            workSheet = workBook.getSheet(sheetName);
            // check if sheet is null or not. null means  sheetname was wrong
            Assert.assertNotNull(workSheet, "Sheet: \"" + sheetName + "\" does not exist\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the data of specific cell
     * @param rowNum starting with 0
     * @param colNum starting with 0
     * @return
     */
    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * We will use this method to provide data from excel to DataProvider
     * @return 2 dimensional Array
     */
    public String[][] getDataArray() {

        String[][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value; // write value in Array
            }
        }
        return data;

    }

    /**
     * Get data as a List<Map<<String, String>>, where key name represents column name
     * @return
     */
    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();
        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }

            data.add(rowMap);
        }

        return data;
    }

    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    /**
     * Write something into excel file
     * @param value what should be written
     * @param rowNum starting with 0
     * @param colNum starting with 0
     */
    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;

        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            //this is output stream because we enter our changes from java to Excel
            workBook.write(fileOut);

            fileOut.close(); //IMPORTANT!! if you do not close it you can corrupt the file

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public int columnCount() {
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return workSheet.getPhysicalNumberOfRows();
    }

    //method from Andrea : it colors the cells that PASS in green and Skipped in red
    public void setCellData(String value, String columnName, int row, boolean passOrNot){
        Cell cell;

        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
        CellStyle style = workBook.createCellStyle();
        if(passOrNot) {
            style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            style.setFillPattern(FillPatternType.LEAST_DOTS);
        }else{
            style.setFillBackgroundColor(IndexedColors.RED1.getIndex());
            style.setFillPattern(FillPatternType.LEAST_DOTS);
        }
        try {
            cell = workSheet.getRow(row).getCell(column);
            if (cell == null) {
                cell = workSheet.getRow(row).createCell(column);
                cell.setCellValue(value);
                cell.setCellStyle(style);
            } else {
                cell.setCellValue(value);
                cell.setCellStyle(style);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

}

}
