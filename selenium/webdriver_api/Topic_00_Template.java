package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login");
	
		// ID
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		// NAME
		driver.findElement(By.name("send")).click();
		
		// CLASS
		driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).sendKeys("classname@gmail.com");
		
		// TAGNAME (Tìm xem có bao nhiêu đường link ở page này và in giá trị của nó ra)
		// Đếm (Count) bao nhiêu element ở trên page?
		
		/* WebElement link = driver.findElement(By.tagName("a")); */
		
		List <WebElement> links = driver.findElements(By.tagName("a"));	
		int linkNumber = links.size();
		System.out.println("Tong so link = " + linkNumber);
		for (WebElement link : links) {
			System.out.println("value = " + link.getAttribute("href"));
		}
		
		// LINK TEXt (Link)
		driver.findElement(By.linkText("Orders and Returns")).click();
		
		// PARTIAL LINK TEXT (Link)
		driver.findElement(By.partialLinkText("Orders")).click();
		
		// CSS
		driver.findElement(By.cssSelector("#email")).sendKeys("css_id@gmail.com");
		
		driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("");
		
		driver.findElement(By.cssSelector(".validate-email")).clear();
		driver.findElement(By.cssSelector(".validate-email")).sendKeys("css_class@gmail.com");
		
		System.out.println("The a dung css = " + driver.findElements(By.cssSelector("a")).size());
		driver.findElement(By.cssSelector("a[href=\\'http://live.demoguru99.com/index.php/sales/guest/form/\\']")).click();
		
		// XPATH
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("");
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("");
		System.out.println("The a dung css = " + driver.findElements(By.xpath("//a")).size());
		driver.findElement(By.xpath("//a[contains(text(),'Orders and Returns')]")).click();
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
