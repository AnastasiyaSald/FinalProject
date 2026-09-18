package case02Tests;

import case02.DashboardPage02;
import case02.LoginPage02;
import case02.MediaLibraryPage02;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class MediaUploadTest02 extends BaseTest02 {

    @Test
    public void loginTest() {
        LoginPage02 loginPage = new LoginPage02(driver);
        loginPage.open();
        DashboardPage02 dashboardPage = loginPage.doLogin("NastyaSald", "7765292Sold!");
        Assert.assertTrue(dashboardPage.mediaItemLeftMenuIsDisplayed(),"User is logged in via incorrect role - media item is NOT displayed");
        dashboardPage.clickOnMediaItem();
    }

    @Test(dependsOnMethods = "loginTest")
    public void uploadPictureTest () throws InterruptedException {
        MediaLibraryPage02 mediaLibraryPage = new MediaLibraryPage02(driver);
        mediaLibraryPage.clickOnAddNewMediabutton();
        mediaLibraryPage.uploadFile("C:\\Users\\A.Saldatsenka\\Documents\\Lightshot\\vogue_picture.jpg");
        mediaLibraryPage.checkThatPictureIsUploaded();
    }

    @Test(dependsOnMethods = "uploadPictureTest")
    public void deleteUploadedPictureTest () {
        MediaLibraryPage02 mediaLibraryPage = new MediaLibraryPage02(driver);
        mediaLibraryPage.deleteUploadedPicture();
        mediaLibraryPage.checkThatPictureIsDeleted();
    }

}
