package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.ConfigReader;
import util.ElementUtil;
import util.JavaScriptUtil;

public class HomePage {

	WebDriver driver;
	ElementUtil elementUtil;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Remember you added launchURL method and another selectbytext method to ElementUtils for this class, different
	// than others
	//Modified excell utilities so that everyone can use his or her own excell file without touching excelUtil.java
	
	// Locators for TC02
     
	String topNaviBarItemBeforeXpath = "(//div[@id='mobile-navbar-collapse']/ul//li//a)[";
	By signupLink = By.id("cmdSignupLink");
	By signinLink = By.id("cmdSiginLink");
	By languagesLink = By.id("dropdownMenuLink");
	
	By signUpBtn = By.cssSelector("button#cmdSignup.submit");
	By signInBtn = By.cssSelector("button#cmdSignin.submit");
	By firstName = By.id("fname");
	
    //Locator for TC03
	
	By footerDiv = By.cssSelector("div.footer-bottom");
	
	// Locators for TC04
	
//	By duration = By.xpath("//span[@id='select2-class_duration-container']");
//	By list = By.className("select2-results__option");
//	By subscribed = By.xpath("//span[@id='select2-package_length-container']");
//	By weeklyClass = By.xpath("//span[@id='select2-per_week_class_number-container']");
//	By program = By.xpath("//span[@id='select2-course_program-container']");

	
	By duration_dd = By.xpath("(//div[@class='col-sm-6']//select)[1]");
	By subscribed_dd = By.xpath("(//div[@class='col-sm-6']//select)[2]");
	By weeklyClass_dd = By.xpath("(//div[@class='col-sm-6']//select)[3]");
	By program_dd = By.xpath("(//div[@class='col-sm-6']//select)[4]");
	By warning = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//h2");
	By buyBtn = By.id("cmdPurchase");
	By nextBtn =By.cssSelector("button#cmdSignupNext.submit");
			
	//Locator for TC05 
	By freeBtn = By.id("btnFree"); 
	// ************** Actions **********************************************

	public String getHomePageURL() {
		return elementUtil.doGetPageUrl();
	}

	public List<String> getTopNavigationBarItems() {

		List<String> urls = new ArrayList<String>();

		// The first 5 items will be checked by the url info
		for (int i = 1; i <= 5; i++) {
			String itemxpath = topNaviBarItemBeforeXpath + i + "]";
			By loca = By.xpath(itemxpath);
			if (elementUtil.isElementEnabled(loca)) {
			//	System.out.println("navigation bar item " + i + " visible");
				elementUtil.doClick(loca);
				urls.add(elementUtil.doGetPageUrl());
				System.out.println("Clicked on navigation bar item " + i);
				// after each click, should go back to homepage to click other items on
				elementUtil.launchURL(ConfigReader.getProperty("url"));
			} else {
			//	System.out.println("item " + i + " is not visible in navigation bar");
			}
		}
		// 6 th item is just |, so skipped it
		// 7th item signupLink: check if the signup button isdisplayed after click
		elementUtil.doClick(signupLink);
		urls.add(Boolean.toString(elementUtil.isElementDisplayed(signUpBtn)));
		elementUtil.launchURL(ConfigReader.getProperty("url"));
		// 8th item signinLink: check if the signin button is diplayed after click
		elementUtil.doClick(signinLink);
		urls.add(Boolean.toString(elementUtil.isElementDisplayed(signInBtn)));
		elementUtil.launchURL(ConfigReader.getProperty("url"));
		// 9th item Languages: check if the menu is opened after click
		WebElement langEle = elementUtil.getElement(languagesLink);
		langEle.click();
		urls.add(langEle.getAttribute("aria-expanded"));
		elementUtil.launchURL(ConfigReader.getProperty("url"));

		return urls;
	}

	public ArrayList<String> getFooterLinks() {

		WebElement footerDriver = elementUtil.getElement(footerDiv);

		// Will try to open each link in new tab
		// Windows users should change COMMAND to CONTROL
		String clickOnLink = Keys.chord(Keys.COMMAND, Keys.ENTER);

		// get all links in the footer
		List<WebElement> links = footerDriver.findElements(By.tagName("a"));

		for (int i = 0; i < links.size(); i++) {
			links.get(i).sendKeys(clickOnLink);
		}

		// Traverse through the links and getURL
		
		ArrayList<String> urls = new ArrayList<String>();
		
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(windows);
		
		//loop starts from 1, do not want to take the original page ,just the clicked links
		for(int i=1;i<windows.size();i++) {
		driver.switchTo().window(list.get(i));
		urls.add(driver.getCurrentUrl());
		}
       
		//System.out.println(urls);
        return urls;
	}
	
	
	public String selectDurationOption(String text){
		
	    //JavaScriptUtil.scrollDownSpecific(driver);
		//JavaScriptUtil.scrollIntoView(driver.findElement(duration_dd), driver);
		
		//JavaScriptUtil.clickElementByJS(elementUtil.getElement(duration_dd), driver);
		
		elementUtil.waitForElementPresentBy(duration_dd);
		return elementUtil.selectandGetDropDownByText(duration_dd, text);
	}
	
	public String selectSubscribedClassesOption(String text) {
		elementUtil.waitForElementPresentBy(subscribed_dd);
		//JavaScriptUtil.clickElementByJS(elementUtil.getElement(subscribed_dd), driver);
		return elementUtil.selectandGetDropDownByText(subscribed_dd, text);
	}
	
	public String selectweeklyClassOption(String text) {
		return elementUtil.selectandGetDropDownByText(weeklyClass_dd, text);
	}
	
	public String selectProgramOption(String text) {
		return elementUtil.selectandGetDropDownByText(program_dd, text);
	}
	
	public String clickBuyBtnAfterInvalidSelection() {
		elementUtil.doClick(buyBtn);
		return elementUtil.getElement(warning).getText();
	}
	
	public boolean clickBuyBtnAfterValidSelection() {
		//System.out.println(elementUtil.isElementDisplayed(nextBtn));
		elementUtil.doClick(buyBtn);
		//System.out.println(elementUtil.isElementDisplayed(nextBtn));
		return elementUtil.isElementDisplayed(nextBtn);
	}
	

	public boolean clickFreeBtn() {
		elementUtil.getElement(freeBtn);
		elementUtil.doClick(freeBtn);
		return elementUtil.isElementDisplayed(signUpBtn);
	}
	
	
	//apps.apple.com
	//play.google.com
	

}

