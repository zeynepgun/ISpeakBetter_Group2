package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class BasePage{

    public WebDriver driver;
    public static String flash;


    public WebDriver initialize_driver(){ 
    	//load the configuration properties file with ConfigReader class
        ConfigReader.readProperties("./src/main/java/config/config.properties");
        String browser = ConfigReader.getProperty("browser");
        flash = ConfigReader.getProperty("elementflash");
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(ConfigReader.getChromeOptions());
        }
        else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(ConfigReader.getFireFoxOptions());      
        }else{
            throw new RuntimeException("Invalid browser name!");
        }
  
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("url"));
        
        return driver;
    }
    
    
    public void tearDown(){
        if(driver != null){
        	try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("some exception occured while quitting browser");
            }
        }
    }

   
}
