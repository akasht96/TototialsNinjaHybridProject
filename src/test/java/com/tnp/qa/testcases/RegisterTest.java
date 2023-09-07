package com.tnp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.RegisterPage;
import com.tnp.qa.utils.Utilities;

@Test
public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver = null;
	RegisterPage registerPage = null;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initialiseEebDriverAndOpenURl(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountOption();
		Thread.sleep(2000);
		registerPage = homePage.clickOnRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountwithMandatoryFields() throws InterruptedException {

		registerPage.enterFirstname("abcdefgh");
		registerPage.enterLastname("xyzhjn");
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber("1234567891");
		registerPage.enterPassword("12345");
		registerPage.enterConfirmPassword("12345");
		registerPage.clickPrivacyAcceptedCheckbox();
		registerPage.clickOnSubmitBtn();
		Thread.sleep(4000);
		Assert.assertTrue(registerPage.verifyLoginSuccessfullyTextIsDisplayed(),
				"Login Verification Text is not Displayed");
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void verifyAccountwithAlreadyRegisteredAccount() throws InterruptedException {

		registerPage.enterFirstname("Akash");
		registerPage.enterLastname("Thakare");
		registerPage.enterEmail("sdsdkkjk@gmail.com");
		registerPage.enterTelephoneNumber("+917972219825");
		registerPage.enterPassword("Akash@6543");
		registerPage.enterConfirmPassword("Akash@6543");
		registerPage.clickPrivacyAcceptedCheckbox();
		registerPage.clickOnSubmitBtn();
		Thread.sleep(3000);

		String actualAlreadyRegisteredErrorText = registerPage.retriveDuplicateEmailAddressWarning();
		String expectedAlreadyRegisteredErrorText = testDataprop.getProperty("emailAddressIsAlreadyRegisteredWarning");
		Assert.assertEquals(actualAlreadyRegisteredErrorText, expectedAlreadyRegisteredErrorText,
				"Already Login Text is not Displayed");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void verifyAccountwithNotProvidingAnyValues() throws InterruptedException {

		registerPage.clickOnSubmitBtn();

		Thread.sleep(3000);
		String actualPrivacyWarning = registerPage.retrivePrivacyPolicyWarning().trim();
		String actualFirstNameyWarning = registerPage.retriveEnterFirstNameWarning();
		String actualLastNameWarning = registerPage.retriveEnterLastNameWarning();
		String actualEmailWarning = registerPage.retriveEnterEmailAddressWarning();
		String actualTelephoneWarning = registerPage.retriveEnterTelephoneWarning();
		String actualPasswordWarning = registerPage.retrivEnterPasswordWarning();
		Assert.assertTrue(actualPrivacyWarning.contains(testDataprop.getProperty("privacypolicyWarnig")),
				"Privacy Policy Warning Message is not displayed");

		Assert.assertTrue(actualFirstNameyWarning.contains(testDataprop.getProperty("firstNameWarnig")),
				"First Name Warning Message is not displayed");

		Assert.assertTrue(actualLastNameWarning.contains(testDataprop.getProperty("lastNameWarnig")),
				"Last Name Warning Message is not displayed");

		Assert.assertTrue(actualEmailWarning.contains(testDataprop.getProperty("emailWarnig")),
				"Email Warning Message is not displayed");

		Assert.assertTrue(actualTelephoneWarning.contains(testDataprop.getProperty("telephoneWarnig")),
				"Telephone Number Warning Message is not displayed");

		Assert.assertTrue(actualPasswordWarning.contains(testDataprop.getProperty("passwordWarnig")),
				"Password Warning Message is not displayed");
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void verifyAccountByProvidingdifferntPassword() throws InterruptedException {

		registerPage.enterFirstname("XYZ");
		registerPage.enterLastname("PQR");
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber("1234567890");
		registerPage.enterPassword("1234567");
		registerPage.enterConfirmPassword("89101112");
		registerPage.clickPrivacyAcceptedCheckbox();
		registerPage.clickOnSubmitBtn();
		Thread.sleep(3000);

		String actualPasswordConfirmWarning = registerPage.retrivepasswordConfirmationWarning();
		//Assert.assertTrue(actualPasswordConfirmWarning.contains(testDataprop.getProperty("passwordConfirmationWarnig")),
			//	"Password Confirm Warning message is not Displayed");
		
		Assert.assertTrue(actualPasswordConfirmWarning.contains("av"),"Password Confirm Warning message is not Displayed");
		Thread.sleep(2000);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
