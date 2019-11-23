package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("");
		
		// Email textbox
		
		// Nếu chỉ tương tác lên element 1 lần thì ko cần khai báo biến
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc.vn@gmail.com");
		
		// Nếu element này được thao tác/ sử dụng nhiều lần 
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		emailTextbox.clear();
		/* Thread.sleep(3000); */
		emailTextbox.sendKeys("automationfc.vn@gmail.com");
		/* Thread.sleep(3000); */
		Assert.assertTrue(emailTextbox.isDisplayed());
		
		//=== THAO TÁC VS >= 2  ELEMENT ===
		
		List <WebElement> links = driver.findElements(By.xpath("//a"));
		
		// === WEB ELEMENT METHOD
	}

	@Test
	public void TC_02_() {
		driver.get("");
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
