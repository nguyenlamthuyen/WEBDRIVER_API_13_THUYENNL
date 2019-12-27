package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_User_Interactions {
	WebDriver driver;
	Actions action;
	JavascriptExecutor javascriptExecutor;
	String javascriptPath, jqueryPath;

	@BeforeClass
	public void beforeClass() {
		// Tắt notification
//		  FirefoxProfile profile = new FirefoxProfile(); 
//		  profile.setPreference("dom.webnotifications.enabled", false);
//		  driver = new FirefoxDriver(profile);

		// Capability (config browser)
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;

		javascriptPath = ".\\dragAndDrop\\drag_anddrop_helper.js";
		jqueryPath = ".\\dragAndDrop\\jquery_load_helper.js";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_() {
		driver.get("https://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'navLink')]//a[text()='Men']"))).perform();

		driver.findElement(By.xpath("//a[@class ='desktop-categoryLink' and text()='Boxers']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Boxers For Men']")).isDisplayed());

	}

//	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numberSize = numbers.size();
		System.out.println("Size before click/ hold = " + numberSize); // =12

		// |0 |1 |2 |3 |4 |5 |6 |...
		// | element 01 | ...

		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();

		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Size after click/hod = " + selectedNumbers.size());

		for (WebElement number : selectedNumbers) {
			System.out.println(number.getText());
		}

		Assert.assertEquals(selectedNumbers.size(), 8);
	}

//	@Test
	public void TC_03_Click_And_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numberSize = numbers.size();
		System.out.println("Size before click/ hold = " + numberSize); // =12

		// |0 |1 |2 |3 |4 |5 |6 |...
		// | element 01 | ...

		action.keyDown(Keys.CONTROL).perform();

		action.click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(6)).click(numbers.get(9)).perform();

		action.keyUp(Keys.CONTROL).perform();

		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Size after click/hod = " + selectedNumbers.size());

		for (WebElement number : selectedNumbers) {
			System.out.println(number.getText());
		}

		Assert.assertEquals(selectedNumbers.size(), 5);
	}

//	@Test
	public void TC_04_Double_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Thread.sleep(5000);
		String demoText = driver.findElement(By.xpath("//p[@id='demo']")).getText();

		Assert.assertEquals(demoText, "Hello Automation Guys!");
	}

//	@Test
	public void TC_05_Right_Click() throws InterruptedException {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(findByXpath("//span[text()='right click me']")).perform();
		Thread.sleep(3000);

		action.moveToElement(findByXpath("//span[text()='Quit']")).perform();
		Thread.sleep(3000);

		Assert.assertTrue(isElementDisplayed("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]//span[text()='Quit']"));

		action.click(findByXpath("//span[text()='Quit']")).perform();
		Thread.sleep(3000);

		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");

		driver.switchTo().alert().accept();
	}

	@Test
	public void TC_06_Drag_And_Drop() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement sourceCircle = findByXpath("//div[@id='draggable']");
		WebElement targetCircle = findByXpath("//div[@id='droptarget']");

		action.dragAndDrop(sourceCircle, targetCircle).build().perform();
//		Thread.sleep(3000);

		Assert.assertTrue(isElementDisplayed("//div[text()='You did great!']"));
	}

//	@Test
	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
//		String jquerscript = readFile(jqueryPath);
//		javascriptExecutor.executeScript(jquerscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
	}

	public WebElement findByXpath(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public boolean isElementDisplayed(String xpathLocator) {
		return findByXpath(xpathLocator).isDisplayed();
	}

	public String readFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

//	@Test
	public void TC_08_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
