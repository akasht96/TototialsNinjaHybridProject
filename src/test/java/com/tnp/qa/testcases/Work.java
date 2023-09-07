package com.tnp.qa.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Work {

	public void readExcel() {
		LocalDateTime dateTime = LocalDateTime.now();
		// System.out.println(dateTime.toString().replace("-", "").replace(":", ""));

		Object logindata[][] = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/tnp/qa/testData/TestDataTNP.xlsx"));
			XSSFSheet sheet = workbook.getSheet("Login");
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
			for (Object[] s1 : logindata) {
				for (Object s : s1) {
					System.out.print(s);
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File reportFolders = new File(System.getProperty("user.dir") + "/test-output/ExtentReport/Screenshots");
		System.out.println(reportFolders.exists());
		if (reportFolders.exists() == false) {
			reportFolders.mkdirs();
		}

	}
}
