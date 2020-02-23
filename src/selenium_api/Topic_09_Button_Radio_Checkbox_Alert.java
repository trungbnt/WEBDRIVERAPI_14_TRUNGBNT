package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox_Alert {
	WebDriver driver;
	WebDriverWait wait;
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
	}
	 
	@Test
	public void TC_01_Button_JS() {
		driver.get("https://live.demoguru99.com/");
		WebElement btnMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		clickElementByJS(btnMyAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.demoguru99.com/index.php/customer/account/login/");
		WebElement btnCreateAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickElementByJS(btnCreateAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.demoguru99.com/index.php/customer/account/create/");
	}
	 
	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		WebElement cbDual = driver.findElement(By.xpath("//input[@id='eq5']"));
		cbDual.click();
		Assert.assertTrue(cbDual.isSelected());
		if (cbDual.isSelected()) {
			cbDual.click();
		}
		Assert.assertTrue(!cbDual.isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement radPetrol = driver.findElement(By.xpath("//input[@id='engine3']"));
		radPetrol.click();
		if (!radPetrol.isSelected()) {
			radPetrol.click();
		}
		Assert.assertTrue(radPetrol.isSelected());
	}
	
	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement radSummer = driver.findElement(By.xpath("//input[@id='mat-radio-4-input']"));
		clickElementByJS(radSummer);
		Assert.assertTrue(radSummer.isSelected());
		if (!radSummer.isSelected()) {
			radSummer.click();
		}		
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement cbChecked = driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']"));
		WebElement cbIndeterminate = driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']"));
		cbChecked.click();
		cbIndeterminate.click();
		Assert.assertTrue(cbChecked.isSelected());
		Assert.assertTrue(cbIndeterminate.isSelected());
		if (cbChecked.isSelected()) {
			cbChecked.click();
		}
		Assert.assertTrue(!cbChecked.isSelected());
		if (cbIndeterminate.isSelected()) {
			cbIndeterminate.click();
		}
		Assert.assertTrue(!cbIndeterminate.isSelected());
	}
	
	@Test
	public void TC_04_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement btnJS = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		btnJS.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals("I am a JS Alert", textOnAlert);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
	}
	
	@Test
	public void TC_05_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement btnJS = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		btnJS.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals("I am a JS Confirm", textOnAlert);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}
	
	@Test
	public void TC_06_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement btnJS = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		btnJS.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals("I am a JS prompt", textOnAlert);
		String txtAlert = "TrungCr";
		alert.sendKeys(txtAlert);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " +txtAlert);
	}
	
	@Test
	public void TC_07_Authentication_Alert() {
		String usernamepass = "admin";
		driver.get("http://the-internet.herokuapp.com");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(byPassAuthenticationAlert(url, usernamepass, usernamepass));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public String byPassAuthenticationAlert(String url, String username, String pass) {
		System.out.println("Old url= " + url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + pass + "@" + splitUrl[1];
		System.out.println("New url = " + url);
		return url;
	}
	 
}
