package case01Tests;


import case01.DashboardPage;
import case01.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTest.class)
public class LoginTest extends BaseTest {
    protected final String baseUrl = "https://dev-wordpress-fcdbgyfxfuetftf5.westus2-01.azurewebsites.net/wp-admin";

    @Test(priority = 1)
    public void loginAsAdmin () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.doLogin("NastyaSald", "7765292Sold!");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedUrl = baseUrl + "/index.php";
        Assert.assertEquals(dashboardPage.dashboardPageIsOpened(), expectedUrl, "DashboardPage is NOT opened");
        Assert.assertTrue(dashboardPage.usersLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - users item is NOT present");
        Assert.assertTrue(dashboardPage.refreshItemIsDisplayed(), "User is logged in via incorrect role - refresh item is NOT present");
    }

    @Test(priority = 3)
    public void loginAsSubscriber () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.doLogin("NastyaSubscriber", "@L7z9blnfyWtoMas8PSoY");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedUrl = baseUrl + "/index.php";
        Assert.assertEquals(dashboardPage.dashboardPageIsOpened(), expectedUrl, "DashboardPage is NOT opened");
        Assert.assertFalse(dashboardPage.usersLeftMenuItemIsDisplayed(), "User is logged in via incorrect role, this item is for Admin role");
    }

    @Test(priority = 2)
    public void loginAsContributor () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.doLogin("NastyaContributor", "&2DO0#OY0Klf6Oatt6AMaGHD");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedUrl = baseUrl + "/index.php";
        Assert.assertEquals(dashboardPage.dashboardPageIsOpened(), expectedUrl, "DashboardPage is NOT opened");
        Assert.assertFalse(dashboardPage.usersLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - users item is displayed");
        Assert.assertFalse(dashboardPage.mediaLeftMenuItemisDisplayed(), "User is logged in via incorrect role - media item is displayed");
        Assert.assertFalse(dashboardPage.pagesLeftMenuItemIsDisplayed(),"User is logged in via incorrect role - pages are present");
        Assert.assertTrue(dashboardPage.postsLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - Posts item is NOT displayed");
    }

    @Test(priority = 2)
    public void loginAsAuthor () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.doLogin("NastyaAuthor", "Et0qMiktu6G2YSN7");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedUrl = baseUrl + "/index.php";
        Assert.assertEquals(dashboardPage.dashboardPageIsOpened(), expectedUrl, "DashboardPage is NOT opened");
        Assert.assertFalse(dashboardPage.usersLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - users are NOT present");
        Assert.assertTrue(dashboardPage.mediaLeftMenuItemisDisplayed(), "User is logged in via incorrect role- media is NOT present");
        Assert.assertTrue(dashboardPage.postsLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - posts are NOT present");
        Assert.assertFalse(dashboardPage.pagesLeftMenuItemIsDisplayed(),"User is logged in via incorrect role - pages are present");
    }

    @Test(priority = 5)
    public void loginAsEditor () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.doLogin("NastyaEditor", "ElT4s@PBi!Pp7xZ9Z");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedUrl = baseUrl + "/index.php";
        Assert.assertEquals(dashboardPage.dashboardPageIsOpened(), expectedUrl, "DashboardPage is NOT opened");
        Assert.assertFalse(dashboardPage.usersLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - users are NOT present");
        Assert.assertTrue(dashboardPage.mediaLeftMenuItemisDisplayed(), "User is logged in via incorrect role- media is NOT present");
        Assert.assertTrue(dashboardPage.postsLeftMenuItemIsDisplayed(), "User is logged in via incorrect role - posts are NOT present");
        Assert.assertTrue(dashboardPage.pagesLeftMenuItemIsDisplayed(),"User is logged in via incorrect role - pages are NOT present");
    }





}
