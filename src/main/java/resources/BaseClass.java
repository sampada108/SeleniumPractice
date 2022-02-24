package resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public WebDriver driver;
	String userDir = System.getProperty("user.dir");
	
	//Initialize driver 
	public void initializeDriver(String browserName)
	{
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	// Get URL and navigate to page and maximize
	public void openApplication(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	// Close all browsers
	public void closeApplication()
	{
		driver.quit();
	}

}
