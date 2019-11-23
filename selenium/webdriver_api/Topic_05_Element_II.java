package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.TestNGThread;

public class Topic_05_Element_II {
	// 1
	WebDriver driver;

	// 2
	By emailTextbox = By.xpath("//input[@id='mail']");
	By age18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	String valueR = "Automation Testing";

	@BeforeClass
	public void beforeClass() {
		// 3
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed() {
		// 4
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, valueR);
		}

		if (isElementDisplayed(age18Radio)) {
			clickToElement(age18Radio);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		if (isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, valueR);
		}
	}

	@Test
	public void TC_02_Enabled() {
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(age18Radio));
		Assert.assertTrue(isElementEnabled(educationTextArea));

	}

	@Test
	public void TC_03_Selected() {
		driver.navigate().refresh();
		clickToElement(age18Radio);
		clickToElement(developmentCheckbox);
		Assert.assertTrue(isElementSelected(age18Radio));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		clickToElement(developmentCheckbox);
		Assert.assertTrue(isElementSelected(age18Radio));
		Assert.assertFalse(isElementSelected(developmentCheckbox));
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is enabled");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is unselected");
			return false;
		}
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
