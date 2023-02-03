package com.Utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadData {
	/**
	 * This method returns value from property file of given key
	 * 
	 * @author Situ
	 *
	 */
	public static String propertyFileData(String value) {
		FileInputStream file;
		Properties p = new Properties();
		String add = System.getProperty("user.dir") + "\\TestData\\config.properties";
		try {
			file = new FileInputStream(add);
			p.load(file);
		} catch (Exception e) {
			System.out.println("Error occured while reading Property File data");
			e.printStackTrace();
		}
		return p.getProperty(value);
	}

	/**
	 * This method returns value from excel file of given row number and colomn
	 * number
	 * 
	 * @author Situ
	 *
	 */
	public static String excelFileData(int row, int col) {
		try {
			String add = System.getProperty("user.dir") + "\\TestData\\excel.xlsx";
			FileInputStream file = new FileInputStream(add);
			Sheet excel = WorkbookFactory.create(file).getSheet("Swag Labs");
			return excel.getRow(row).getCell(col).getStringCellValue();
		} catch (Exception e) {
			System.out.println("Error ocured during reading Excel file");
			return null;
		}

	}
}
