package com.tnp.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.tnp.qa.utils.ExtentReporter;
import com.tnp.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports generateReport;
	ExtentTest createTest;
	@Override
	public void onStart(ITestContext context) {
		generateReport = ExtentReporter.generateReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		createTest = generateReport.createTest(result.getName());
		createTest.log(Status.INFO, result.getName() + " - Started executing ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destscreenshotpath=Utilities.captureScrrenshot(driver, result.getName());
		createTest.log(Status.INFO,MediaEntityBuilder.createScreenCaptureFromPath(destscreenshotpath).build());
		createTest.log(Status.PASS, result.getName() + " - got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destscreenshotpath=Utilities.captureScrrenshot(driver, result.getName());
		createTest.log(Status.INFO,MediaEntityBuilder.createScreenCaptureFromPath(destscreenshotpath).build());
		createTest.log(Status.INFO, result.getThrowable());
		createTest.log(Status.FAIL, result.getName() + " - got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		createTest.log(Status.INFO, result.getThrowable());
		createTest.log(Status.SKIP, result.getName() + " - got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		generateReport.flush();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "/test-output/ExtentReport/ExtentReport.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
