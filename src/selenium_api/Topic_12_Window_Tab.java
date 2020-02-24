package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
	public void TC_02_Window_Tab() throws Exception {
		driver.get("https://kyna.vn/");
		List<WebElement> fancyPopup = driver.findElements(By.cssSelector(".fancybox-inner"));
		if (fancyPopup.size() > 0) {
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		String titleParent = "Kyna.vn - Học online cùng chuyên gia";
		switchToWindowAndCheckTitle("//img[@alt='facebook']/parent::a", "Kyna.vn - Trang chủ | Facebook", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='youtube']/parent::a", "Kyna.vn - YouTube", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='zalo']/parent::a", "Kyna.vn", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='apple-app-icon']", "KYNA on the App Store", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='android-app-icon']", "KYNA - Học online cùng chuyên gia - Apps on Google Play", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='kynaforkids.vn']/parent::a", "Kynaforkids.vn trường học trực tuyến cho trẻ", titleParent);
		switchToWindowAndCheckTitle("//img[@alt='kynabiz.vn']/parent::a", "Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn", titleParent);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle(titleParent);
		String parentWindow = driver.getWindowHandle();
		closeAllWinDowsWithoutParentWindow(parentWindow);
	}
	
	@Test
	public void TC_03_Window_Tab() {
		driver.get("https://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		switchToWindowByTitle("Mobile");
		String parentWindow = driver.getWindowHandle();
		closeAllWinDowsWithoutParentWindow(parentWindow);
		driver.findElement(By.xpath("//a[contains(text(), 'Clear All')]")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
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
	
	public void switchToWindowAndCheckTitle(String xpathElement, String title, String titleParent) throws Exception {
		driver.findElement(By.xpath(xpathElement)).click();
		switchToWindowByTitle(title);
		Thread.sleep(1000);
		Assert.assertEquals(driver.getTitle(), title);
		Thread.sleep(1000);
		switchToWindowByTitle(titleParent);
	}
}
