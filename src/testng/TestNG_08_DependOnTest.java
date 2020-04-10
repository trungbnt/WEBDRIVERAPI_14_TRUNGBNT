package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_08_DependOnTest {
	@Test(groups = {"user"})
	  public void TC_01_Register() {
		  Assert.assertTrue(false);
	  }
	  
	  @Test(groups = {"user"}, dependsOnMethods = "TC_01_Register")
	  public void TC_02_Login() {
		  
	  }
	  
	  @Test(groups = {"product"})
	  public void TC_03_NewProduct() {
		  
	  }
	  
	  @Test(groups = {"product"})
	  public void TC_04_EditProduct() {
		  
	  }
}
