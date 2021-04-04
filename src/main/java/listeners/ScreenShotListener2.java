package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenShotListener2 implements ITestListener {
	WebDriver driver = null;

	public void onTestFailure(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
        System.out.println("failed test name is " + result.getTestName());
        try {
			System.out.println(result.getTestClass().getRealClass().getDeclaredField("driver").getName());
		}  catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println("ne bulamadi abaca");
			e2.printStackTrace();
		}
		if (!result.isSuccess()) {
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
						.get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "/target/surefire-reports";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
						+ "' height='100' width='100'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
