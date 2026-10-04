package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.BrowserMultiThread;
import webdriver.ConfigReader;

import java.time.Duration;

public abstract class BasePage {

    protected final String baseUrl = ConfigReader.getProperty("baseUrl");

    public BasePage() {
    }

    protected WebDriver getDriver() {
        return BrowserMultiThread.getDriver();
    }

    protected WebElement findVisibleElement(By locator) {
        return BrowserMultiThread.findVisibleElement(locator);
    }

    protected WebElement findExistElement(By locator) {
        return BrowserMultiThread.findExistElement(locator);
    }

    protected JavascriptExecutor getJavascriptExecutor() {
        return BrowserMultiThread.getJavascriptExecutor();
    }
}
