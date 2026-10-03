package case04Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import webdriver.BrowserMultiThread;

public class BasicStartCloseBrowserTest {

  @BeforeClass
  public void setUp() {
    BrowserMultiThread.getDriver();
  }

  @AfterClass (alwaysRun = true)
  public void tearDown() {
    BrowserMultiThread.close();
  }
}
