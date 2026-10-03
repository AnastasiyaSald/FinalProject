package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage extends BasePage {

    public DashboardPage () {
        super();
    }

    private static final By pagesItemLeftMenuLocator = By.xpath(
            "//ul[@id='adminmenu']//div[@class='wp-menu-name' and contains(text(), 'Pages')]");

    public boolean pagesItemLeftMenuIsDisplayed() {
        try {
            return findVisibleElement(pagesItemLeftMenuLocator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnPagesItem() {
        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement pagesItem = localWait.until(ExpectedConditions.elementToBeClickable(pagesItemLeftMenuLocator));
        pagesItem.click();
    }

}
