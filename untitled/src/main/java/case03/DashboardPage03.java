package case03;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage03 extends BasePage03 {
    public DashboardPage03() {
        super();
    }

    @FindBy(xpath = "//ul[@id='adminmenu']//div[@class='wp-menu-name' and contains(text(), 'Pages')]")
    WebElement pagesItemLeftMenu;

    public boolean pagesItemLeftMenuIsDisplayed() {
        try {
            return findVisibleElement(pagesItemLeftMenu).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnPagesItem() {
        wait.until(ExpectedConditions.elementToBeClickable(pagesItemLeftMenu));
        pagesItemLeftMenu.click();
    }

}
