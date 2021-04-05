package tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ConfigReader;
import pages.HomePage;
import util.ExcelUtil;
//@Listeners(listeners.ExtentReportListener.class)
public class HomePageTest {

	
	public WebDriver driver;
	BasePage basePage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver();
		homePage = new HomePage(driver);
	}
	
	@Test (priority=1, enabled=true, description="CheckHomePage URL")
	public void checkHomePageURL() {
		Assert.assertEquals(ConfigReader.getProperty("url"), homePage.getHomePageURL());
	}
	
	@Test(priority=2, enabled=true, description="Clicks on each top Navigation Bar items and checks if each works")
	public void checkTopNavigationBarItems() throws InterruptedException{
		
		List<String> urlsList = homePage.getTopNavigationBarItems();
		Assert.assertEquals(ConfigReader.getProperty("url"),urlsList.get(0));
		Assert.assertTrue(urlsList.get(1).contains("blog"));
		Assert.assertTrue(urlsList.get(2).contains("about"));
    	Assert.assertTrue(urlsList.get(3).contains("courses"));
		Assert.assertTrue(urlsList.get(4).contains("contact"));
		Assert.assertEquals(urlsList.get(5), "true");
		Assert.assertEquals(urlsList.get(6), "true");
		Assert.assertEquals(urlsList.get(6), "true");
	}
	
	@Test(priority=3, enabled=true, description = "Clicks on each link in the footer and checks if each works")
	public void checkFooterLinks() {
		ArrayList<String>  urls = homePage.getFooterLinks();
		//ispeakbetter.com/partners, https://ispeakbetter.com/contact, https://ispeakbetter.com/faq, 
	    //https://ispeakbetter.com/privacy-policy, https://ispeakbetter.com/terms-conditions, https://ispeakbetter.com/#
		Assert.assertTrue(urls.get(0).contains("partners"));
		Assert.assertTrue(urls.get(1).contains("contact"));
		Assert.assertTrue(urls.get(2).contains("faq"));
		Assert.assertTrue(urls.get(3).contains("privacy-policy"));
		Assert.assertTrue(urls.get(4).contains("terms-conditions"));
		Assert.assertTrue(urls.get(5).contains("#"));
	}
	
	@Test(dataProvider = "durationOptions", priority=4, enabled=false, description= "selects each option in duration dropdown menu")
	public void checkDurationDropDown(String value) {
	  Assert.assertEquals(homePage.selectDurationOption(value), value);
	}
   @DataProvider
   public Object[][] durationOptions(){
	   Object[][] options = {{"Select Duration"},{"30"},{"60"}};
	   return options;
   }
   
   
   @Test(dataProvider="subscribedOptions", priority=5, enabled=false, description ="selects each option in subscribed classes drop down menu")
   public void checkSubscribedOptions(String value) {
	   Assert.assertEquals(homePage.selectSubscribedClassesOption(value), value);
   }
   
   @DataProvider
   public Object[][] subscribedOptions(){
	   Object[][] options = {{"Select Package Length"},{"2 weeks"},{"4 weeks"},{"8 weeks"},
			   {"12 weeks"},{"24 weeks"}};
	   return options;
   }
 
   
   @Test(dataProvider="getFlexiblePackageInValidOptions", priority=6, enabled=true, description="Tries to buy a package with invalid options, should get a warning at the end")
   public void checkFlexiblePackageInvalidOptions(String durationOpt, String subsClassesOpt, 
		   String weeklyClassesOpt, String programOpt) throws InterruptedException{
	   homePage.selectDurationOption(durationOpt);
	   homePage.selectSubscribedClassesOption(subsClassesOpt);
	   homePage.selectweeklyClassOption(weeklyClassesOpt);
	   homePage.selectProgramOption(programOpt);
	   Assert.assertEquals(homePage.clickBuyBtnAfterInvalidSelection(), "Warning!");
   }
   @DataProvider
   public Object[][] getFlexiblePackageInValidOptions(){
	   String TESTDATA_SHEET_DATA = "./src/main/java/testdata/FlexiblePackageInValidOptions.xlsx";
	   Object[][] options = ExcelUtil.getTestData("Sheet1",TESTDATA_SHEET_DATA);
	   return options;
   }
   
   
   @Test(dataProvider="getFlexiblePackageValidOptions", priority=7, enabled=true, description= "Tries to buy a package with valid options, signup page should show up at the end")
   public void checkFlexiblePackageValidOptions(String durationOpt, String subsClassesOpt, 
		   String weeklyClassesOpt, String programOpt) throws InterruptedException{
	   homePage.selectDurationOption(durationOpt);
	   homePage.selectSubscribedClassesOption(subsClassesOpt);
	   homePage.selectweeklyClassOption(weeklyClassesOpt);
	   homePage.selectProgramOption(programOpt);
	   Assert.assertTrue(homePage.clickBuyBtnAfterValidSelection());
   } 
   @DataProvider
   public Object[][] getFlexiblePackageValidOptions(){
	   String TESTDATA_SHEET_DATA = "./src/main/java/testdata/FlexiblePackageValidOptions.xlsx";
	   Object[][] options = ExcelUtil.getTestData("Sheet1",TESTDATA_SHEET_DATA);
	   return options;
   }
   
   @Test(priority=8, enabled=true, description="checks if try for free button works")
   public void checkFreeBtn() {
	   Assert.assertTrue(homePage.clickFreeBtn());
	   //Assert.assertEquals(homePage.clickFreeBtn(), "Sign up");
   }
   
   
   
   @AfterMethod
   public void tearDown() {
	   basePage.tearDown();
   }
   
}