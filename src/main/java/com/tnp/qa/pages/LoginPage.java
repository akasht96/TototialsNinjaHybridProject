package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailField;
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordField;
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginOption;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNoMatchWarning;

	public void enterEmailFieldValue(String email) {
		emailField.sendKeys(email);
	}

	public void enterPasswordFieldValue(String password) {
		passwordField.sendKeys(password);
	}

	public AccountPage clickOnLoginOption() {
		loginOption.click();
		return new AccountPage(driver);
	}
	
	public String getemailPasswordNoMatchWarningMessage() {
		return emailPasswordNoMatchWarning.getText();
	}

}
