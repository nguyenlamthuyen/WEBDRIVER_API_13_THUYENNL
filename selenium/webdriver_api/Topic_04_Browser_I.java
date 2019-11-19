package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		/* driver = new ChromeDriver(); */
		
		/* driver = new SafariDriver(); */
	}

	@Test
	public void TC_01_() {
		// Mở cái AUT (Application under test) -> required: http:// hoặc https:// - SSE bảo mật tốt hơn
		driver.get("http://live.demoguru99.com/index.php"); // (**)
		
		// Đóng browser -> Handle Windows/Tabs
		/* driver.close(); */
		
		// Đóng browser
		/* driver.quit(); */ // (**)
		
		// Trả về URL của page hiện tại - tiền tố là get luôn trả về dữ liệu ngoại trừ driver.get("URL")
		String homePageUrl = driver.getCurrentUrl(); // (*)
		System.out.println(homePageUrl);
		
		// Hàm của TestNG
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/index.php");
		
		// Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Home page"); // (*)
		
		// Trả về source code của page hiện tại
		Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store. All Rights Reserved."));
		
		// Trả về cái Windows GUID (Handle Windows) của windows / tab hiện tại
		String homePageTabID = driver.getWindowHandle(); // (**)
		System.out.println("Windows ID = " + homePageTabID);
		
		// Trả về Windows GUID của all tab / windows đang có
		// List(), Set() - Không trùng nhau
		/*
		 * Set<String> allWindowsID = driver.getWindowHandles(); // (**)
		 * 
		 * for (String id : allWindowsID){ System.out.println(id); }
		 * 
		 * for (int i =0; i<= allWindowsID.size(); i++) { System.out.println(i); }
		 */
		 
		// Khai báo 1 biến element là email textbox
		WebElement emailTextbox = driver.findElement(By.xpath("")); // (**)
		
		// Khai báo 1 biến để lấy tất cả các link trên page hiện tại
		List <WebElement> links = driver.findElements(By.xpath("")); // (**)
		
		// Get ra các log của tab Network
		System.out.println(driver.manage().logs().get(LogType.PERFORMANCE));
		
		// Chờ cho element được stable để thao tác lên nó trong khoảng time bao nhiêu -> WebDriverWait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // (**)
		
		// Chờ cho 1 page được load thành công trong khoảng time bao nhiêu
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); // (*)
		
		// Dùng cho Javascript Execuotr (Asynch) -> Sync
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// F11
		driver.manage().window().fullscreen();
		
		// GUI (Graphic User Interface) - Test font/ size/ color/ position/...
		// Functional UI
		
		// Get ra vị trí
		System.out.println(driver.manage().window().getPosition());
		
		// Set ra vị trí
		Point point = new Point(100, 100);
		driver.manage().window().setPosition(point);
		
		// Get ra chiều rộng/ cao (size)
		System.out.println(driver.manage().window().getSize());
		
		Dimension dimension = new Dimension(1920, 1080);
		driver.manage().window().setSize(dimension);
		
		// Giống User sử dụng
		driver.manage().window().maximize(); // (**)
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		// Tracking history/gps/location
		driver.navigate().to("http://live.demoguru99.com/index.php/customer/account/");
		
		// Alert/ Windows/Frame (Iframe)
		
		// Alert // (**)
		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().sendKeys("");
		
		// Windows // (**)
		driver.switchTo().window("Windows GUID");
		
		// Iframe/ frame // (**)
		driver.switchTo().frame(driver.findElement(By.xpath("")));
	}

	@Test
	public void TC_02_() {
		driver.get("");
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
