package case02;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage02 extends BasePage02 {
    public DashboardPage02(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@id='adminmenu']//div[@class='wp-menu-name' and contains(text(), 'Media')]")
    WebElement mediaItemLeftMenu;

    public boolean mediaItemLeftMenuIsDisplayed() {
        try {
            return findVisibleElement(mediaItemLeftMenu).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnMediaItem () {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mediaItemLeftMenu));
        mediaItemLeftMenu.click();
    }


}
