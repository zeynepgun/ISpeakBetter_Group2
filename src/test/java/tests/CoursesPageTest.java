package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import base.BasePage;
import pages.CoursesPage;
import pages.LoginPage;


public class CoursesPageTest {

	
	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	CoursesPage coursespage;
	
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		loginPage = new LoginPage(driver);
		coursespage = new CoursesPage(driver);
	}
	
	// TestCases
}
