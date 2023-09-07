package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountOption;

	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginOption;

	@FindBy(xpath = "//ul/li/a[text()='Register']")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement seachOption;
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement clickOptionBtn;

	public void clickOnMyAccountOption() {
		myAccountOption.click();
	}

	public LoginPage clickOnLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage clickOnRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void sendKeysForSearchOption(String keys) {
		seachOption.sendKeys(keys);
	}
	public SearchPage clickOnSearchButton() {
		clickOptionBtn.click();
		return new SearchPage(driver);
	}
}
