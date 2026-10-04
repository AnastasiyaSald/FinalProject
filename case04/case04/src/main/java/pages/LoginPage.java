package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final By userNameLocator = By.id("user_login");
    private static final By passwordLocator = By.id("user_pass");
    private static final By loginButtonLocator = By.id("wp-submit");

    public LoginPage() {
        super();
    }

    public void open() {
        getDriver().get(baseUrl);
    }

    public void doLogin (String userName, String password) {
        enterUsername (userName);
        enterPassword (password);
        clickLoginButton();
    }

    public void enterUsername (String userName) {
        findVisibleElement(userNameLocator).sendKeys(userName);
    }

    public void enterPassword (String password) {
        findVisibleElement(passwordLocator).sendKeys(password);
    }

    public void clickLoginButton() {
        findVisibleElement(loginButtonLocator).click();
    }

}
