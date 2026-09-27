package case03;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage03 extends BasePage03 {
    private static DashboardPage03 instance;

    private DashboardPage03(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public static DashboardPage03 getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new DashboardPage03(driver);
        }
        return instance;
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
