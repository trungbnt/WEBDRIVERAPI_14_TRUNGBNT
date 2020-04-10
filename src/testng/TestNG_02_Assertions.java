package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_02_Assertions {
  @Test
  public void TC_01() {
	  String testNGFramework = "TestNG";
	  String junitFramework = "JUnit";
	  
	  boolean status = testNGFramework.equals(junitFramework);
	  System.out.println("Status: " +status); //false
	  
	  boolean assertion = testNGFramework.equals(testNGFramework);
	  System.out.println("Status: " +status); //true
	  
	  Assert.assertTrue(assertion);
	  Assert.assertFalse(status);
	  
	  Assert.assertNotEquals(testNGFramework, junitFramework);
	  Assert.assertEquals(testNGFramework, "TestNG");
	  
	  Object obj = null;
	  
	  Assert.assertNull(obj);
	  
	  obj = "Automation";
	  
	  Assert.assertNotNull(obj);
	  
  }
}
