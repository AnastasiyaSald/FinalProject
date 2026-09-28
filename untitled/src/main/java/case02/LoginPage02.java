package case02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage02 extends BasePage02 {

    @FindBy(id = "user_login")
    private WebElement userNameField;

    @FindBy(id = "user_pass")
    private WebElement passwordField;

    @FindBy(id = "wp-submit")
    private WebElement loginButton;

    public LoginPage02(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl);
    }

    public DashboardPage02 doLogin(String userName, String password) {
        enterUsername(userName);
        enterPassword(password);
        clickLoginButton();

        return new DashboardPage02(driver);
    }

    public void enterUsername(String userName) {
        findVisibleElement(userNameField).sendKeys(userName);
    }

    public void enterPassword(String password) {
        findVisibleElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        findVisibleElement(loginButton).click();
    }
}
