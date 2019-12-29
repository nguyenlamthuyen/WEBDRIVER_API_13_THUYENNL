package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor ;
	WebElement element;
	String username = "mngr238966";
	String password = "emYmEqe";
	
	// Input in New Customer (user)/ Output (server) data
	String customerName = "Jason Staham";
	String gender = "male";
	String dateOfBirth = "1983-06-06";
	String address = "255 PA Hamlet";
	String city = "Hawaii";
	String state = "NewYork";
	String pin = "999777";
	String phone = "0988555777";
	String email = "jsstaham" + randomNumber() + "@hotmail.com";
	
	// Locator for New/ Edit Customer form
	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='m']");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	By submitButton = By.name("sub");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		// Set driver for JS lib
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_JS() throws InterruptedException {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");
		
		String liveUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveUrl, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		
		verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.");
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(3000);
		
		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "demo.guru99.com");
	}

//	@Test
	public void TC_02_Remove_Attribute() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String homePageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homePageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']//td[text()='Manger Id : " + username + "']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Input data to New Customer form
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).sendKeys(gender);
		
		// Remove attribute type='date' (Daate of birth)
		removeAttributeInDOM("//input[@id='dob']", "type");	
		Thread.sleep(4000);
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// Verify output data = input data
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
	}

	@Test
	public void TC_03_Create_Account() throws InterruptedException{
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		clickToElementByJS("//div[@id='header-account']//a[contains(text(),'My Account')]");
		
		clickToElementByJS("//a[@class='button']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "Automation");
		sendkeyToElementByJS("//input[@id='lastname']", "FC");
		sendkeyToElementByJS("//input[@id='email_address']", "automationfc" + randomNumber() + "@gmail.com");
		sendkeyToElementByJS("//input[@id='password']", "123456");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");
		
		clickToElementByJS("//span[text()='Register']");
		
		String innerSuccessT = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(innerSuccessT.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}

		public int randomNumber() {
			Random rand = new Random();
			return rand.nextInt(100000);
		}
}
