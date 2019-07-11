package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

/**
 * @author Sanket Barde
 * @Date 11/07/2019
 *
 */
public class ExcelReader {
	private static Properties excelProperties;
	private static String filePath;
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;

	public ExcelReader() throws IOException {
		filePath = System.getProperty("user.dir") + excelProperties.getProperty("excelFilePath");
		Assert.assertNotNull(filePath, "Excel File is not found");
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		fis.close();
	}

	public final int getSheetRows(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		return (sheet.getLastRowNum() + 1);
	}

	public final int getSheetColumns(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);
		return (row.getLastCellNum());
	}

	public final String getCellData(String sheetName, int colNum, int rowNum) {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		return (cell.getStringCellValue());
	}

	public final String getCellData(String sheetName, String colName, int rowNum) {
		int colNum = -1;
		int index = workbook.getSheetIndex(sheetName);
		try {
			sheet = workbook.getSheetAt(index);
			for (int i = 0; i < getSheetColumns(sheetName); i++) {
				row = sheet.getRow(0);
				cell = row.getCell(i);
				if (cell.getStringCellValue().equals(colName)) {
					colNum = cell.getColumnIndex();
					break;
				}
			}
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);

			cell.setCellType(CellType.STRING);

			return (cell.getStringCellValue());
		} catch (NullPointerException e) {
			return null;
		}
	}
}
