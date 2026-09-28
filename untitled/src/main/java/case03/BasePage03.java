package case03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage03 {

    protected WebDriver driver;
    protected final String baseUrl = "https://dev-wordpress-fcdbgyfxfuetftf5.westus2-01.azurewebsites.net/wp-admin";
    protected WebDriverWait wait;

    public BasePage03(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected WebElement findVisibleElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement findExistElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
