package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_I_Ex {
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "Testing";
	String validEmail = "automation@gmail.com";
	String validPassword = "123123";

	@BeforeClass(description = "Chạy trước cho tất cả các test bên dưới")
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod(description = "Chạy trước cho mỗi test bên dưới")
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com");		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	}

	/* @Test */
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}

	/* @Test */
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");	
	}
	
	/* @Test */
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");	
	}
	
	 @Test 
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");		
	}
	
	/* @Test */
	public void TC_05_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Cách 1: Dùng hàm assertTrue(điều kiện) -> locator được hiển thị (isDisplayed)
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
		// Cách 2: Dùng hàm assertEquals(điều kiện 1, điều kiện 2) -> getText() - actual result/ expected result		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
