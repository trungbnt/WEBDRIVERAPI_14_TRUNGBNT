package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	String customerID;
	String username = "mngr244694";
	String password = "hYnypEr";
	
	//Input data New Customer
	String customerName = "Selenium Online";
	String gender = "male";
	String dob = "2000-01-10";
	String address = "123 Address";
	String city = "Ho Chi Minh";
	String state = "Thu Duc";
	String pin = "123456";
	String mobileNumber = "123456987";	
	String email = "Selenium" + randomNumber() + "@gmail.com";
	String passwordNewCustomer = "123456";
	
	//Input data Edit Customer
	String addressEdit = "123 Address 456 Da Nang";
	String cityEdit = "Da Nang";
	String stateEdit = "Hai Chau";
	String pinEdit = "654321";
	String mobileNumberEdit = "123456789";	
	String emailEdit = "Selenium" + randomNumber() + "@hotmail.com";
	String passwordEditCustomer = "1234567";	
	
	//Locator of New/Edit Customer
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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertEquals(driver.findElement(By.tagName("marquee")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']")).isDisplayed());
	}
	 
	@Test
	public void TC_01_Add_New_Customer() {		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
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
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer Id is: " + customerID);
		
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dob, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(mobileNumber, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
	}
	 
	@Test
	public void TC_02_Edit_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextarea).getText());
		
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(cityEdit);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(stateEdit);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(pinEdit);
		driver.findElement(mobileTextbox).clear();
		driver.findElement(mobileTextbox).sendKeys(mobileNumberEdit);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(emailEdit);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(addressEdit, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(cityEdit, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(stateEdit, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pinEdit, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(mobileNumberEdit, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(emailEdit, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
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
