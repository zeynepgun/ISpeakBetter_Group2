package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import base.BasePage;
import pages.SignUpPage;

public class SignUpPageTest {

	WebDriver driver;
	BasePage basePage;
	SignUpPage signUpPage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		signUpPage = new SignUpPage(driver);
	}
}
