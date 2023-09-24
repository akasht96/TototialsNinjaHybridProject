package com.tnp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.SearchPage;

//adding a comment here - added more details 
@Test
public class SearchTest extends Base {

	public SearchTest() {
		super();
	}

	public WebDriver driver = null;
	HomePage homePage = null;
	SearchPage searchPage = null;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initialiseEebDriverAndOpenURl(prop.getProperty("browser"));
		homePage = new HomePage(driver);
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void verifySearchwithValidProduct() {

		homePage.sendKeysForSearchOption("HP");
		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.verifyIsSearchedProductisDisplayed("HP LP3065"), "Link Text is not Displayed");
	}

	@Test(priority = 2)
	public void verifySearchwithInValidProduct() {

		homePage.sendKeysForSearchOption("Honda");
		searchPage = homePage.clickOnSearchButton();

		String noProductFoundText = searchPage.retriveNoProductFoundWarning();
		//Assert.assertTrue(noProductFoundText.contains(testDataprop.getProperty("noProductFoundWarning")),"No Product Found Text is not Displayed");
		Assert.assertTrue(noProductFoundText.contains("abcvd"),"No Product Found Text is not Displayed");
	}

	@Test(priority = 3,dependsOnMethods = {"verifySearchwithInValidProduct"})
	public void verifySearchwithNoProduct() {

		homePage.sendKeysForSearchOption("");
		searchPage = homePage.clickOnSearchButton();

		String noProductFoundText = searchPage.retriveNoProductFoundWarning();
		Assert.assertTrue(noProductFoundText.contains(testDataprop.getProperty("noProductFoundWarning")),
				"No Product Found Text is not Displayed");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
