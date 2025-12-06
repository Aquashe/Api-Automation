package com.api.pojo.excel.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public record CellPosition(int rowIndex, int columnIndex, Row row, Cell cell) {
}
