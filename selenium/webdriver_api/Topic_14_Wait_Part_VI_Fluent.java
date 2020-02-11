package webdriver_api;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_Wait_Part_VI_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--whitelist-ip *");
		chromeOptions.addArguments("--proxy-server='direct://'");
		chromeOptions.addArguments("--proxy-bypass-list=*");
		driver = new ChromeDriver(chromeOptions);
//		explicitWait = new WebDriverWait(driver, 5);
		fluentDriver = new FluentWait<WebDriver>(driver);

		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Fluent_WebDriver() {
		driver.get("http://demo.guru99.com/");

		// Tổng thời gian cần check
		fluentDriver.withTimeout(Duration.ofSeconds(15))
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(Duration.ofMillis(200))
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class);

		WebElement submitButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@name='Login']"));
			}
		});

		submitButton.click();
	}

	@Test
	public void TC_02_Fluent_WebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentElement = new FluentWait<WebElement>(countdount);
		
		fluentElement.withTimeout(Duration.ofSeconds(15))
			// Tần số mỗi 1s check 1 lần
			.pollingEvery(Duration.ofMillis(100))
			// Nếu gặp exception là find ko thấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class);
		
		boolean status = (Boolean) fluentElement.until(new Function<WebElement, Boolean>(){
			@Override
			public Boolean apply(WebElement element) {
				// Kiểm tra điều kiện countdount = 00
				boolean flag = element.getText().endsWith("15");
				System.out.println("Time = " + element.getText());
				//return giá trị cho function apply
				return flag;
			}
		});
		
		System.out.println("Status = ");
		Assert.assertTrue(status);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
