package com.tnp.qa.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReporter {

	private static ExtentReports extentReports = null;

	public static ExtentReports generateReport() {
		try {
			extentReports = new ExtentReports();
			File reportFolders = new File(System.getProperty("user.dir") + "/test-output/ExtentReport/Screenshots");
			if (reportFolders.exists() == false) {
				reportFolders.mkdirs();
			}
			File filepath = new File(System.getProperty("user.dir") + "/test-output/ExtentReport/ExtentReport.html");
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepath);
			extentReports.attachReporter(sparkReporter);

			// change order of tabs
			// sparkReporter.viewConfigurer().viewOrder()
			// .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY
			// }).apply();

			// set configuration value in report
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setReportName("Report name");
			sparkReporter.config().setDocumentTitle("Document title");
			sparkReporter.config().setTimeStampFormat("dd-MM-yy HH:mm:ss");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return extentReports;
	}
}
