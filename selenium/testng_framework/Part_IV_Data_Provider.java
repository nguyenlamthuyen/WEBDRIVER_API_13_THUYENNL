package testng_framework;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_IV_Data_Provider {
	WebDriver driver;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

//		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "user_pass")
	public void TC_01_LoginToSystem(String username, String password) throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" }, { "selenium_11_03@gmail.com", "111111" } };
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
