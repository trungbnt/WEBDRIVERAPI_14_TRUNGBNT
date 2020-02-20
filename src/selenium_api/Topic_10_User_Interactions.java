package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		Actions action = new Actions(driver);
		WebElement hover = driver.findElement(By.xpath("//*[@id='desktop-header-cnt']//a[text()='Discover']"));
		action.moveToElement(hover).perform();
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']//a[text()='Lacoste']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Lacoste Products");
	}
	 
	@Test
	public void TC_02_Multi_Item() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.clickAndHold(list.get(0)).moveToElement(list.get(3)).release().perform();
		List<WebElement> listItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(4, listItemSelected.size());
	}
	
	@Test
	public void TC_03_Random_Item() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).perform();
		list.get(0).click();
		list.get(2).click();
		list.get(5).click();
		list.get(10).click();
		List<WebElement> listItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(4, listItemSelected.size());
	}
	
	@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Actions action = new Actions(driver);
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(doubleClick).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}
	
	@Test
	public void TC_05_Right_Click() {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		Actions action = new Actions(driver);
		WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClick).perform();
		WebElement hoverQuit = driver.findElement(By.xpath("//span[text()='Quit']"));
		action.moveToElement(hoverQuit).perform();
		hoverQuit.click();
	}
	
	@Test
	public void TC_06_Drag_Drop() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		Actions action = new Actions(driver);
		WebElement sourceElement = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//*[@id='droptarget']"));
		action.dragAndDrop(sourceElement, targetElement).perform();
		Thread.sleep(1000);
		Assert.assertEquals(targetElement.getText(), "You did great!");
	}
	
	@Test
	public void TC_07_Drag_Drop_HTML5() throws InterruptedException, IOException, AWTException {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
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
	 
}
