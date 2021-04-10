package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import base.BasePage;
import base.BasePage;
import util.Constants;
import util.ElementUtil;
import util.JavaScriptUtil;

public class LoginPage extends BasePage{
	
	public WebDriver driver;
	ElementUtil elementUtil;
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Locators 
    By wronglogin = By.xpath("//*[@id='crendentialsError']");
	By clickLogin = By.id("cmdSiginLink");
	By email = By.xpath("//input[@id='_email']");
	By password  = By.id("_password");
	By login = By.id("cmdSignin");
	By clickSignin = By.id("signin");
	By duration = By.xpath("//span[@id='select2-class_duration-container']");
	By list = By.className("select2-results__option");
	By subscribed = By.xpath("//span[@id='select2-package_length-container']");
	By weeklyClass = By.xpath("//span[@id='select2-per_week_class_number-container']");
	By program = By.xpath("//span[@id='select2-course_program-container']");
	
	//Page Actions (Methods)
	
	public String getPageTitle() {
		elementUtil.waitForElementPresentBy(clickLogin);
		elementUtil.doClick(clickLogin);
		elementUtil.waitForElementPresentBy(clickSignin);
		elementUtil.waitForGetPageTitle(Constants.LOGIN_PAGE_TITLE_STRING);
		return driver.getTitle();

	}
	

	public String doLogin(String username, String pwd) throws InterruptedException {
		elementUtil.waitForElementPresentBy(clickLogin);
		elementUtil.doClick(clickLogin);
		elementUtil.waitForElementPresentBy(clickSignin);
		elementUtil.doClick(clickSignin);
		elementUtil.isElementEnabled(email);
		elementUtil.doSendKeys(email, username);
		elementUtil.waitForElementPresentBy(password);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.waitForElementPresentBy(login);
		elementUtil.doClick(login);
		Thread.sleep(3000);
		elementUtil.waitForGetPageTitle(Constants.MAIN_PAGE_TITLE_STRING);
		return driver.getTitle();
	
	}
	public String wrongLogin(String username, String pwd) throws InterruptedException {
		elementUtil.waitForElementPresentBy(clickLogin);
		elementUtil.doClick(clickLogin);
		elementUtil.waitForElementPresentBy(clickSignin);
		elementUtil.doClick(clickSignin);
		elementUtil.isElementEnabled(email);
		elementUtil.doSendKeys(email, username);
		elementUtil.waitForElementPresentBy(password);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.waitForElementPresentBy(login);
		elementUtil.doClick(login);
		Thread.sleep(3000);
		elementUtil.waitForElementPresentBy(wronglogin);
		return driver.findElement(wronglogin).getText();


	}
	

}
