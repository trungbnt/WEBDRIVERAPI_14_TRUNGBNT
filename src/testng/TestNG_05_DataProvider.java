package testng;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class TestNG_05_DataProvider {
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
  @Test(dataProvider = "user_pass")
  public void TC01_LoginSystem(String username, String pass) {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(emailTextbox).sendKeys(username);
	  driver.findElement(passTextbox).sendKeys(pass);
	  driver.findElement(loginButton).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }

  @DataProvider
  public Object[][] user_pass() {
    return new Object[][] {
      new Object[] { "selenium_11_01@gmail.com", "111111" },
      new Object[] { "selenium_11_02@gmail.com", "111111" },
      new Object[] { "selenium_11_03@gmail.com", "111111" }
    };
  }
  
  @AfterClass
	public void afterClass() {
		driver.quit();
	}
}
