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

public class Part_III_Priority {

	// Testcases
	@Test(priority = 1)
	public void Priority_01_Create_New_Customer() {
		System.out.println("Run test 01");
	}

	@Test(priority = 2)
	public void Priority_02_Edit_Customer() {
		System.out.println("Run test 02");
	}

	@Test(description = "Create new Customer")
	public void Create_New_Account() {
		System.out.println("Run test 03");
	}

	@Test(description = "Edit Account")
	public void Edit_Account() {
		System.out.println("Run test 04");
	}

	@Test(enabled = false)
	public void Delete_Account() {
		System.out.println("Run test 05");
	}

	@Test(priority = 6)
	public void Delete_Customer() {
		System.out.println("Run test 06");
		Assert.assertTrue(false);
	}

}
