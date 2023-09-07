package com.tnp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.AccountPage;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.LoginPage;
import com.tnp.qa.utils.Utilities;

@Test
public class LoginTest extends Base {

	public LoginTest() {
		super();
	}

	public WebDriver driver = null;
	LoginPage loginPage = null;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initialiseEebDriverAndOpenURl(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountOption();
		loginPage = homePage.clickOnLoginOption();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "testdata")
	public void LoginwithValidCredentials(String username, String password) throws InterruptedException {

		loginPage.enterEmailFieldValue(username);
		loginPage.enterPasswordFieldValue(password);
		AccountPage accountPage = loginPage.clickOnLoginOption();
		Thread.sleep(3000);
		// AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusofEditYourAccountInformationText(),
				"Edit your Account Info Text is not displayed");
		Thread.sleep(2000);
	}

	@DataProvider(name = "testdata")
	public Object[][] supplyLoginTestData() {
		Object[][] data = Utilities.getLoginTestDataFormExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void LoginwithInValidCredentials() throws InterruptedException {

		loginPage.enterEmailFieldValue(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordFieldValue(testDataprop.getProperty("invalidPassword"));
		loginPage.clickOnLoginOption();
		Thread.sleep(2000);

		String actualWarningText = loginPage.getemailPasswordNoMatchWarningMessage();
		String expectedWarningText = testDataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningText.contains(expectedWarningText), "Expected Warning Message is not displayed");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void LoginwithInValidEmailAndValidPassword() throws InterruptedException {

		loginPage.enterEmailFieldValue(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordFieldValue("Akash@6543");
		loginPage.clickOnLoginOption();
		Thread.sleep(2000);

		String actualWarningText = loginPage.getemailPasswordNoMatchWarningMessage();
		String expectedWarningText = testDataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningText.contains(expectedWarningText), "Expected Warning Message is not displayed");
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void LoginwithValidEmailAndInValidPassword() throws InterruptedException {

		loginPage.enterEmailFieldValue("asthakare@gmail.com");
		loginPage.enterPasswordFieldValue(testDataprop.getProperty("invalidPassword"));
		loginPage.clickOnLoginOption();
		Thread.sleep(2000);

		String actualWarningText = loginPage.getemailPasswordNoMatchWarningMessage();
		String expectedWarningText = testDataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningText.contains(expectedWarningText), "Expected Warning Message is not displayed");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void LoginwithoutCredentials() throws InterruptedException {

		loginPage.clickOnLoginOption();
		Thread.sleep(2000);
		String actualWarningText = loginPage.getemailPasswordNoMatchWarningMessage();
		String expectedWarningText = testDataprop.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertTrue(actualWarningText.contains(expectedWarningText), "Expected Warning Message is not displayed");
		Assert.assertTrue(actualWarningText.contains("abcd"), "Expected Warning Message is not displayed");
		
		Thread.sleep(2000);
	}

}
