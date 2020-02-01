package webdriver_api;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_V_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
	}

//	@Test
	public void TC_01_Found_Element() {
		driver.get("http://demo.guru99.com/");

		//Implicit
		System.out.println("---- STEP 01 Start TC_01_Found_Element: " + new Date() + " ---- ");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("---- STEP 01 End TC_01_Found_Element: " + new Date() + " ---- ");
		
		//Explicit
		System.out.println("---- STEP 02 Start TC_01_Found_Element: " + new Date() + " ---- ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='btnLogin']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("---- STEP 02 End TC_01_Found_Element: " + new Date() + " ---- ");
	}

	@Test
	public void TC_02_Not_Found_Element() {
		// Explicit Wait (Element Status)
		explicitWait = new WebDriverWait(driver, 10);

		// Implicit Wait (FindElement/s)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/");
		
		// Implicit (Not found element)
		System.out.println("---- STEP 01 Start Implicit: " + new Date() + " ---- ");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("automation_testing"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("---- STEP 01 - NHẢY VÀO CATCH ---- ");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- STEP 01 End Implicit: " + new Date() + " ---- ");

		// Explicit (Not found - tham số là web element)
		System.out.println("---- STEP 01 Explicit (WebElement): " + new Date() + " ---- ");
		try {
			WebElement emailTextbox = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("automation_testing"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("---- STEP 02 - NHẢY VÀO CATCH ---- ");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- STEP 01 Explicit (WebElement): " + new Date() + " ---- ");
		
		// Explicit (Not found - tham số là by)
		System.out.println("---- STEP 03 Explicit (By): " + new Date() + " ---- ");
		try {
			WebElement emailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("automation_testing")));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("---- STEP 02 - NHẢY VÀO CATCH ---- ");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- STEP 03 Explicit (By): " + new Date() + " ---- ");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
