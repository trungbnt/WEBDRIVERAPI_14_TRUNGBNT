package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Elements {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Elements_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");		
		
		WebElement emailElement = driver.findElement(By.xpath("//*[@id='mail']"));
		WebElement ageElement = driver.findElement(By.xpath("//*[@id='under_18']"));
		WebElement eucationElement = driver.findElement(By.xpath("//*[@id='edu']"));
		
		Assert.assertTrue(emailElement.isDisplayed());
		if (emailElement.isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='mail']")).sendKeys("Automation Testing");
		}
		Assert.assertTrue(eucationElement.isDisplayed());
		if (eucationElement.isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='edu']")).sendKeys("Automation Testing");
		}
		Assert.assertTrue(ageElement.isDisplayed());
		if (ageElement.isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='under_18']")).click();
		}		
	}
	 
	@Test
	public void TC_02_Elements_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Boolean isEnabledEmail = driver.findElement(By.xpath("//*[@id='mail']")).isEnabled();
		if (isEnabledEmail) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledAge = driver.findElement(By.xpath("//*[@id='under_18']")).isEnabled();
		if (isEnabledAge) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledEdu = driver.findElement(By.xpath("//*[@id='edu']")).isEnabled();
		if (isEnabledEdu) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledJob1 = driver.findElement(By.xpath("//*[@id='job1']")).isEnabled();
		if (isEnabledJob1) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledDev = driver.findElement(By.xpath("//*[@id='development']")).isEnabled();
		if (isEnabledDev) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledSlider1 = driver.findElement(By.xpath("//*[@id='slider-1']")).isEnabled();
		if (isEnabledSlider1) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		
		Boolean isEnabledPass = driver.findElement(By.xpath("//*[@id='password']")).isEnabled();
		if (isEnabledPass) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledRadio = driver.findElement(By.xpath("//*[@id='radio-disabled']")).isEnabled();
		if (isEnabledRadio) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledBio = driver.findElement(By.xpath("//*[@id='bio']")).isEnabled();
		if (isEnabledBio) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledJob3 = driver.findElement(By.xpath("//*[@id='job3']")).isEnabled();
		if (isEnabledJob3) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledCheck = driver.findElement(By.xpath("//*[@id='check-disbaled']")).isEnabled();
		if (isEnabledCheck) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
		Boolean isEnabledSlider2 = driver.findElement(By.xpath("//*[@id='slider-2']")).isEnabled();
		if (isEnabledSlider2) {
			System.out.println("Element is enabled.");
		} else System.out.println("Element is disabled.");
	}
	
	@Test
	public void TC_03_Elements_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='under_18']")).isEnabled());
		driver.findElement(By.xpath("//*[@id='under_18']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='development']")).isEnabled());
		driver.findElement(By.xpath("//*[@id='development']")).click();
		
		Boolean under18IsSelected = driver.findElement(By.xpath("//*[@id='under_18']")).isSelected();
		Boolean devIsSelected = driver.findElement(By.xpath("//*[@id='development']")).isSelected();
		if (under18IsSelected && devIsSelected) {
			System.out.println("Checkbox Age(Under 18) & Interests(Development) is selected.");			
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='development']")).isEnabled());
		driver.findElement(By.xpath("//*[@id='development']")).click();
		if (driver.findElement(By.xpath("//*[@id='development']")).isSelected() == false) {
			System.out.println("Interests(Development) is not selected.");
		}
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
