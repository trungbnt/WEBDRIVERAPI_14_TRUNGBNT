package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_05_Locator_In_Selenium {
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "Testing";
	String validEmail = "automation_13@gmail.com";
	String validPass = "123123";
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	 
	@Test
	public void TC_01_EmptyEmailPassword() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
				
		String requireMsgEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(requireMsgEmail, "This is a required field.");
		
		String requireMsgPw = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(requireMsgPw, "This is a required field.");
	}
	 
	@Test
	public void TC_02_InvalidEmail() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
				
		String errorMsgEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorMsgEmail, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_PassLessThan6Characters() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
				
		String errorMsgPass = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorMsgPass, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_InvalidPass() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");
	}
	
	@Test
	public void TC_05_ValidEmailPass() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String stringTitle = driver.findElement(By.xpath("//h1[text()='My Dashboard']")).getText();
		Assert.assertEquals(stringTitle, "MY DASHBOARD");
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+firstName+" "+lastName+"!']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'"+firstName+" "+lastName+"')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+validEmail+"')]")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
	}
	
	@Test
	public void TC_06_CreateAccount() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click();
		
		// chuyển kiểu của đối tượng driver thành JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String randomEmail = js.executeScript("return Math.random().toString(36).substring(2,12)").toString();

		System.out.println(randomEmail);
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Trung");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Bui");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(randomEmail + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//form[@id='form-validate']//button[@title='Register']")).click();
		
		String successMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMsg, "Thank you for registering with Main Website Store.");

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site')]")));
		
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/index.php/");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
