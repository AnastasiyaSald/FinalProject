package case02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage02 {
    protected static WebDriver driver;
    protected final String baseUrl = "https://dev-wordpress-fcdbgyfxfuetftf5.westus2-01.azurewebsites.net/wp-admin";
    protected WebDriverWait wait;

    public BasePage02(WebDriver driver) {
        BasePage02.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected WebElement findElement(WebElement element) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return shortWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement findVisibleElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement findExistElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
