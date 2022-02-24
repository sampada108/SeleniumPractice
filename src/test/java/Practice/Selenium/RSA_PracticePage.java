package Practice.Selenium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import resources.BaseClass;

public class RSA_PracticePage extends BaseClass {

	
	@BeforeClass
	public void Initialize()
	{
		initializeDriver("Chrome");
		openApplication("https://rahulshettyacademy.com/AutomationPractice");
	}
	
	@Test
	public void RadioButtonTest(){
		
		System.out.println(driver.getTitle());
		driver.findElement(By.cssSelector("input[value='radio1']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='radio1']")).isSelected());
		System.out.println("Selected");
		
		driver.findElement(By.cssSelector("input[value='radio2']")).click();
		Assert.assertTrue(!driver.findElement(By.cssSelector("input[value='radio1']")).isSelected());
		System.out.println("De-selected");
		
	}
	
	@Test 
	public void SuggessionClassTest(){
		
		String ExpectedCountry = "India";
		String temp;
		driver.findElement(By.id("autocomplete")).sendKeys("ind");
		List <WebElement> countryList = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));
		System.out.println(countryList.size());
		for (int i=0; i<countryList.size(); i++) {
			temp = countryList.get(i).getText();
			System.out.println(temp);
			if(temp.equalsIgnoreCase(ExpectedCountry)) {
				int j = i;
				System.out.println(i);
				while(j>=0) {
					driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
					j--;
				}
				driver.findElement(By.id("autocomplete")).sendKeys(Keys.ENTER);
				break;
			}
		}
		Assert.assertEquals(driver.findElement(By.id("autocomplete")).getAttribute("value"),ExpectedCountry);
		System.out.println("Country " + ExpectedCountry + " is selected");
	}

	@Test 
	public void DropDown_CheckboxTest(){
		driver.findElement(By.name("checkBoxOption2")).click();
		String optionChecked = driver.findElement(By.name("checkBoxOption2")).getAttribute("value");
		Assert.assertTrue(driver.findElement(By.name("checkBoxOption2")).isSelected());
		
		// for select tag use Select class
		Select s = new Select(driver.findElement(By.id("dropdown-class-example"))); 
		s.selectByValue(optionChecked);
		
		String optionSelected = s.getFirstSelectedOption().getText();
		Assert.assertTrue(optionChecked.equalsIgnoreCase(optionSelected));
		
		driver.findElement(By.name("checkBoxOption2")).click();
		Assert.assertFalse(driver.findElement(By.name("checkBoxOption2")).isSelected());
		
		s.selectByVisibleText("Option3");
		String optionSelect = s.getFirstSelectedOption().getText();
		Assert.assertTrue("Option3".equalsIgnoreCase(optionSelect));
		
	}
	
	@Test
	public void SwitchWindowTabTest(){
		
		//driver.findElement(By.id("openwindow")).click();   // for window
		driver.findElement(By.id("opentab")).click();   // for tab
		Set <String> winHandles = driver.getWindowHandles();
		Iterator<String> itr = winHandles.iterator();
		String mainWinHandle = itr.next();
		String childWinHandle = itr.next();
		
		driver.switchTo().window(childWinHandle);
		//Assert.assertTrue(driver.findElement(By.
				//partialLinkText("Selenium Webdriver with Java Basics")).isDisplayed());  // for window
		Assert.assertTrue(driver.findElement(By.
				xpath("//h2[contains(text(),'Featured Courses')]")).getText().equals("Featured Courses")); // for tab
		driver.switchTo().window(mainWinHandle);
		Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());	
	}
	
	@Test
	public void AlertTest() {
		
		String text = "Main";
		driver.findElement(By.id("alertbtn")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("input[id='name']")).sendKeys(text);
		driver.findElement(By.id("confirmbtn")).click();
		Alert alert1 = driver.switchTo().alert();
		String alertText = alert1.getText();
		Assert.assertTrue(alertText.contains(text));
		alert1.dismiss();
	}
	
	@Test
	public void ElementDisplayedExampleTest(){
		
		driver.findElement(By.id("displayed-text")).sendKeys("hello");
		driver.findElement(By.id("hide-textbox")).click();

		Assert.assertFalse(driver.findElement(By.id("displayed-text")).isDisplayed());
		driver.findElement(By.id("show-textbox")).click();

		Assert.assertTrue(driver.findElement(By.id("displayed-text")).isDisplayed());
		System.out.println(driver.findElement(By.id("displayed-text")).getAttribute("value"));
	}
	
	@Test
	public void WebTableTest() {
		
		List<String> searchCourses = new ArrayList<String>();
		searchCourses.add("Learn SQL in Practical + Database Testing from Scratch");
		searchCourses.add("WebServices / REST API Testing with SoapUI")	;
		searchCourses.add("Advanced Selenium Framework Pageobject, TestNG, Maven, Jenkins,C");
		
		List<String> coursePrices = new ArrayList<String>();
		
		String[] headings = {"Instructor", "Course", "Price"};
		
		WebElement Table = driver.findElement(By.xpath("//table[@class='table-display']/tbody"));
		
		List<WebElement> headingsActual = Table.findElements(By.xpath("tr[1]/th"));
		for(int i=0; i< headingsActual.size(); i++)
			System.out.println(headingsActual.get(i).getText());
		
		for(int i=0; i< headingsActual.size(); i++) {
			for(int j=i; j<headings.length; j++) {
				if(headingsActual.get(i).getText().trim().equals(headings[j])) {
					System.out.println(headingsActual.get(i).getText() + " " + headings[j]);
				}
				break;
			}
		}
		
		// check courses and find price for each course
		List<WebElement> Courses = Table.findElements(By.xpath("tr/td[2]"));	
		List<WebElement> prices = Table.findElements(By.xpath("tr/td[3]"));
		
		for (int i=0; i<searchCourses.size(); i++) {
			for (int j=0; j<Courses.size(); j++) {
				if(searchCourses.get(i).equals(Courses.get(j).getText().trim())) {
					String price = prices.get(j).getText();
					coursePrices.add(price);
					break;
				}
			}
		}
		for (int i=0; i<coursePrices.size(); i++) {
			System.out.println(coursePrices.get(i));
		}
	}

	@Test
	public void webTableFixedHeaderWithScrollTest() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);
		js.executeScript("document.querySelector('.tableFixHead').scrollTop=300");
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void TearDown()
	{
		closeApplication();
	}	
}
