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

public class Topic_07_Dropdown_List_II {
	WebDriver driver;
	Select select;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		Assert.assertFalse(select.isMultiple());
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(1000,1000);");
		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(5000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		select.selectByValue("manual");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		select.selectByIndex(9);
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		Assert.assertEquals(select.getOptions().size(), 10);
		
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Manual");
		Thread.sleep(2000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(2000);
		select.selectByVisibleText("Security");
		Thread.sleep(2000);
		select.selectByVisibleText("Intergration");
		Thread.sleep(2000);
		
		List <WebElement> optionSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 4);
		
		List <String> arraySelected = new ArrayList<String>();		
		for(WebElement select: optionSelected) {
			System.out.println(select.getText());
			arraySelected.add(select.getText());
		}
		
		Assert.assertTrue(arraySelected.contains("Manual"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Security"));
		Assert.assertTrue(arraySelected.contains("Intergration"));
		
		select.deselectAll();
		Thread.sleep(2000);
		
		List <WebElement> optionunSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionunSelected.size(), 0);
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
