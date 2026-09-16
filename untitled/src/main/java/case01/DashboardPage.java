package case01;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private static final By usersLeftMenuItemLocator = By.xpath("//div[@class='wp-menu-name' and contains(text(), 'Users')]");
    private static final By refreshItemLocator = By.xpath("//li[@id='wp-admin-bar-updates']//span[@class='ab-icon']");
    private static final By mediaLeftMenuItemLocator = By.xpath("//ul[@id='adminmenu']//div[@class='wp-menu-name' and contains(text(), 'Media')]");
    private static final By dashboardPageLeftMenuItemLocator = By.xpath("//div[@id='adminmenuwrap']//div[@class='wp-menu-name' and contains(text(), 'Dashboard')]");
    private static final By postsLeftMenuItemLocator = By.xpath("//div[@id='adminmenuwrap']//div[@class='wp-menu-name' and contains(text(), 'Posts')]");
    private static final By pagesLeftMenuItemLocator = By.xpath("//div[@id='adminmenuwrap']//div[@class='wp-menu-name' and contains(text(), 'Pages')]");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String dashboardPageIsOpened () {
        findVisibleElement(dashboardPageLeftMenuItemLocator).click();
        return driver.getCurrentUrl();
    }

    public boolean usersLeftMenuItemIsDisplayed() {
        try {
            return findVisibleElement(usersLeftMenuItemLocator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean refreshItemIsDisplayed () {
        try {
            return findVisibleElement(refreshItemLocator).isDisplayed();
        }
        catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean mediaLeftMenuItemisDisplayed () {
        try {
            return findVisibleElement(mediaLeftMenuItemLocator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean postsLeftMenuItemIsDisplayed () {
        try {
            return findVisibleElement(postsLeftMenuItemLocator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean pagesLeftMenuItemIsDisplayed () {
        try {
            return findVisibleElement(pagesLeftMenuItemLocator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }


}
