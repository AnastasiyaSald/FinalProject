package case02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MediaLibraryPage02 extends BasePage02 {

    public MediaLibraryPage02(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='page-title-action aria-button-if-js' and contains(text(), 'Add New Media File')]")
    WebElement addNewMediaButton;

    public void clickOnAddNewMediabutton () {
        addNewMediaButton.click();
    }

    @FindBy(xpath = "//input[contains(@id, 'html')]")
    WebElement uploadFile;

    public void uploadFile (String pathForUploading) {
        uploadFile.sendKeys(pathForUploading);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(pictureUploaded));
    }

    @FindBy(xpath = "//div[@class='attachments-wrapper']//li[contains(@aria-label, 'vogue_picture')]")
    WebElement pictureUploaded;

    public void checkThatPictureIsUploaded () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(pictureUploaded));
        Assert.assertTrue(pictureUploaded.isDisplayed(), "Picture is NOT uploaded");
    }

    @FindBy(xpath = "//div[@class='actions']//button[@class='button-link delete-attachment']")
    WebElement buttonToDeleteUploadedPicture;

    public void deleteUploadedPicture() {
        pictureUploaded.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(buttonToDeleteUploadedPicture));
        buttonToDeleteUploadedPicture.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void checkThatPictureIsDeleted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(buttonToDeleteUploadedPicture));

        List<WebElement> pictures = driver.findElements(By.xpath("//div[@class='attachments-wrapper']//li[contains(@aria-label, 'vogue_picture')]"));
        boolean isPicturePresent = !pictures.isEmpty();
        Assert.assertFalse(isPicturePresent, "Picture is NOT deleted");
    }

}
