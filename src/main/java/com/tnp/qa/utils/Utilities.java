package com.tnp.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPICIT_WAIT = 25;
	public static final int PAGE_LOAD_TIMEOUT = 25;

	public static String generateEmailWithTimeStamp() {
		String timestamp = LocalDateTime.now().toString().replace("-", "").replace(":", "");
		return "akah" + timestamp + "@gmail.com";
	}

	public static String getUniqueSSName(String name) {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		return dateTime.format(dateTimeFormatter)+name;
	}
	
	public static String captureScrrenshot(WebDriver driver,String name) {
		
		File srcscreenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String uniqueSSName = Utilities.getUniqueSSName(name);
		File destscreenshotpath = new File(System.getProperty("user.dir") + "/test-output/ExtentReport/Screenshots/" + uniqueSSName + ".png");
		try {
			FileHandler.copy(srcscreenshotAs, destscreenshotpath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destscreenshotpath.getAbsolutePath();
	}

	public static Object[][] getLoginTestDataFormExcel(String sheetName) {
		Object logindata[][] = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/tnp/qa/testData/TestDataTNP.xlsx"));
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int lastRowNum = sheet.getLastRowNum();
			short lastColumnNum = sheet.getRow(0).getLastCellNum();
			logindata = new Object[lastRowNum][lastColumnNum];
			System.out.println(lastColumnNum);
			System.out.println(lastRowNum);
			for (int i = 0; i < lastRowNum; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				for (int j = 0; j < lastColumnNum; j++) {
					XSSFCell cell = row.getCell(j);
					CellType cellType = cell.getCellType();
					switch (cellType) {
					case STRING:
						logindata[i][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						logindata[i][j] = Integer.toString((int) cell.getNumericCellValue());
						break;
					}

				}
				System.out.println();
			}
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logindata;
	}
}
