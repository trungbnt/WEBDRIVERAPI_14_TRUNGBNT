package testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_04_Priority_Enable_Disable {
  @Test(groups = {"user"}, priority = 1, enabled = true)
  public void Register() {
	  
  }
  
  @Test(groups = {"user"}, priority = 2, enabled = true)
  public void Login() {
	  
  }
  
  @Test(groups = {"product"}, priority = 3, enabled = true)
  public void NewProduct() {
	  
  }
  
  @Test(groups = {"product"}, priority = 3, description = "Edit product")
  public void EditProduct() {
	  
  }
  
  @Test(groups = {"product"})
  public void ViewProduct() {
	  
  }
  
  @Test(groups = {"product"})
  public void DeleteProduct() {
	  
  }
  
  @Test(groups = {"account"})
  public void NewAccount() {
	  
  }
  
  @Test(groups = {"account"})
  public void EditAccount() {
	  
  }

}
