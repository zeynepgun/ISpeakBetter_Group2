package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.github.javafaker.Faker;

import base.BasePage;
import util.ElementUtil;

public class SignUpPage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	Faker faker = new Faker();

//Constructor
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	By signUpLink = By.id("cmdSignupLink");
	By firstName = By.id("fname");
	By lastName = By.id("lname");
	By email = By.id("email");
	By password = By.id("password");
	By confirmPwd = By.id("confirmPass");
	By country = By.id("timezone");
	By mobileNmr = By.id("mobile");
	By referralCode = By.xpath("//input[@placeholder='Referral Code']");
	By acceptCbox = By.id("check_term");
	By signUpBtn = By.id("cmdSignup");
	By update = By.id("check_update");
	By nativeL = By.id("getnativelang");
	By okBtn = By.id("dialogbox-btn-0");
	By faceBk = By.linkText("Facebook");
	By faceEmail = By.id("email");
	By facePwd = By.id("pass");
	By faceBtn = By.id("loginbutton");
	By error = By.xpath("//div[text()='Feature Unavailable: Facebook Login is currently unavailable for this app.']");
	By errorOk = By.xpath("//button[@type='submit']");
    By alertMessage = By.id("alertmsg");
    
    
    //Actions
	public void registerWithValidCredential() throws InterruptedException {
		FillMandatoryFields(faker.internet().emailAddress(),"12345");
		FillNonMandatoryFields();
		FillLastFieldsAndSelectLanguage();
	}

	public String signUpWithFacebook() throws InterruptedException {
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		elementUtil.waitForElementPresentBy(signUpLink);
		elementUtil.waitForElementPresentBy(faceBk);
		elementUtil.doClick(faceBk);
		elementUtil.doSendKeys(faceEmail, "yellowpink663@gmail.com");
		elementUtil.doSendKeys(facePwd, "pyellow145&");
		elementUtil.doClick(faceBtn);
		return driver.getTitle();
	}

	public String registerWithInvalidEmail(String email) {
		FillMandatoryFields(email,"12345");
		FillNonMandatoryFields();
		driver.findElement(acceptCbox).click();
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
        return driver.findElement(alertMessage).getText();
	}

	public String registerWithExistingEmailAddress() throws InterruptedException {
		FillMandatoryFields("aliveli@gmail.com", "12345");
		FillNonMandatoryFields();
		driver.findElement(acceptCbox).click();
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
        return driver.findElement(alertMessage).getText();
	}

	public void registerWithMandotoryField() throws InterruptedException {
		FillMandatoryFields(faker.internet().emailAddress(),"12345");
		FillLastFieldsAndSelectLanguage();
	}

	public void registerWithoutAlphanumericPwd() throws InterruptedException {
        FillMandatoryFields(faker.internet().emailAddress(), "HelloWorld");
		FillNonMandatoryFields();
		FillLastFieldsAndSelectLanguage();
	}
	
	public void FillMandatoryFields(String e_mail, String pwd) {
		
		elementUtil.waitForElementVisible(signUpLink);
		elementUtil.doClick(signUpLink);
		elementUtil.waitForElementPresentBy(signUpLink);
		elementUtil.waitForElementPresentBy(firstName);
		elementUtil.doSendKeys(firstName, faker.name().firstName());
		driver.findElement(lastName).click();
		elementUtil.doSendKeys(lastName, faker.name().lastName());
		driver.findElement(email).click();
		driver.findElement(email).sendKeys(e_mail);
		driver.findElement(password).click();
		elementUtil.doSendKeys(password, pwd);
		driver.findElement(confirmPwd).click();
		elementUtil.doSendKeys(confirmPwd, pwd);
		elementUtil.selectDropDownByText(country, "America - America/New_York");
		driver.findElement(mobileNmr).click();
	}
	
	public void FillNonMandatoryFields() {
		driver.findElement(mobileNmr).click();
		elementUtil.doSendKeys(mobileNmr, faker.phoneNumber().cellPhone());
		driver.findElement(referralCode).click();
		elementUtil.doSendKeys(referralCode, "412");
	}
	
	public void FillLastFieldsAndSelectLanguage() {
		driver.findElement(acceptCbox).click();
		driver.findElement(update).click();
		driver.findElement(signUpBtn).click();
		elementUtil.waitForElementVisible(nativeL);
		elementUtil.waitForElementPresentBy(nativeL);
		elementUtil.selectDropDownByText(nativeL, "Arabic");
		driver.findElement(okBtn).click();
	}
}
