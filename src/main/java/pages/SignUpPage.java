package pages;

import java.util.Properties;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

import base.BasePage;
import util.ElementUtil;

public class SignUpPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	Faker faker = new Faker();
	
	//Constructor
	public  SignUpPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	

	By signUpLink = By.id("cmdSignupLink");
	
	By firstName= By.id("fname");
	
	By lastName= By.id("lname");
	
	By email= By.id("email");
	
	By password= By.id("password");
	
	By confirmPwd = By.id("confirmPass");
	
	By country = By.id("timezone");
	
	By mobileNmr= By.id("mobile");
	
	By referralCode= By.xpath("//input[@placeholder='Referral Code']");
	
	By acceptCbox =By.id("check_term");
	
	By signUpBtn= By.id("cmdSignup");
	
	By update = By.id("check_update");
	
	By nativeL = By.id("getnativelang");
	
	By okBtn = By.id("dialogbox-btn-0");
	
	By faceBk= By.linkText("Facebook");
	
	By faceEmail = By.id("email");
	
	By facePwd = By.id("pass");
	
	By faceBtn = By.id("loginbutton");
	
	By error = By.xpath("//div[text()='Feature Unavailable: Facebook Login is currently unavailable for this app.']");
	
	By errorOk = By.xpath("//button[@type='submit']");
	
	
	
	
	
	
	//Actions
	public void registerWithValidCredential() throws InterruptedException {
		
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		
		elementUtil.waitForElementPresentBy(signUpLink);
		
		elementUtil.waitForElementPresentBy(firstName);
		
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		
		driver.findElement(lastName).click();
		//elementUtil.doSendKeys(lastName, "Veli");
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		//elementUtil.doSendKeys(email, "aliveli@gmail.com");
		driver.findElement(email).sendKeys(faker.internet().emailAddress());
		driver.findElement(password).click();
		elementUtil.doSendKeys(password,"12345");
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, "12345");
		driver.findElement(country).click();
		elementUtil.selectDropDownByText(country, "America - America/New_York");
		driver.findElement(mobileNmr).click();
		elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		driver.findElement(referralCode).click();
		elementUtil.doSendKeys(referralCode, "412");
		driver.findElement(acceptCbox).click();
		
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		

        elementUtil.waitForElementPresentBy(nativeL);
		
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
		
		
	//	Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
		
		
	}
	
	public void signUpWithFacebook() throws InterruptedException{
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		elementUtil.waitForElementPresentBy(signUpLink);
		elementUtil.waitForElementPresentBy(faceBk);
		
		elementUtil.doClick(faceBk);
		elementUtil.doSendKeys(faceEmail, "yellowpink663@gmail.com");
		elementUtil.doSendKeys(facePwd, "pyellow145&");
		elementUtil.doClick(faceBtn);
	    String text =	driver.findElement(error).getText();
	    Assert.assertEquals(text, "Feature Unavailable: Facebook Login is currently unavailable for this app.");
	    elementUtil.doClick(errorOk);
		
		
	
		//String alert = elementUtil.getAlertText(driver);
		//Assert.assertEquals(alert, "Feature Unavailable: Facebook Login is currently unavailable for this app.");
		
	
		
	}
	
	public WebElement clickOnSignLink() throws InterruptedException {
		return driver.findElement(signUpLink);
	}
	
	public WebElement getEmail() {
	
		return driver.findElement(email);
	}
	
	public WebElement clickOnSignUpBtn() {
		return driver.findElement(signUpBtn);
	}
	
	public void registerWithInvalidEmail() {
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		
		elementUtil.waitForElementPresentBy(signUpLink);
		
		elementUtil.waitForElementPresentBy(firstName);
		
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		
		driver.findElement(lastName).click();
		//elementUtil.doSendKeys(lastName, "Veli");
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		//elementUtil.doSendKeys(email, "aliveli@gmail.com");
		driver.findElement(email).sendKeys(faker.internet().emailAddress());
		driver.findElement(password).click();
		elementUtil.doSendKeys(password,"12345");
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, "12345");
		driver.findElement(country).click();
		elementUtil.selectDropDownByText(country, "America - America/New_York");
		driver.findElement(mobileNmr).click();
		elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		driver.findElement(referralCode).click();
		elementUtil.doSendKeys(referralCode, "412");
		driver.findElement(acceptCbox).click();
		
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		

        elementUtil.waitForElementPresentBy(nativeL);
		
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
		
		
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
	}
	
	public void registerWithExistingEmailAddress() throws InterruptedException{
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		
		elementUtil.waitForElementPresentBy(signUpLink);
		
		elementUtil.waitForElementPresentBy(firstName);
		
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		
		driver.findElement(lastName).click();
		//elementUtil.doSendKeys(lastName, "Veli");
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		elementUtil.doSendKeys(email, "aliveli@gmail.com");
		//driver.findElement(email).sendKeys(faker.internet().emailAddress());
		driver.findElement(password).click();
		elementUtil.doSendKeys(password,"12345");
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, "12345");
		driver.findElement(country).click();
		elementUtil.selectDropDownByText(country, "America - America/New_York");
		driver.findElement(mobileNmr).click();
		elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		driver.findElement(referralCode).click();
		elementUtil.doSendKeys(referralCode, "412");
		driver.findElement(acceptCbox).click();
		
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		

       elementUtil.waitForElementPresentBy(nativeL);
		
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
		
		
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
		
	}
	
	public void registerWithMandotoryField() throws InterruptedException{
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		
		elementUtil.waitForElementPresentBy(signUpLink);
		
		elementUtil.waitForElementPresentBy(firstName);
		
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		
		driver.findElement(lastName).click();
		//elementUtil.doSendKeys(lastName, "Veli");
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		//elementUtil.doSendKeys(email, "aliveli@gmail.com");
		driver.findElement(email).sendKeys(faker.internet().emailAddress());
		driver.findElement(password).click();
		elementUtil.doSendKeys(password,"12345");
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, "12345");
		//driver.findElement(country).click();
		//elementUtil.selectDropDownByText(country, "America - America/New_York");
		//driver.findElement(mobileNmr).click();
		//elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		//driver.findElement(referralCode).click();
		//elementUtil.doSendKeys(referralCode, "412");
		
		driver.findElement(acceptCbox).click();
		
		
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		
		elementUtil.waitForElementVisible(nativeL);
        elementUtil.waitForElementPresentBy(nativeL);
		
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
		
		
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
	}
	
	public void registerWithoutAlphanumericPwd() throws InterruptedException{
		
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		
		elementUtil.waitForElementPresentBy(signUpLink);
		
		elementUtil.waitForElementPresentBy(firstName);
		
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		
		driver.findElement(lastName).click();
		//elementUtil.doSendKeys(lastName, "Veli");
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		//elementUtil.doSendKeys(email, "aliveli@gmail.com");
		driver.findElement(email).sendKeys(faker.internet().emailAddress());
		driver.findElement(password).click();
		elementUtil.doSendKeys(password,"HelloWorld");
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, "HelloWorld");
		driver.findElement(country).click();
		elementUtil.selectDropDownByText(country, "America - America/New_York");
		driver.findElement(mobileNmr).click();
		elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		driver.findElement(referralCode).click();
		elementUtil.doSendKeys(referralCode, "412");
		driver.findElement(acceptCbox).click();
		
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		

        elementUtil.waitForElementPresentBy(nativeL);
		
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
		
		
		Assert.assertEquals(driver.getTitle(), "ISpeakBetter Online English Center - PAID STUDENTS");
		
	}
	
	
	

		
		
		
		
		
		
	
	
}
