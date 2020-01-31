package webdriver_api;

import java.io.IOException;
import java.util.List;
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

public class Topic_13_Upload_Files {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String pic01 = projectPath + "\\uploadFiles\\1.png";
	String pic02 = projectPath + "\\uploadFiles\\2.png";
	String pic03 = projectPath + "\\uploadFiles\\3.png";
	String pic04 = projectPath + "\\uploadFiles\\4.png";
	
	String chromeAutoIT = projectPath + "\\uploadAutoIT\\chrome.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Sendkeys() throws InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		uploadFile.sendKeys(pic01);

		Thread.sleep(4000);

		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='1.png']")).isDisplayed());
	}

	@Test
	public void TC_02_SendKeys() throws InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		uploadFile.sendKeys(pic01 + "\n" + pic02 + "\n" + pic03 + "\n" + pic04);

		Thread.sleep(4000);

		List <WebElement> startButtons = finds("//table//button[@class='btn btn-primary start']");
		
		for(WebElement start : startButtons) {
			start.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='1.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='3.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='4.png']")).isDisplayed());

	}
	
	@Test
	public void TC_03_AutoIT() throws IOException, InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.cssSelector(".btn-success"));
		Thread.sleep(2000);
		uploadFile.click();
		
		// Execute runtime file (.exe/ .bat/ .msi/ .sh)
		Runtime.getRuntime().exec(new String[] { chromeAutoIT, pic01});
		
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='1.png']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Robot() throws IOException, InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.cssSelector(".btn-success"));
		Thread.sleep(2000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public List <WebElement> finds(String xpath){
		return driver.findElements(By.xpath(xpath));
	}

}
