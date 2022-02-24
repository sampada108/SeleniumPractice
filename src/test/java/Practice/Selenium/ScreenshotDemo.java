package Practice.Selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

public class ScreenshotDemo{
	
	@Test
	public void ScreenshotDemoTest() throws IOException
	{
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(userDir+ "//src//main//java//resources//Screenshot.png"));
		
		driver.close();
		
	}

}
