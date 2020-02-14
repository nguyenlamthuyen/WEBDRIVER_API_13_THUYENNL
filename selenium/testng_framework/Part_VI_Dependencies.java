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

public class Part_VI_Dependencies {

	// Testcases
	@Test()
	public void Priority_01_Create_New_Customer() {
		System.out.println("Run test 01");
	}

	@Test(dependsOnMethods = "Priority_01_Create_New_Customer")
	public void Priority_02_Edit_Customer() {
		System.out.println("Run test 02");
	}

	@Test(dependsOnMethods = "Priority_02_Edit_Customer")
	public void Create_New_Account() {
		System.out.println("Run test 03");
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "Create_New_Account")
	public void Edit_Account() {
		System.out.println("Run test 04");
	}

	@Test(dependsOnMethods = "Edit_Account")
	public void Delete_Account() {
		System.out.println("Run test 05");
	}

	@Test(dependsOnMethods = "Delete_Account")
	public void Delete_Customer() {
		System.out.println("Run test 06");
	}

}
