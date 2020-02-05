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
	By emailElement = By.xpath("//*[@id='mail']");
	By ageElement = By.xpath("//*[@id='under_18']");
	By educationElement = By.xpath("//*[@id='edu']");
	By job1Element = By.xpath("//*[@id='job1']");
	By developmentElement = By.xpath("//*[@id='development']");
	By slider1Element = By.xpath("//*[@id='slider-1']");
	By passElement = By.xpath("//*[@id='password']");
	By radioElement = By.xpath("//*[@id='radio-disabled']");
	By bioElement = By.xpath("//*[@id='bio']");
	By job3Element = By.xpath("//*[@id='job3']");
	By checkElement = By.xpath("//*[@id='check-disbaled']");
	By slider2Element = By.xpath("//*[@id='slider-2']");
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Elements_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (isElementDisplayed(emailElement)) {
			driver.findElement(emailElement).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(educationElement)) {
			driver.findElement(educationElement).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(ageElement)) {
			driver.findElement(ageElement).click();
		}
	}
	 
	@Test
	public void TC_02_Elements_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		isElementEnabled(emailElement);
		isElementEnabled(ageElement);
		isElementEnabled(educationElement);
		isElementEnabled(job1Element);
		isElementEnabled(developmentElement);
		isElementEnabled(slider1Element);
		isElementEnabled(passElement);
		isElementEnabled(radioElement);
		isElementEnabled(bioElement);
		isElementEnabled(job3Element);
		isElementEnabled(checkElement);
		isElementEnabled(slider2Element);
	}
	
	@Test
	public void TC_03_Elements_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		isElementEnabled(ageElement);
		driver.findElement(ageElement).click();
		isElementEnabled(developmentElement);
		driver.findElement(developmentElement).click();
		
		if (isElementSelected(ageElement) && isElementSelected(developmentElement)) {
			System.out.println("Checkbox Age(Under 18) & Interests(Development) is selected.");			
		}
		
		isElementEnabled(developmentElement);
		driver.findElement(developmentElement).click();
		isElementSelected(developmentElement);
	}
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element with by " + by + " is enabled!");
			return true;
		} else {
			System.out.println("Element with by " + by + " is disabled!");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element with by " + by + " is selected!");
			return true;
		} else {
			System.out.println("Element with by " + by + " is de-selected!");
			return false;
		}
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 
}
