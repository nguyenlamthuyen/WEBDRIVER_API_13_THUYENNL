package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_II {
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
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");

		System.out.println("Step 02 - Click My Account link");
		/* driver.findElement(By.xpath("//a[@title='My Account']")).click(); */
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		System.out.println("Step 03 - Verify Login Page Url");
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' button");
		// css
		/* driver.findElement(By.cssSelector("div[class='buttons-set'] a[title='Create an Account']")).click(); */
		// xpath	
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(1000,1000);");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click(); 

		System.out.println("Step 05 - Verify 'Register Page' Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test 
	public void TC_02_() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Verify Title of the Login Page");
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");
		
		System.out.println("Step 04 - Click Create an account button");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(1000,1000);");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();

		System.out.println("Step 05 - Verify Title of the Register Page");
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");
	}
	
	@Test 
	public void TC_03_() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Click Create an account button");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(1000,1000);");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		
		System.out.println("Step 04 - Verify Url of the Register Page");
		String urlRegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		System.out.println("Step 05 - Back to Login Page");
		driver.navigate().back();

		System.out.println("Step 06 - Verify Url of the Login Page");
		String urlLoginPage = driver.getCurrentUrl();
		Assert.assertEquals(urlLoginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 07 - Forward to Register Page");
		driver.navigate().forward();
		
		System.out.println("Step 05 - Verify Title of the Register Page");
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");			
	}
	
	@Test 
	public void TC_04_() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Login Page contains Login or Create an Account ");
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		System.out.println("Step 04 - Click Create an account button");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(1000,1000);");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Register Page contains Crate an Account");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
