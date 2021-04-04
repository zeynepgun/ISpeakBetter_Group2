package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import pages.SignUpPage;
@Listeners(listeners.ExtentReportListener.class)
public class SignUpPageTest {
	public WebDriver driver;
	BasePage basePage;
	SignUpPage signUpPage;
//Properties properties;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		signUpPage = new SignUpPage(driver);

	}

	@Test(enabled = true, priority = 1)
	public void registerWithValidCredential() throws InterruptedException {
		signUpPage.registerWithValidCredential();
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
	}

	@Test(enabled = true, priority = 2)

	public void signUpWithFaceBookTest() throws InterruptedException {
		//This test fails, because when we sign in with facebook account, we are not directed to ispeakbetterpage
        Assert.assertTrue(signUpPage.signUpWithFacebook().contains("Sign up"));
	}

	@Test(dataProvider = "getData", enabled = false, priority = 3)
	public void registerWithInvalidEmailTest(String email) {
		
		Assert.assertEquals(signUpPage.registerWithInvalidEmail(email), "Email Invalid");

	}

	@Test(enabled = true, priority = 4)
	public void registerWithExistingEmailAddressTest() throws InterruptedException {
		
		Assert.assertEquals(signUpPage.registerWithExistingEmailAddress(), "Email already exist! Please use other email to continue");

	}

	@Test(enabled = true, priority = 5)
	public void registerWithMandotoryFieldTest() throws InterruptedException {
		signUpPage.registerWithMandotoryField();
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
	}

	@Test(enabled = true, priority = 6)
	public void registerWithoutAlphanumericPwdTest() throws InterruptedException {
		signUpPage.registerWithoutAlphanumericPwd();
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
	}

	@AfterMethod
	public void quit() {
		basePage.tearDown();

	}

	@DataProvider

	public Object[][] getData() {
		Object[][] data = new Object[4][1];
		data[0][0] = "esragamil.com";
		data[1][0] = "esra@gmailcom";
		data[2][0] = "esra";
		data[3][0] = "@gmail.com";
		return data;
	}

}
