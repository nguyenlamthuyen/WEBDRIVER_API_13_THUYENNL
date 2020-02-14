package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_II_Group {
	
	WebDriver driver;
	
	// Pre-condition: New driver/ new browser/ new data test/..
	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
		
		driver = new ChromeDriver();
		
		Assert.assertTrue(false);
	}

	// Testcases
	@Test(groups = "user")
	public void TC_01() {
		System.out.println("Run test 01");
	}

	@Test(groups = "user")
	public void TC_02() {
		System.out.println("Run test 02");
	}

	@Test(groups = "pay")
	public void TC_03() {
		System.out.println("Run test 03");
	}

	@Test(groups = "pay")
	public void TC_04() {
		System.out.println("Run test 04");
	}

	@Test(groups = "shop")
	public void TC_05() {
		System.out.println("Run test 05");
	}

	@Test(groups = "shop")
	public void TC_06() {
		System.out.println("Run test 06");
		Assert.assertTrue(false);
	}

	// Post-condition: Close browser/ close driver/ clean data test/...
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("afterClass");
	}

}
