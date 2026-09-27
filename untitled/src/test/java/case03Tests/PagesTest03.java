package case03Tests;

import case03.DashboardPage03;
import case03.LoginPage03;
import case03.PagesPage03;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PagesTest03 extends BaseTest03 {
    @Test
    public void loginTest() {
        LoginPage03 loginPage = LoginPage03.getInstance(driver);
        loginPage.open();
        DashboardPage03 dashboardPage = loginPage.doLogin("NastyaSald", "7765292Sold!");
        Assert.assertTrue(dashboardPage.pagesItemLeftMenuIsDisplayed());
        dashboardPage.clickOnPagesItem();
    }

    @Test(dependsOnMethods = "loginTest")
    public void pagesTest () {
        PagesPage03 pagesPage = PagesPage03.getInstance(driver);
        pagesPage.clickOnAddNewPageButton();
        Assert.assertTrue(pagesPage.chooseAPatternModalIsOpened());
        pagesPage.clickOnSelectedPattern();
        pagesPage.enterTextInTheTitleField("Nastya's Title");
        pagesPage.enterTextInTheArticleField("Audrey Hepburn is a legendary British actress who became a symbol of refined beauty, fashion, and the Golden Age of Hollywood thanks to her iconic roles in the films *Roman Holiday* and *Breakfast at Tiffany's*.");
        pagesPage.clickPublishButtonTwice();
        pagesPage.returnBackToPagesPage();
        pagesPage.titleIsDisplayedInTheTable();

    }



}
