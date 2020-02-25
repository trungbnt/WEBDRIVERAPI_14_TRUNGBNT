package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String fileName1 = "dm.jpg";
	String fileName2 = "jasmine.jpg";
	String fileName3 = "gift.jpg";
	String dmPath = projectPath + "\\upload\\" + fileName1;
	String jasminePath = projectPath + "\\upload\\" + fileName2;
	String giftPath = projectPath + "\\upload\\" + fileName3;
	String multiPath = dmPath + "\n" + jasminePath + "\n" +  giftPath;
	String chromeAutoIT = projectPath + "\\autoIT\\chrome.exe";
	 
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Send_Key() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(multiPath);
		Thread.sleep(2000);
		
		List<WebElement> startBtns = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement startBtn : startBtns) {
			startBtn.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+fileName1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+fileName2+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+fileName3+"']")).isDisplayed());
	}
	 
	@Test
	public void TC_02_AutoIT() throws InterruptedException, IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
		Runtime.getRuntime().exec(new String[] {chromeAutoIT, dmPath});
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+fileName1+"']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Robot() throws InterruptedException, IOException, AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		StringSelection select = new StringSelection(dmPath);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+fileName1+"']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Upload_File() throws InterruptedException, IOException {
		driver.get("https://gofile.io/?t=uploadFiles");
		
		WebElement uploadFile = driver.findElement(By.xpath("//button[@id='btnChooseFiles']"));
		uploadFile.click();
		Thread.sleep(1000);
		
		Runtime.getRuntime().exec(new String[] {chromeAutoIT, dmPath});
		Thread.sleep(1000);
		uploadFile.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec(new String[] {chromeAutoIT, jasminePath});
		Thread.sleep(1000);
		uploadFile.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec(new String[] {chromeAutoIT, giftPath});
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+fileName1+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+fileName2+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+fileName3+"')]")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@id='btnUpload']")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		
		String urlDowload = driver.findElement(By.xpath("//a[@id='link']")).getText();
		driver.get(urlDowload);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+fileName1+"']//following-sibling::td[@class='text-right']//i")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+fileName2+"']//following-sibling::td[@class='text-right']//i")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+fileName3+"']//following-sibling::td[@class='text-right']//i")).isDisplayed());
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
