package selenium_api;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_16_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	 
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Demo() {
		driver.get("");
	}
	 
	@Test
	public void TC_02_Implicit_Wait() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_Explicit_Wait_Use_Invisible_03s() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='loading']/img")));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_Explicit_Wait_Use_Invisible_05s() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='loading']/img")));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_04_Explicit_Wait_Use_Visible_03s() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_04_Explicit_Wait_Use_Visible_05s() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_05_Explicit_Wait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceholder1_RadCalendar1")));
		System.out.println(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText());
		driver.findElement(By.xpath("//td[@title='Monday, March 02, 2020']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@class,'rcSelected')]//a[text()='2']")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Monday, March 02, 2020");
	}
	
	@Test
	public void TC_06_Fluent_Wait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		FluentWait<WebElement> fluentElement;
		WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdown);
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
						.pollingEvery(100, TimeUnit.MILLISECONDS)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebElement, Boolean>(){
							public Boolean apply(WebElement element) {
								boolean flag = element.getText().endsWith("02");
								System.out.println("Time = " + element.getText());
								return flag;
							}
						});
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
