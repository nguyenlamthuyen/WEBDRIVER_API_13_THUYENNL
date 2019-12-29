package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Window_Tab {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Windows() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// ID cuả tan đang đứng
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		// Tất cả ID của tất cả các tab
//		Set<String> allIDs = driver.getWindowHandles();
		// List <String> -> nó cho phép lưu dữ liệu trùng nhau
		// Set <String> -> ko được phép trùng - nếu trùng thì chỉ lấy 1
//		for(String id : allIDs) {
//			System.out.println("ID = " + id);
//		}
		Thread.sleep(2000);
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		closeAllWindowsWithoutParent(parentID);
	}

//	@Test
	public void TC_02_Windows() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),' to comparis')]")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='button']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Thread.sleep(2000);
		
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'cleared')]")).isDisplayed());
	}
	
	@Test
	public void TC_03_Windows() throws InterruptedException {
		driver.get("https://kyna.vn/");	
		String parentID = driver.getWindowHandle();
		Thread.sleep(5000);
		
		System.out.println("Step 02 - Count popup number");
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup number = " + fancyPopup.size());
		
		if(fancyPopup.size() > 0) {
			System.out.println("Step 03 - Check popup displayed and close it");
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		System.out.println("Step 04 - Click");		
		driver.findElement(By.xpath("//img[@alt='facebook']")).click();
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");		
		driver.findElement(By.xpath("//img[@alt='youtube']")).click();
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
//		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");	
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]")));
//		driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//parent::div[@class]//preceding::a//img")).click();
//		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
//		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//img[@alt='zalo']")).click();		
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "‎Kyna.vn");
		
//		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
//		driver.findElement(By.xpath("//img[@alt='apple-app-icon']")).click();
//		switchToWindowByTitle("‎KYNA on the App Store");
//		Assert.assertEquals(driver.getTitle(), "‎KYNA on the App Store");
		
//		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
//		driver.findElement(By.xpath("//img[@alt='android-app-icon']")).click();
//		switchToWindowByTitle("‎KYNA - Học online cùng chuyên gia - Apps on Google Play");
//		Assert.assertEquals(driver.getTitle(), "‎KYNA - Học online cùng chuyên gia - Apps on Google Play");
	
		closeAllWindowsWithoutParent(parentID);
	}

	public void switchToChildWindowsByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if(currentWin.equals(title)) {
				break;
			}
		}
	}
	
	public void closeAllWindowsWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows) {
			if(!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
