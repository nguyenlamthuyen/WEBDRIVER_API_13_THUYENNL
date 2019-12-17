package webdriver_api;

import java.util.List;
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

public class Topic_10_Popup_Iframe {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Popup() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		Thread.sleep(5000);
		// Case 01 - Có popup xuất hiện
		// Case 02 - Không có popup xuất hiện
		
		System.out.println("Step 02 - Count popup number");
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup number = " + fancyPopup.size());
		
		// Step 03
		if(fancyPopup.size() > 0) {
			System.out.println("Step 03 - Check popup displayed and close it");
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		// Step 04
//		boolean fancyPopup = driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed();
//		System.out.println("Fancy popup displayed = " + fancyPopup);
//		Assert.assertTrue(fancyPopup);
		
//		driver.findElement(By.cssSelector(".fancybox-close")).click();
		
		// Switch vào Iframe trước thì mới tương tác vs các element nằm trong iframe đó
		
		// Index
		//driver.switchTo().frame(1);
		
		// Name or ID
		//driver.switchTo().frame("");
		
		// Web Element
		System.out.println("Step 04 - Switch vào Facebook iframe");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]")));
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("Facebook iframe displayed = " + facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText();
		System.out.println("Facebook like number = " + facebookLikes);
		Assert.assertEquals(facebookLikes, "170K likes");
		
		// Switch về Main Page lại
		driver.switchTo().defaultContent();
		
//		Thread.sleep(3000);
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("fancybox-skin")));
		driver.findElement(By.className("button-login")).click();		
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.id("btn-submit-login")).click();
		
		WebElement userLogin = driver.findElement(By.xpath("//span[@class='user']"));
		
		Assert.assertTrue(userLogin.isDisplayed());
		
		Assert.assertEquals(userLogin.getText(), "Automation FC");
	}

//	@Test
	public void TC_02_() {
		driver.get("");
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
