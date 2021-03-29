package tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import base.ConfigReader;
import pages.HomePage;

public class HomePageTest {

	
	WebDriver driver;
	BasePage basePage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		homePage = new HomePage(driver);
	}
	
	
	
}
