package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_IV_Implicit_Explicit {
	WebDriver driver;
	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingImageBy = By.xpath("//div[@id='loading']//img");
	By helloworldTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Implicit_Wait() {
		// Mở app: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Check cho element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());

		// Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());
		
		// Để Loading này biến mất (tới 5s) - dù nhanh hay chậm
		
		// Helloworld text được hiển thị: Visible (Displayed)
		System.out.println("Start helloworld - " + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld - " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		
		// 4 - Hiển thị Inprogress bar (Loading bar): Invisible (Undisplayed)
		
		
		
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
