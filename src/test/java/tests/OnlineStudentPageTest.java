package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import base.BasePage;

import pages.LoginPage;
import pages.OnlineStudentPage;

public class OnlineStudentPageTest {

	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	OnlineStudentPage onlineStudentPage;
	
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		loginPage = new LoginPage(driver);
		onlineStudentPage = new OnlineStudentPage(driver);
	}
	
	// TestCases
	
}
