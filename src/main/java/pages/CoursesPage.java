package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.ElementUtil;

public class CoursesPage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//Constructor
	public  CoursesPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	//Locators
	//Locators
		public By coursesBtn = By.xpath("//span[@data-hover='Courses']");
		public By sectionTitle = By.xpath("//h1[contains(text(),'Courses provided')]");
		public By genConvEng = By.xpath("//a[@href='https://ispeakbetter.com/conversational-english']");
		public By genConvEngText = By.xpath("//h1[contains(text(),'Conversational English')]");
		public By genConvEngFreeClsBtn = By.xpath("//a[contains(text(),'GET A FREE CLASS')]");
		public By signUpTxt = By.xpath("//p[contains(text(),'Sign up using')]");
		public By cancelBtn = By.xpath("//button[@data-izimodal-transitionout='bounceOutDown']");
		//public String TESTDATA_SHEET_DATA = ".\\src\\main\\java\\testdata\\FaceBookExcel.xlsx";
		public String TESTDATA_SHEET_DATA = "./src/main/java/testdata/FaceBookExcel.xlsx";
		
		public By Fname = By.id("fname");
		public By Lname =By.id("lname");
		public By email =By.id("email");
		public By passWrd =By.id("password");
		public By ConfpassWrd =By.id("confirmPass");
		public By TimeZone =By.id("timezone");
		public By accept =By.id("check_term");
		public By signUpBtn =By.id("cmdSignup");
		
		//Actions
		public void clickButton (By locator) {
			elementUtil.doClick(locator);
		  }
		
		public String pageTitle() {
			return elementUtil.waitForGetPageTitle("");
			
		}
		public String sectionText(By locator) {
			return elementUtil.doGetText(locator);
		}
		
		public void enterValue(By locator, String key) {
			elementUtil.doSendKeys(locator, key);
		}
		
		public void selectCountry() {
			elementUtil.selectDropDownByValue(TimeZone, "America/New_York");
			
		}
	
}
