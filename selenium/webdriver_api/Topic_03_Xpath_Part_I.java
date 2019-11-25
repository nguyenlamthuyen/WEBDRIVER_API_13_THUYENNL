package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_I {
	WebDriver driver;
	String username = "mngr231005";
	String password = "duvabyq";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		System.out.println("Mở ra trang AUT");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Click vào My Account link ở dưới footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
	}

	@Test
	public void TC_02_Start_With() {
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String welcomeMsgID =  driver.findElement(By.xpath("//td[starts-with(text(),'Manger Id :')]")).getText();
		System.out.println("Text = " + welcomeMsgID);
		Assert.assertTrue(welcomeMsgID.contains(username));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + username + "']")).isDisplayed());
	}
 

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
