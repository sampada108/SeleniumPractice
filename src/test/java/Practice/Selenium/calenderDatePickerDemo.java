package Practice.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class calenderDatePickerDemo {

	public static void main(String[] args) {

		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		
		String expectedDay= "28";
		String expectedMonth= "February";
		String expectedYear= "2022";
		
		driver.get("https://www.globalsqa.com/demo-site/datepicker/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		WebElement framelocator = 
				driver.findElement(By.xpath("//div[@class='newtabs horizontal']/div[@class='resp-tabs-container']/div[@class='single_tab_div resp-tab-content resp-tab-content-active']/p/iframe"));
		driver.switchTo().frame(framelocator);
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		String monthDate = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
		System.out.println(monthDate); 
		
		while(!(monthDate.split(" ")[0].trim().equals(expectedMonth) && monthDate.split(" ")[1].trim().equals(expectedYear))){
			
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthDate = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
		}
		System.out.println(monthDate);
		
		try
		{
			driver.findElement(By.xpath("//a[text()='"+expectedDay+"']")).click();
		}
		catch(Exception e)
		{
			System.out.println("Wrong Date= "+ expectedDay +"/"+expectedMonth+"/"+ expectedYear);
		}
		
		System.out.println(driver.findElement(By.xpath("//input[@id='datepicker']")).getAttribute("value"));
		
		driver.switchTo().defaultContent();
		
		//driver.close();
	}

}
