package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox_Alert {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		javascript = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Button
		elementEnabled("//button[@id='button-disabled']");
		
		// Checkbox
		elementEnabled("//input[@id='development']");
		elementSelected("//input[@id='development']");
		
		// Radio button
		elementEnabled("//input[@id='under_18']");
		elementSelected("//input[@id='under_18']");
	}

//	@Test
	public void TC_02_ClickByJS() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/");
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Desktops ']")));
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
	}
	
//	@Test
	public void TC_03_Checkbox() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkboxLabel = "//label[text()='Luggage compartment cover']";
		String checkboxInput = "//label[text()='Luggage compartment cover']//preceding-sibling::input";
		
		// Nếu như radio/ checkbox mà bị ẩn thì User ko thể click
//		driver.findElement(By.xpath(checkboxLabel)).click();
		clickByJS(checkboxInput);
		Thread.sleep(2000);
		
		elementSelected(checkboxInput);
		
		Assert.assertTrue(isELementSelected(checkboxInput));
		
//		driver.findElement(By.xpath(checkboxLabel)).click();
		clickByJS(checkboxInput);
		
		Assert.assertFalse(isELementSelected(checkboxInput));
	}
	
//	@Test
	public void TC_04_CheckAndUncheckToCheckbox() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		String newsletterCheckbox = "//input[@id='Newsletter']";
		
		// Click vào checkbox -> Unchecked
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertFalse(isELementSelected(newsletterCheckbox));
		
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertTrue(isELementSelected(newsletterCheckbox));
		
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isELementSelected(newsletterCheckbox));
		
		// Uncheck
		uncheckToCheckbox(newsletterCheckbox);
		Assert.assertFalse(isELementSelected(newsletterCheckbox));
	}
	
//	@Test
	public void TC_05_Handle_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		String fullName = "Automation Tester";
		
		// ACCEPT ALERT
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		Thread.sleep(2000);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");
		
		// CANCEL ALERT
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(2000);
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");
		
		// SNEDKEY TO ALERT
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(2000);
		
		alert = driver.switchTo().alert();
		alert.sendKeys(fullName);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: "+ fullName);
	}
	
	@Test
	public void TC_06_Authentication_Alert() {
		String usernameAndPass = "admin";
		String url = "https://the-internet.herokuapp.com/basic_auth";
		url = "https://" + usernameAndPass + ":" + usernameAndPass + "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
	}
	
	public void clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);
	}

	public void elementEnabled(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
		}else {
			System.out.println("Element is disabled");
		}
	}
	
	public void elementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			System.out.println("Element is selected");
		}else {
			System.out.println("Element is deselected");
		}
	}
	
	public boolean isELementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
