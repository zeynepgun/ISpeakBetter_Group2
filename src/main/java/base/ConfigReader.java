package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ConfigReader {

    static Properties properties;
    
    public static ChromeOptions cOptions;
	public static FirefoxOptions fOptions;
	
    public static void readProperties(String filePath) {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){

        return properties.getProperty(key);
    }
    
    public static ChromeOptions getChromeOptions(){
		cOptions = new ChromeOptions();
		if(properties.get("incognito").equals("yes"))
			cOptions.addArguments("--incognito");
		if (properties.getProperty("headless").equals("yes"))
			cOptions.addArguments("--headless");
		
		return cOptions;
	}
	
	public static FirefoxOptions getFireFoxOptions(){
		fOptions = new FirefoxOptions();
		if(properties.get("incognito").equals("yes"))
			fOptions.addArguments("--incognito");
		if (properties.getProperty("headless").equals("yes"))
			fOptions.addArguments("--headless");
		
		return fOptions;
	}

}