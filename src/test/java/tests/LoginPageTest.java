package tests;


import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
	@Test(priority = 1, enabled = true, description = "I Speak Better Login Page title")
	public void testPageTitle() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String title = loginPage.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE_STRING);
	}
	
	@Test(priority = 2, enabled = true, description = "Login the system in I Speak Better")
	public void testLogin() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String title = loginPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		System.out.println(title);
		Assert.assertEquals(title, Constants.MAIN_PAGE_TITLE_STRING);
	}
	
	@Test(priority = 3, enabled = true, description = "Login with incorrect email correct pw")
	public void testLogwrongun() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String title = loginPage.wrongLogin(ConfigReader.getProperty("incorrectuser"), ConfigReader.getProperty("password"));
		System.out.println(title);
		Assert.assertEquals(title, "Wrong Username or password!");	
	}
	
	@Test(priority = 4, enabled = true, description = "Login with correct email incorrect pw")
	public void testLogwrongpw() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String title = loginPage.wrongLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("incorrectpass"));
		System.out.println(title);
		Assert.assertEquals(title, "Wrong Username or password!");	
	}
	
	@Test(priority = 5, enabled = true, description = "Login with incorrect email incorrect pw")
	public void testLogwrongunpw() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String title = loginPage.wrongLogin(ConfigReader.getProperty("incorrectuser"), ConfigReader.getProperty("incorrectpass"));
		System.out.println(title);
		Assert.assertEquals(title, "Wrong Username or password!");	
	}
	
	
	@AfterMethod
	public void tearDown() {
		basePage.tearDown();
	}
	

}

