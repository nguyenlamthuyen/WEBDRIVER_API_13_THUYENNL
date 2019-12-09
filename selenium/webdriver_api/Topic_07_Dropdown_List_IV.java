package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List_IV {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	Actions action;
	By numberAllItems = By.xpath("//ul[@id='number-menu']/li/div");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_JQuery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Click vào 5 và kiểm tra nó được chọn thành công
		selectItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "5");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
		Thread.sleep(2000);

		// Click vào 10 và kiểm tra nó được chọn thành công
		selectItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "10");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']"));
		Thread.sleep(2000);

		// 6 - Kiểm tra item đã được chọn thành công
//		boolean status = isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']");
//		System.out.println("Status = " + status);
//		Assert.assertTrue(status);
		// sleep here
//		Thread.sleep(5000);

	}

//	@Test 
	public void TC_02_Angular() throws InterruptedException {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		// Click vào 5 và kiểm tra nó được chọn thành công
		selectItemInCustomeDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		Thread.sleep(2000);

		// Kiểm tra Football được chọn thành công
//		Assert.assertEquals(getTextElement("//select[@id='games_hidden']/option"), "Football"); (hem được)
		Assert.assertEquals(getTextByJS("#games_hidden > option"), "Football");
		Thread.sleep(2000);
	}

//	@Test
	public void TC_03_React() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		// Click vào Christian và kiểm tra nó được chọn thành công
		selectItemInCustomeDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", "//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span", "Christian");
		Thread.sleep(2000);

		// Kiểm tra Football được chọn thành công
		Assert.assertTrue(isElementDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@class='text' and text()='Christian']"));
		Thread.sleep(2000);
	}
	
	@Test
	public void TC_04_Editable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		inputItemInCustomDropdown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']", " //input[@class='search']", "American Samoa");
		Assert.assertTrue(isElementDisplayed("//div[contains(@class,'search selection')]//div[@class='text' and text()='American Samoa']"));
		Thread.sleep(2000);
	}

	public void selectItemInCustomeDropdown(String parentDropDown, String allItemsDropdown, String expectedNumber) throws InterruptedException {
		// 1 - Click vào thẻ chứa dropdown để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentDropDown)).click();
		Thread.sleep(5000);

		// 2 - Khai báo 1 List WebElement chứa all các item bên trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsDropdown));

		// 3 - Wait cho tất cả item (List WebElement) được xuất hiện ở trong
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsDropdown)));

		// 4 - Get text từng item sau đó so sánh vs item mình cần chọn
//				for (int i = 0; i <= allItems.size(); i++) {
//					WebElement item = allItems.get(i);
//					System.out.println("Text = " + item.getText());
		for (WebElement item : allItems) {
			System.out.println(item.getText());

			// 5 - Kiểm tra item nào đúng vs cái mình cần chọn thì click vào
			if (item.getText().equals(expectedNumber)) {
				item.click();
				break;
			}
		}
	}

	public void inputItemInCustomDropdown(String parentDropDown, String inputDd, String expectedText) {
		// 1 - Click vào thẻ chứa dropdown để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentDropDown)).click();

		// 2 - Input text vào textbox
		driver.findElement(By.xpath(inputDd)).sendKeys(expectedText);

		// 3 - Truyền phím ENTER vào Input text
		action.sendKeys(driver.findElement(By.xpath(inputDd)), Keys.ENTER).perform();
	}

	public String getTextElement(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		return element.getText();
	}

	public String getTextByJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
	}

	public boolean isElementDisplayed(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
