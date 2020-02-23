package selenium_api;

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

public class Topic_11_Popup_Iframe {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Popup_Iframe() {
		driver.get("https://kyna.vn/");
		List<WebElement> fancyPopup = driver.findElements(By.cssSelector(".fancybox-inner"));
		if (fancyPopup.size() > 0) {
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		} else {
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
			String likespage = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
			Assert.assertEquals(likespage, "170K likes");
		}
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("btn-submit-login")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Automation FC']")).isDisplayed());		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
