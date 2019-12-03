package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List_III {
	WebDriver driver;
	Select selectDay;
	Select selectMonth;
	Select selectYear;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com");
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		selectDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(selectDay.getOptions().size(), 32);
		selectMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(selectMonth.getOptions().size(), 13);
		selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(selectYear.getOptions().size(), 112);
		
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Nancy");
		driver.findElement(By.id("LastName")).sendKeys("Nguyen");
		selectDay.selectByVisibleText("28");
		selectMonth.selectByVisibleText("March");
		selectYear.selectByVisibleText("1991");
		driver.findElement(By.id("Email")).sendKeys("AutomationTesting307@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("thuyen2803");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("thuyen2803");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='ico-account']")).getText(), "My account");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='ico-account']")).getText(), "My account");
	}

	/* @Test */
	public void TC_02_() {
		driver.get("");
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
