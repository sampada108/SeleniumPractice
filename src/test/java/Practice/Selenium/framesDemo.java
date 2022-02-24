package Practice.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.BaseClass;

public class framesDemo extends BaseClass{
	
	
	@BeforeTest
	public void initialize() {
		initializeDriver("chrome");
		openApplication("https://demoqa.com/frames");
		
	}

	@Test
	public void framesTest() {
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		
		int frameCount = frames.size();
		System.out.println(frameCount);
		
		for (int i=0;i<frameCount; i++) {
			System.out.println(frames.get(i).getAttribute("id"));
		}
		
		driver.switchTo().frame("frame1");
		System.out.println(driver.findElement(By.cssSelector("#sampleHeading")).getText());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		System.out.println(driver.findElement(By.cssSelector("#sampleHeading")).getText());
		driver.switchTo().defaultContent();
		System.out.println(driver.findElement(By.xpath("//div[@class='main-header']")).getText());
		
	}
	
	@AfterTest()
	public void tearDown() {
		driver.close();
	}
}
