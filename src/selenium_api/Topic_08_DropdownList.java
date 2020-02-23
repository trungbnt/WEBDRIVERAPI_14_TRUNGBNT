package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String fName = "Selenium";
	String lName = "Online";
	String email = "Selenium" + randomNumber() + "@gmail.com";
	String pass = "123456";
	String confirmPass = "123456";
	 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	 
	@Test
	public void TC_01_HTML_DropdownList() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Select selectJob1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(selectJob1.isMultiple());
		selectJob1.selectByVisibleText("Mobile Testing");
		Assert.assertEquals("Mobile Testing", selectJob1.getFirstSelectedOption().getText());
		selectJob1.selectByValue("manual");
		Assert.assertEquals("Manual Testing", selectJob1.getFirstSelectedOption().getText());
		selectJob1.selectByIndex(9);
		Assert.assertEquals("Functional UI Testing", selectJob1.getFirstSelectedOption().getText());
		Assert.assertEquals(10, selectJob1.getOptions().size());
		
		Select selectJob2 = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(selectJob2.isMultiple());
		selectJob2.selectByVisibleText("Automation");
		selectJob2.selectByVisibleText("Mobile");
		selectJob2.selectByVisibleText("Desktop");
		List<WebElement> selectedList = selectJob2.getAllSelectedOptions();
		for(WebElement list:selectedList) {
			System.out.println(list.getText());
		}
		Assert.assertEquals(3, selectJob2.getAllSelectedOptions().size());
		selectJob2.deselectAll();
		Assert.assertEquals(0, selectJob2.getAllSelectedOptions().size());
		
	}
	 
	@Test
	public void TC_02_HTML_DropdownList() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.className("ico-register")).click();
		Assert.assertEquals("Register", driver.findElement(By.className("page-title")).getText());
		
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(fName);
		driver.findElement(By.id("LastName")).sendKeys(lName);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(pass);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPass);
		
		Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
		selectDay.selectByVisibleText("1");
		Assert.assertEquals(32, selectDay.getOptions().size());
		Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		selectMonth.selectByVisibleText("May");
		Assert.assertEquals(13, selectMonth.getOptions().size());
		Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
		selectYear.selectByVisibleText("1980");
		Assert.assertEquals(112, selectYear.getOptions().size());
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals("Your registration completed", driver.findElement(By.className("result")).getText());
		driver.findElement(By.name("register-continue")).click();
		Assert.assertEquals("https://demo.nopcommerce.com/", driver.getCurrentUrl());
		driver.findElement(By.className("ico-account")).click();
		Assert.assertEquals("My account - Customer info", driver.findElement(By.className("page-title")).getText());
		driver.findElement(By.className("ico-logout")).click();
		Assert.assertEquals("https://demo.nopcommerce.com/", driver.getCurrentUrl());
	}
	
	@Test
	public void TC_03_Custom_DropdownList() throws Exception {
		//jQuery
		Custom_Dropdown_One_Item("https://jqueryui.com/resources/demos/selectmenu/default.html", "//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		
		//Angular
		Custom_Dropdown_One_Item("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding", "//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
	
		//React
		Custom_Dropdown_One_Item("https://react.semantic-ui.com/maximize/dropdown-example-selection/", "//div[@role='listbox']", "//div[@role='option']/span", "Christian");	
	
		//Vuejs
		Custom_Dropdown_One_Item("https://mikerodham.github.io/vue-dropdowns/", "//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
	
		//React Dropdown Search
		Custom_Dropdown_One_Item("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/", "//div[@role='alert']", "//div[@class='item']/span", "Bahrain");
	}
	
	@Test
	public void TC_04_Custom_DropdownList() throws Exception {
		driver.get("https://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='templates/template.html?v=188&url=basic.html']")));
		String parentXpath = "//div[@class='form-group row'][2]//button";
		String dropdownXpath = "//div[@class='form-group row'][2]//input[@data-name='selectItem']/following-sibling::span";
		String[] months = {"January","February","March", "April"};		
		Thread.sleep(1000);
		Custom_Dropdown_Multi_Item(parentXpath, dropdownXpath, months, "//*[@class='selected']");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
	}
	
	public void Custom_Dropdown_One_Item(String url, String parentXpath, String allItemXpath, String expectedValueItem) throws Exception {
		driver.get(url);
		driver.findElement(By.xpath(parentXpath)).click();
		Thread.sleep(1000);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		//System.out.println("So luong phan tu la: " +allitems.size());
		for (WebElement childElement : allItems) {
			//System.out.println(childElement.getText());
			if (childElement.getText().equals(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {					
					js.executeScript("arguments[0].scrollIntoView(true);", childElement);
					Thread.sleep(1000);
					js.executeScript("arguments[0].click();", childElement);
				}
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public void Custom_Dropdown_Multi_Item(String parentXpath, String allItemXpath, String[] expectedValueItem, String itemsSelectedXpath) throws Exception {
		driver.findElement(By.xpath(parentXpath)).click();
		//WebElement parentItem = driver.findElement(By.xpath(parentXpath));
		//js.executeScript("arguments[0].click();", parentItem);
		//Thread.sleep(1000);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		//System.out.println("So luong phan tu la: " +allItems.size());
		for (WebElement childElement : allItems) {
			//System.out.println(childElement.getText());
			for (String item : expectedValueItem) {
				//System.out.println(item);
				if (childElement.getText().equals(item)) {					
					//js.executeScript("arguments[0].scrollIntoView(true);", childElement);
					//Thread.sleep(1000);
					//js.executeScript("arguments[0].click();", childElement);
					//Thread.sleep(1000);
					childElement.click();
					List<WebElement> itemSelected = driver.findElements(By.xpath(itemsSelectedXpath));
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
			
		}
	}
	
	public boolean checkItemSelected(String[] itemSelectedText, String itemsSelectedXpath, String allItemSelectedTextXpath) {
		List<WebElement> itemSelected = driver.findElements(By.xpath(itemsSelectedXpath));
		int numberItemSelected = itemSelected.size();
		String allItemSelectedText = driver.findElement(By.xpath(allItemSelectedTextXpath)).getText();
		if (numberItemSelected > 0 && numberItemSelected <= 3) {
			for (String item : itemSelectedText) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+ numberItemSelected +" of 12 selected']")).isDisplayed();
		}
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
