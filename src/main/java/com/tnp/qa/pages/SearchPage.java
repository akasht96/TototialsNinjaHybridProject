package com.tnp.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductFoundWarning;

	

	public Boolean verifyIsSearchedProductisDisplayed(String productname) {
		return driver.findElement(By.linkText(productname)).isDisplayed();
	}
	
	public String retriveNoProductFoundWarning() {
		return noProductFoundWarning.getText();
	}
}
