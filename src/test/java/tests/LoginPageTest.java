package tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import base.BasePage;
import base.ConfigReader;
import pages.LoginPage;
import util.Constants;

public class LoginPageTest {
	
	public WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		loginPage = new LoginPage(driver);
	}
	@Test(priority = 1, enabled = true, description = "I Speak Better Main Page title")
	public void testPageTitle() {
		String title = loginPage.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE_STRING);
	}
	
	
	@Test(priority = 2, enabled = true, description = "login system in I Speak Better")
	public void testLogin() throws InterruptedException {
		loginPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		Thread.sleep(3000);
	}
	@AfterMethod
	public void tearDown() {
		basePage.tearDown();
	}
	

}

