package case04Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import webdriver.ConfigReader;

@Listeners(ListenerTest.class)
public class BaseTest extends BasicStartCloseBrowserTest {

  @BeforeClass
  public void login() {
    LoginPage loginPage = new LoginPage();
    loginPage.open();

    loginPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    DashboardPage dashboardPage = new DashboardPage();
    Assert.assertTrue(dashboardPage.pagesItemLeftMenuIsDisplayed());
    dashboardPage.clickOnPagesItem();
  }
}
