package com.tnp.qa.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tnp.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver = null;
	public Properties prop=null;
	public  Properties testDataprop=null;
	public Base()  {
		try {
		prop=new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/tnp/qa/config/config.properties"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			testDataprop=new Properties();
			testDataprop.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/tnp/qa/testData/TestData.properties"));
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public  WebDriver initialiseEebDriverAndOpenURl(String browesr) {
		switch (browesr) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("URL"));
		
		return driver;
	}
}
