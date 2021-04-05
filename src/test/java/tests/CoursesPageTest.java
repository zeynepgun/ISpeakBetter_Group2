package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import pages.CoursesPage;
import pages.LoginPage;
import util.ExcelUtil;
import util.JavaScriptUtil;

//@Listeners(listeners.ExtentReportListener.class)
public class CoursesPageTest {

	
	public WebDriver driver;
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
	@Test(priority = 1,description = "This test check to be at courses section")
	public void checkCoursesText() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		coursespage.clickButton(coursespage.coursesBtn);
		String actualText = coursespage.sectionText(coursespage.sectionTitle);
		String expectedText = "Courses provided";
		Assert.assertEquals(expectedText, actualText);
	}
	@Test(priority = 2,description = "This test check to be at Conversational English section")
	public void checkConversationalEnglishPageText() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		coursespage.clickButton(coursespage.coursesBtn);
		coursespage.clickButton(coursespage.genConvEng);
		String actualText = coursespage.sectionText(coursespage.genConvEngText);
		String expectedText = "Conversational English";
		Assert.assertEquals(expectedText, actualText);
	}
	
	@Test(priority = 3,description = "This test check to login for free class sign up page")
	public void checkFreeCourseSignUpPage() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		coursespage.clickButton(coursespage.coursesBtn);
		coursespage.clickButton(coursespage.genConvEng);
		JavaScriptUtil.scrollDownSpecific(driver);
		coursespage.clickButton(coursespage.genConvEngFreeClsBtn);
		String actualText = coursespage.sectionText(coursespage.signUpTxt);
		String expectedText = "Sign up using Facebook or Google+";
		Assert.assertEquals(actualText, expectedText);
		coursespage.clickButton(coursespage.cancelBtn);
	}
	@DataProvider
	public Object[][] getFaceBookOptions(){
		Object [][] data = ExcelUtil.getTestData("data", coursespage.TESTDATA_SHEET_DATA);
		return data;
	}
	
	@Test(priority = 4,dataProvider = "getFaceBookOptions",description = "This test check to enable to add credantials sign up page for free class")
	public void signUpForFreeCourses(String FirstName, String LastName, String Email, String Password, String ConfPasword) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		coursespage.clickButton(coursespage.coursesBtn);
		coursespage.clickButton(coursespage.genConvEng);
		JavaScriptUtil.scrollDownSpecific(driver);
		coursespage.clickButton(coursespage.genConvEngFreeClsBtn);
		coursespage.enterValue(coursespage.Fname, FirstName);
		coursespage.enterValue(coursespage.Lname, LastName);
		coursespage.enterValue(coursespage.email, Email);
		coursespage.enterValue(coursespage.passWrd, Password);
		coursespage.enterValue(coursespage.ConfpassWrd, ConfPasword);
		coursespage.selectCountry();
		coursespage.clickButton(coursespage.accept);
		//coursespage.clickButton(coursespage.signUpBtn);
	}
	
	
	@AfterMethod 
	public void tearDown(){
	basePage.tearDown();
	} 
}
