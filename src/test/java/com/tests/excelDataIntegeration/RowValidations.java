package com.tests.excelDataIntegeration;

import com.api.pojo.excel.model.CellPosition;
import com.api.utils.DataDriven;
import com.base.ApiBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Slf4j
public class RowValidations extends ApiBaseTest {
    private static final String EXCEL_PATH =
            "C:\\Users\\Wonder\\OneDrive\\Desktop\\ApiFramework\\ExcelDemoData.xlsx";
    private static final String SHEET_NAME = "TestData";
    private static final String CELL_NAME = "Purchase";

    @Test(priority = 1)
    public void purchaseRowValidations(){
        XSSFSheet sheet = DataDriven.fetchSheetDataFromExcel(EXCEL_PATH, SHEET_NAME);
        CellPosition purchaseRow = DataDriven.getRowAndColumnOfCell(sheet, CELL_NAME);
        List<Object> purchaseList = DataDriven.getRowCellValuesList(purchaseRow.row());

        log.info("{} values", CELL_NAME);
        purchaseList.forEach( obj-> System.out.print(obj+"\t"));
    }
}
