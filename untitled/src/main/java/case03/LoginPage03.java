package case03;

import case03.BasePage03;
import case02.DashboardPage02;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage03 extends BasePage03 {
    private static LoginPage03 instance;

    private LoginPage03(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static LoginPage03 getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new LoginPage03(driver);
        }
        return instance;
    }

    @FindBy(id = "user_login")
    private WebElement userNameField;

    @FindBy(id = "user_pass")
    private WebElement passwordField;

    @FindBy(id = "wp-submit")
    private WebElement loginButton;

    public void open() {
        driver.get(baseUrl);
    }

    public DashboardPage03 doLogin(String userName, String password) {
        enterUsername(userName);
        enterPassword(password);
        clickLoginButton();

        return DashboardPage03.getInstance(driver);
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
