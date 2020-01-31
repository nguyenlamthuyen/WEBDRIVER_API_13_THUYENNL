package webdriver_api;

import java.util.concurrent.TimeUnit;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_14_Wait_Part_III_Static {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		// Apply để chờ cho element hiển thị -> Tương tác vào -> findElement/ findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Apply để chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Static() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start sleep - " + getCurrentTime());
		// 1000 ms = 1s 
		// 1 - Nếu như page được load xong trong 3s thì sẽ mất 2s bị lãng phí
		// 2 - Nếu như page được load xong trong vòng 10s hoặc hơn thì bị thiếu thời gian -> Fail testcase
		// Ko flexible
		// Special case: Internet Explorer    
		System.out.println("End sleep - " + getCurrentTime());
		Thread.sleep(5000);
		driver.findElement(By.id("search_query_top")).sendKeys("Automation");		
	}
	
	public String getCurrentTime() {
		Date date = new Date(); 
		return date.toString(); 
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
