package util;


import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.google.common.base.Function;

//import base.BasePage;
import base.BasePage;


public class ElementUtil extends BasePage {

    WebDriver driver;
    WebDriverWait wait;
    
    
    //Constructor
    public ElementUtil (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
    
    
    /**
     * Get Element Explicit , Fluent Wait
     * 
     */

    public WebElement getElement(By locator){
        waitForElementPresentBy(locator);

        WebElement element = null;
        try{
            element = driver.findElement(locator);
            if(flash.equalsIgnoreCase("yes")){
                JavaScriptUtil.flash(element, driver);
            
           }
    } catch (Exception e) {
            System.out.println("some exception occured while creating webelement "+ locator);
        }
        return element;
    }
    
    
    
    public WebElement getElementFluentWait(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
				// .ignoring(StaleElementReferenceException.class)
				// .ignoring(ElementClickInterceptedException.class)
				.withMessage("WebDriver could not find the element in 20 seconds, timeout");

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}
    
    /**
	 * 
	 * WaitForElement Methods
	 * 
	 */

    public void waitForElementPresentBy(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
    
    public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
    
    /**
     *  Get page title, url
	 * 
	 */
    
    public void launchURL(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println("an exception occured while launching URL");
		}

	}
    
    public String doGetPageUrl() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting URL...");
		}
		return null;
	}
    
    public String waitForGetPageTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
        return driver.getTitle();
    }
    
    /**
	 *  Click, SendKeys, GetText
	 *
	 */

    public void doClick(By locator){
        try{
            getElement(locator).click();
        }
        catch(Exception e){
            System.out.println("Some exception occured while click on  webelement " +locator);
        }
    }

    public void doSendKeys(By locator, String value){
        try{
            getElement(locator).clear();
            getElement(locator).sendKeys(value);
        }
        catch(Exception e){
            System.out.println("Some exception occured while sending to  webelement " + locator);
        }
    }

    public String doGetText(By locator){
        String text = null;
        try {
            text = getElement(locator).getText();
        } catch (Exception e) {
            System.out.println("some exception occured while get text to webelement "+ locator);
        }
        return text;
    }

    
    /**
	 *  Visibility Methods
	 *
	 */
    
    

    public boolean isElementDisplayed(By locator){
        try{
            return getElement(locator).isDisplayed();
        }catch (Exception e) {
            System.out.println("some exception occured while checking webelement displayed "+ locator);
            return false;
        }
    }

    public boolean isElementEnabled(By locator){
        try{
            return getElement(locator).isEnabled();
        }catch (Exception e) {
            System.out.println("some exception occured while checking webelement enabled "+ locator);
            return false;
        }
    }
    public boolean isElementSelected(By locator){
    	try{
    	return getElement(locator).isSelected();
    	}catch (Exception e) {
            System.out.println("some exception occured while checking webelement selected "+ locator);
            return false;
        }
    }
    
    /**
	 *  Alert
	 *
	 */
    
    public static String getAlertText(WebDriver driver) {

		Alert alert = driver.switchTo().alert();

		String alertText = alert.getText();

		alert.accept();

		return alertText;

	}
    
    /**
	 *  DropDown 
	 *
	 */
    
    public void selectDropDownByValue(By locator, String value) {
		
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public String getDropDownByValueText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
		String text = select.getFirstSelectedOption().getText();
		return text;
	}

	public void selectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void selectDropDownByText(By locator, String text) {
		
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
    
    
   public String selectSingleValue(WebDriver driver, By locator, String value) {
		
		List<WebElement> dropList = driver.findElements(locator);
		//System.out.println(dropList.size());
		
		for(int i= 0; i<dropList.size(); i++) {
			String text = dropList.get(i).getText();
			//System.out.println(text);
			
			try {
				if(!text.isEmpty()) {  //use not equal to select any value
					if(text.equals(value)) {
						//System.out.println(text);
						dropList.get(i).click();
						break;
					}
				}
			} catch (Exception e) {
				

			}
		}
		return value;
	}
   
   
   
   
}

