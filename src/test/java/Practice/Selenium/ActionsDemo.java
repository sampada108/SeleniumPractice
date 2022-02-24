package Practice.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionsDemo {
	
	@Test(enabled=true)
	public void mouseActionsTest() {
		
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		
		// Mousehover
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mousehover"))).perform();
		List<WebElement> mousehoverOptions = driver.findElements(By.xpath("//div[@class='mouse-hover-content']/a"));
		for(int i=0; i<mousehoverOptions.size(); i++) {
			System.out.println(mousehoverOptions.get(i).getText());
		}
		
		action.moveToElement(driver.findElement(By.id("mousehover")))
		.click().sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		
		action.contextClick(driver.findElement(By.id("autocomplete"))).perform();
		
		driver.close();
	}

	@Test 
	public void dragAndDropTest() {
		
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir+"//src//main//java//resources//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/test/drag_drop.html");
		
		WebElement source = driver.findElement(By.xpath("//*[@id='credit2']"));
		WebElement destination = driver.findElement(By.xpath("//*[@id='bank']/li"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(source, destination).perform();
		driver.close();
		
	}
}
