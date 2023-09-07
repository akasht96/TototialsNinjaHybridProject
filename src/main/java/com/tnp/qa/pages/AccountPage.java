package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//a[contains(text(),'Edit your account information')]")
	private WebElement editYourAccountInformationOption;
	
	public boolean getDisplayStatusofEditYourAccountInformationText() {
		return editYourAccountInformationOption.isDisplayed();
	}
}
