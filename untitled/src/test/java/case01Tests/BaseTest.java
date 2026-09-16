package case01Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Map;

@Listeners(ListenerTest.class)
public class BaseTest {
  protected WebDriver driver;

  @BeforeMethod
  @Parameters("browser")
  public void setUp(@Optional("chrome") String browser) {

    if (browser.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
      options.setExperimentalOption("prefs", Map.of(
              "credentials_enable_service", false,
              "profile.password_manager_enabled", false,
              "profile.password_manager_leak_detection", false
      ));
      driver = new ChromeDriver(options);

    } else if (browser.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();

      driver = new FirefoxDriver(options);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    System.out.println("✅ Browser started: " + browser);
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("✅ Browser closed");
    }
  }
}
