package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstnameField;
	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement lastnameField;
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailField;
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement telephoneField;
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordField;
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement confirmpasswordField;
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement policyAcceptedcheckbox;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//div[@id='content']/h1")
	private  WebElement loginVerificationTxt;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement alreadyRegisteredEmailAddressWarning;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement enterFirstNameWarning;
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement enterLastNameWarning;
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement enterEmailAddressWarning;
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement enterTelephoneWarning;
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement enterPasswordWarning;
	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordConfirmationWarning;
	public void enterFirstname(String firstname) {
		firstnameField.sendKeys(firstname);
	}

	public void enterLastname(String lastname) {
		lastnameField.sendKeys(lastname);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterTelephoneNumber(String telephone) {
		telephoneField.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void enterConfirmPassword(String confirm) {
		confirmpasswordField.sendKeys(confirm);
	}

	public void clickPrivacyAcceptedCheckbox() {
		policyAcceptedcheckbox.click();
	}

	public void clickOnSubmitBtn() {
		submitBtn.click();
	}
	
	public Boolean verifyLoginSuccessfullyTextIsDisplayed() {
		return loginVerificationTxt.isDisplayed();
	}
	
	public String retriveDuplicateEmailAddressWarning() {
		return alreadyRegisteredEmailAddressWarning.getText();
	}
	
	public String retrivePrivacyPolicyWarning() {		
		return privacyPolicyWarning.getText();		
	}
	
	public String retriveEnterFirstNameWarning() {
		return enterFirstNameWarning.getText();
	}
	
	public String retriveEnterLastNameWarning() {
		return enterLastNameWarning.getText();
	}
	public String retriveEnterEmailAddressWarning() {
		return enterEmailAddressWarning.getText();
	}
	public String retriveEnterTelephoneWarning() {
		return enterTelephoneWarning.getText();
	}
	public String retrivEnterPasswordWarning() {
		return enterPasswordWarning.getText();
	}

	public String retrivepasswordConfirmationWarning() {
		return passwordConfirmationWarning.getText();
	}
}
