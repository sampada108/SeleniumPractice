package Practice.Selenium;

//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class chromeOptionsDemo {

	public static void main(String[] args) throws InterruptedException {
		
		  // Add more capabilities to Chrome options 
		  ChromeOptions optionch = new ChromeOptions(); 
		  optionch.setCapability("ACCEPT_INSECURE_CERTS", true);
		  optionch.setCapability("ACCEPT_SSL_CERTS", true);
		  
		  String userDir = System.getProperty("user.dir");
		  System.setProperty("webdriver.chrome.driver",
		  userDir+"//src//main//java//resources//chromedriver.exe"); 
		  ChromeDriver driver = new ChromeDriver(optionch); 
		  driver.get("https://www.google.com");
		  driver.manage().window().maximize();  
		  Thread.sleep(1500);
		  System.out.println(driver.getTitle());
		  driver.manage().deleteAllCookies();
		  driver.close();
		  
		  Thread.sleep(2000);
		  
		FirefoxOptions op = new FirefoxOptions();
		op.setCapability("ACCEPT_INSECURE_CERTS", true);
		op.setCapability("ACCEPT_SSL_CERTS", true);

		System.setProperty("webdriver.gecko.driver", userDir+"//src//main//java//resources//geckodriver.exe");
		FirefoxDriver driverfx = new FirefoxDriver(op);
		driverfx.get("https://www.google.com");
		driverfx.manage().window().maximize();
		Thread.sleep(1500);
		System.out.println(driverfx.getTitle());
		driverfx.manage().deleteAllCookies();
		driverfx.close();

	}
}
