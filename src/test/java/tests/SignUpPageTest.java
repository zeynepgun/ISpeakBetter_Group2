package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BasePage;
import pages.SignUpPage;

public class SignUpPageTest {

	WebDriver driver;
	BasePage basePage;
	SignUpPage signUpPage;
	//Properties properties;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		signUpPage = new SignUpPage(driver);
		
	}
	
	@Test(enabled=true, priority = 1)
	public void registerWithValidCredential() throws InterruptedException {
		signUpPage.registerWithValidCredential();
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
	}
	
	@Test(enabled=true, priority = 2)
	
	public void signUpWithFaceBookTest() throws InterruptedException {
		signUpPage.signUpWithFacebook();
		
		
	}	
	@Test(dataProvider = "getData", enabled = true, priority = 3)
	public void registerWithInvalidEmailTest(String email) {
		signUpPage.clickOnSignUpBtn();
		signUpPage.getEmail().sendKeys(email);
		
		signUpPage.clickOnSignUpBtn();
		signUpPage.registerWithInvalidEmail();
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
		
	}
	
	@Test(enabled=true, priority = 4)
	
	public void registerWithExistingEmailAddressTest() throws InterruptedException {
		signUpPage.registerWithExistingEmailAddress();
		
		
	}
	
	@Test(enabled=true, priority = 5)
	public void registerWithMandotoryFieldTest() throws InterruptedException {
		signUpPage.registerWithMandotoryField();
	}
	
	@Test(enabled=true, priority = 6)
	
	public void registerWithoutAlphanumericPwdTest() throws InterruptedException {
		signUpPage.registerWithoutAlphanumericPwd();
	}
	
	
	
	
	
	@AfterMethod
	public void quit() {
		basePage.tearDown();
	
		
	}
	@DataProvider 
	
	public Object[][] getData() {
		Object[][] data = new Object[4][1];
		data[0][0]="esragamil.com";
		data[1][0]= "esra@gmailcom";
		data[2][0]= "esra";
		data[3][0]="@gmail.com";
		return data;
	}
	
	
	
	
	
}
