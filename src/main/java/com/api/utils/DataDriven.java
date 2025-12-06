package com.api.utils;

import com.api.exceptions.ExcelException;
import com.api.pojo.excel.model.CellPosition;
import io.cucumber.java.sl.Ce;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
public class DataDriven{

    public static XSSFSheet fetchSheetDataFromExcel(String excelPath, String sheetName) {

        log.info("Reading Excel file from path: {}", excelPath);
        XSSFSheet sheet = null;
        try(FileInputStream fs = new FileInputStream(excelPath)) {
            log.debug("Excel file opened successfully");
            XSSFWorkbook workbook = new XSSFWorkbook(fs);

            int sheets = workbook.getNumberOfSheets();
            for(int i=0; i<sheets; i++){
                if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)){
                    sheet = workbook.getSheetAt(i);
                    break;
                }
            }


        }catch (IOException e){
            log.error("Failed to fetch Excel : {}", excelPath, e);
            throw new ExcelException("Failed to fetch Excel : "+excelPath, e);
        }
        return sheet;
    }

    public static CellPosition getRowAndColumnOfCell(XSSFSheet sheet, String columnName){
        int rowIndex =0;
        for(Row row : sheet){
            int columnIndex = 0;
            for(Cell cell : row){
                if(cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(columnName))
                    return new CellPosition(rowIndex, columnIndex, row, cell);
                columnIndex++;
            }
            rowIndex++;
        }
        throw new ExcelException(String.format("Column named : %s is not present in the sheet : %s",
                columnName, sheet.getSheetName()));
    }
}
