package Practice.Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ExcelsheetDemo {

	@Test
	public void getExcelData() {

		String userDir = System.getProperty("user.dir");
		String TestdataSheetPath = userDir + "\\src\\main\\java\\resources\\ExcelDemo.xlsx";
		Workbook workbook = null;
		Sheet sheet;

		int numberOfRows;
		int numberOfColumns;
		FileInputStream file = null;

		try {
			file = new FileInputStream(TestdataSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Factory for creating the appropriate kind of Workbook
		// (be it HSSFWorkbook(for .xls extension) or XSSFWorkbook(for .xlsx
		// extension)),by auto-detecting from the supplied input.
		try {
			workbook = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet("Sheet1");

		numberOfRows = sheet.getLastRowNum();
		numberOfColumns = sheet.getRow(0).getLastCellNum();

		System.out.println("numberOfRows = " + numberOfRows);
		System.out.println("numberOfColumns = " + numberOfColumns);

		Object[][] data = new Object[numberOfRows][numberOfColumns];

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}
	}
}
