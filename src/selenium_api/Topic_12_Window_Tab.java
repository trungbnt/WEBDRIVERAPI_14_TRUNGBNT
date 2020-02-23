package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();
		switchToWindowByTitle("Google");
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[@href='https://facebook.com']")).click();
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[@href='https://tiki.vn']")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		String parentWindow = driver.getWindowHandle();
		closeAllWinDowsWithoutParentWindow(parentWindow);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}
	 
	@Test
	public void TC_02_Window_Tab() {
		driver.get("https://kyna.vn/");
		List<WebElement> fancyPopup = driver.findElements(By.cssSelector(".fancybox-inner"));
		if (fancyPopup.size() > 0) {
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		driver.findElement(By.xpath("//img[@alt='facebook']/parent::a")).click();
		String titleFB = "Kyna.vn - Trang chủ | Facebook";
		switchToWindowByTitle(titleFB);
		Assert.assertEquals(driver.getTitle(), titleFB);
			
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}
	 
	public boolean closeAllWinDowsWithoutParentWindow(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;			
		} else return false;
	}
}
