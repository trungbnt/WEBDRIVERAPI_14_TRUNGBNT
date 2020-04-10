package testng;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class TestNG_07_Loop {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromedriver.exe");
			driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
  @Test(invocationCount = 3)
  public void TC01_RegisterSystem() {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  String email = "selenium" +randomNumber()+ "@gmail.com";
	  driver.findElement(emailTextbox).sendKeys(email);
	  driver.findElement(passTextbox).sendKeys("111111");
	  driver.findElement(loginButton).click();
	  
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }
  
  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
  public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
}
