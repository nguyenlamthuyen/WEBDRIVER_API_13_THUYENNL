package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_IV_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait waitExplicit;

	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingImageBy = By.xpath("//div[@id='loading']//img");
	By helloworldTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");

		// Rõ ràng: Chờ cho element theo từng trạng thái cụ thể
		driver = new ChromeDriver();

		// Ngầm định: Ko chờ cho element nào có trạng thái cụ thể -> đi tìm element
		waitExplicit = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// 1
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Implicit_Wait() {
		// Mở app: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Check cho element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());

		// Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());

		// Để Loading này biến mất (tới 5s) - dù nhanh hay chậm

		// Set laị 10s cho implicit wait (3s ko còn ý nghĩa) - bị ghi đè
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 2
		// Helloworld text được hiển thị: Visible (Displayed)
		System.out.println("Start helloworld - " + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld - " + getCurrentTime());

		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());

		// 4 - Hiển thị Inprogress bar (Loading bar): Invisible (Undisplayed)

	}

	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}

//	@Test
	public void TC_02_Implicit_Override() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Check cho element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());

		// Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());

		// Để Loading này biến mất (tới 5s) - dù nhanh hay chậm

		// Helloworld text được hiển thị: Visible (Displayed)
		System.out.println("Start helloworld - " + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld - " + getCurrentTime());

		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());

	}

//	@Test
	public void TC_03_Explicit_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Click Start button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
//		waitExplicit.until(ExpectedConditions.elementToBeClickable(driver.findElement(startButtonBy)));
		driver.findElement(startButtonBy).click();

		// Loading icon hiển thị và biến mất sau 5s

		// Wait cho helloworld được visible trước khi check displayed
		System.out.println("Start wait visible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
		System.out.println("End wait visible - " + getCurrentTime());

		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());
	}

//	@Test
	public void TC_04_Explicit_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Click Start button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
//		waitExplicit.until(ExpectedConditions.elementToBeClickable(driver.findElement(startButtonBy)));
		driver.findElement(startButtonBy).click();

		// Loading icon hiển thị và biến mất sau 5s
		System.out.println("Start wait invisible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
		System.out.println("End wait invisible - " + getCurrentTime());

		// Wait cho helloworld được visible trước khi check displayed
		System.out.println("Start wait visible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
		System.out.println("End wait visible - " + getCurrentTime());

		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());
	}

//	@Test
	public void TC_05_Date_Implicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// In ra ngày được chọn: No Selected Date to display
		WebElement dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");		
		
		// Click vào current day
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		// Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());

		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "Tuesday, January 07, 2020");		
	}
	
	@Test
	public void TC_06_Date_Explicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// In ra ngày được chọn: No Selected Date to display
		WebElement dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");		
		
		// Click vào current day
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		// Chờ cho loading icon biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position: absolute;')]//div[@class='raDiv']")));
		
		// Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());

		dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "Friday, February 07, 2020");		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
