package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JsExcecutor {
	WebDriver driver;
	//Locator of New Customer
	By nameTextbox = By.xpath("//input[@name='name']");
	By genderRadio = By.xpath("//input[@name='rad1']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dobTextbox = By.xpath("//input[@name='dob']");
	By addressTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By mobileTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passTextbox = By.xpath("//input[@name='password']");
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_Js_Excecutor() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location = 'http://live.guru99.com/'");
		String domain = (String) js.executeScript("return document.domain;");
		Assert.assertEquals(domain, "live.demoguru99.com");
		String url = (String) js.executeScript("return document.URL;");
		Assert.assertEquals(url, driver.getCurrentUrl());
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//button")));
		verifyTextByJS("Samsung Galaxy was added to your shopping cart.");
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Customer Service']")));
		String titleCS = js.executeScript("return document.title;").toString();
		Assert.assertEquals(titleCS, driver.getTitle());
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("newsletter")));
		String sText = js.executeScript("return document.documentElement.innerText;").toString();
		Assert.assertTrue(sText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		String domainDemov4 = (String) js.executeScript("return document.domain;");
		Assert.assertEquals(domainDemov4, "demo.guru99.com");
	}
	 
	@Test
	public void TC_02_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4");
		String username = "mngr247579";
		String pass = "ruhUgYp";
		//Input data New Customer
		String customerName = "Selenium Online";
		String dob = "2000-01-10";
		String address = "123 Address";
		String city = "Ho Chi Minh";
		String state = "Thu Duc";
		String pin = "123456";
		String mobileNumber = "123456987";	
		String email = "Selenium" + randomNumber() + "@gmail.com";
		String passwordNewCustomer = "123456";
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('type');", driver.findElement(dobTextbox));
		
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(dobTextbox).sendKeys(dob);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileTextbox).sendKeys(mobileNumber);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(passwordNewCustomer);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Create_Account() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location = 'http://live.guru99.com/'");
		clickElementByJS(driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
		clickElementByJS(driver.findElement(By.xpath("//a[@title='Create an Account']")));
		//Locator of New Account
		WebElement fnameTextbox = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement lnameTextbox = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement mailTextbox = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement confirmPassTextbox = driver.findElement(By.xpath("//input[@id='confirmation']"));
		//Input data New Account
		String fname = "Selenium";
		String lname = "Online";
		String emailAdd = "Selenium" + randomNumber() + "@gmail.com";
		String passNewAccount = "123456";
		sendKeyByJS(fnameTextbox, fname);
		sendKeyByJS(lnameTextbox, lname);
		sendKeyByJS(mailTextbox, emailAdd);
		sendKeyByJS(passwordTextbox, passNewAccount);
		sendKeyByJS(confirmPassTextbox, passNewAccount);
		clickElementByJS(driver.findElement(By.xpath("//button[@title='Register']")));
		verifyTextByJS("Thank you for registering with Main Website Store.");
		clickElementByJS(driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")));
		Assert.assertTrue(driver.findElement(By.xpath("//html[@id='top']//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void sendKeyByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+value+"');", element);
	}
	
	public boolean verifyTextByJS(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js.executeScript("return document.documentElement.innerText.match('"+textExpected+"')[0];");
		return textActual.equals(textExpected);
	}
	 
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
}
