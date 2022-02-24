package Practice.Selenium;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.BaseClass;

public class MultipleWindowsDemo extends BaseClass {

	@BeforeTest
	public void initialize() {
		initializeDriver("chrome");
		openApplication("https://the-internet.herokuapp.com/windows");

	}

	@Test
	public void MultipleWindowTest() {

		driver.findElement(By.xpath("//div[@class='example']/a")).click();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parentWindow = it.next();
		String childWindow = it.next();

		// switch to child window
		driver.switchTo().window(childWindow);
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());

		// switch to parent window
		driver.switchTo().window(parentWindow);
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
	}

	@AfterTest()
	public void tearDown() {
		driver.quit();
	}
}
