package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait waitExplicit; 
	boolean status;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_ElementDisplayedOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI + có trong DOM: PASSED
		// Chờ cho element displayed/ visible
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		// Kiểm tra element displayed
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();
		System.out.println("Element có hiển thị trên UI + có trong DOM = " + status);
		
		// ĐK 1 - Element không hiển thị trên UI + có trong DOM: PASSED
		// Chờ cho element undisplayed/invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + có trong DOM = " + status);
		
		// ĐK 1 - Element không hiển thị trên UI + không có trong DOM: FAILED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + không có trong DOM = " + status);
	}

//	@Test
	public void TC_02_ElementUndisplayedOrInVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI + có trong DOM: FAILED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		// ĐK 1 - Element không hiển thị trên UI + có trong DOM: PASSED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// ĐK 1 - Element không hiển thị trên UI + không có trong DOM: PASSED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
	}
	
	@Test
	public void TC_03_ElementPresence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI và có trong DOM: PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		// ĐK 1 - Element không hiển thị trên UI và có trong DOM: PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// ĐK 1 - Element không hiển thị trên UI và không có trong DOM: FAILED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
