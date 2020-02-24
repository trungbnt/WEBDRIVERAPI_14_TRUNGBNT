package selenium_api;

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

public class Topic_13_JsExcecutor {
	WebDriver driver;
	
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
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
		Assert.assertEquals(domainDemov4, "live.demoguru99.com");
	}
	 
	@Test
	public void TC_02_() {
		driver.get("");
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public boolean verifyTextByJS(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js.executeScript("return document.documentElement.innerText.match('"+textExpected+"')[0];");
		return textActual.equals(textExpected);
	}
	 
}
