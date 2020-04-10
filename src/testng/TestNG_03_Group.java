package testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_03_Group {	
  @Test(groups = {"user"})
  public void TC_01_Register() {
	  
  }
  
  @Test(groups = {"user"})
  public void TC_02_Login() {
	  
  }
  
  @Test(groups = {"product"})
  public void TC_03_NewProduct() {
	  
  }
  
  @Test(groups = {"product"})
  public void TC_04_EditProduct() {
	  
  }
  
  @Test(groups = {"product"})
  public void TC_05_ViewProduct() {
	  
  }
  
  @Test(groups = {"product"})
  public void TC_06_DeleteProduct() {
	  
  }
  
  @Test(groups = {"account"})
  public void TC_07_NewAccount() {
	  
  }
  
  @Test(groups = {"account"})
  public void TC_08_EditAccount() {
	  
  }
  
}
