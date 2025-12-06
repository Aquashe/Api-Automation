package com.tests.excelDataIntegeration;

import com.api.pojo.excel.model.CellPosition;
import com.api.utils.DataDriven;
import com.base.ApiBaseTest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.testng.annotations.Test;

import java.util.Iterator;


public class RowValidations extends ApiBaseTest {
    private static final String EXCEL_PATH =
            "C:\\Users\\Wonder\\OneDrive\\Desktop\\ApiFramework\\ExcelDemoData.xlsx";
    private static final String SHEET_NAME = "TestData";
    private static final String CELL_NAME = "Purchase";

    @Test(priority = 1)
    public void purchaseRowValidations(){
        XSSFSheet sheet = DataDriven.fetchSheetDataFromExcel(EXCEL_PATH, SHEET_NAME);
        CellPosition purchaseRow = DataDriven.getRowAndColumnOfCell(sheet, CELL_NAME);
        for(Cell cell : purchaseRow.row()){
            System.out.print(cell+"\t");
        }
    }
}
