package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_I {
	WebDriver driver;

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

		// === THAO TÁC VS 1 ELEMENT ===

		// Nếu chỉ tương tác lên element 1 lần thì ko cần khai báo biến
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc.vn@gmail.com");

		// Nếu element này được thao tác/ sử dụng nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		emailTextbox.clear();
		Thread.sleep(3000);
		emailTextbox.sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		Assert.assertTrue(emailTextbox.isDisplayed());

		// === THAO TÁC VS >= 2 ELEMENT ===
		// Tương tác lên all links ở page hiện tại
		List<WebElement> links = driver.findElements(By.xpath("//a"));

		// Có bao nhiêu link ở page hiện tại
		System.out.println("Link size = " + links.size());

		// Get ra all text của link
		for (WebElement link : links) {
			System.out.println("Text = " + link.getText());
		}

		// Textbox/ textarea/ link/ button/ checkbox/ radio/...
		// WebElement element = driver.findElement(By.cssSelector(""));

		// === WEB ELEMENT METHOD (API) ===
		// Xóa dữ liệu trong textbox/ textarea/ dropdown list (editable) trước khi sendkeys (đảm bảo dữ liệu sạch)
		// element.clear();

		// Nhập dữ liệu vào textbox/ textarea/ dropdown list (editable)
		// element.sendKeys("");

		// Click vào link/ button/ checkbox/ radio button/ image/ dropdown
		// element.click();

		WebElement passwordTextbox = driver.findElement(By.id("password"));
		String passwordTextboxHint = passwordTextbox.getAttribute("plaeholder");
		System.out.println(passwordTextboxHint);

		// GUI: Graphic User Interface: font/ size/ color/ location/...
		WebElement buttonDisabled = driver.findElement(By.id("button-disabled"));
		String buttonBackground = buttonDisabled.getCssValue("background-color");
		String buttonFontSize = buttonDisabled.getCssValue("font-size");
		System.out.println(buttonBackground);
		System.out.println(buttonFontSize);

		// Lấy ra vị trí của nó so vs độ phân giải màn hình
		buttonDisabled.getLocation();

		buttonDisabled.getSize();

		// Locator:
		String buttonTagname = buttonDisabled.getTagName();
		System.out.println(buttonTagname);

		System.out.println(driver.findElement(By.tagName("h1")).getText());

		// Kiểm tra cho bất kì 1 element nào được hiển thị ở trên page hay ko
		// displayed/ visible (User: có thể nhìn thấy và thao tác được)

		WebElement userAvatar5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		Assert.assertFalse(userAvatar5.isDisplayed());

		// Kiểm tra cho 1 element có được phép thao tác hay ko (nó có bị disable)
		buttonDisabled.isEnabled();

		// Kiểm tra cho 1 element đã được chọn hay chưa (radio/ checkbox)
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		Assert.assertFalse(under18Radio.isSelected());
		under18Radio.click();
		Assert.assertTrue(under18Radio.isSelected());

		// Work cho 1 cái element là form
		userAvatar5.submit();
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
