package case02Tests;

import case02.DashboardPage02;
import case02.LoginPage02;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Map;

@Listeners(ListenerTest.class)
public class BaseTest02 {
  protected WebDriver driver;

  @BeforeClass
  @Parameters("browsersUploadPicture")
  public void setUp(@Optional("chrome") String browser) {
    final String baseUrl = "https://dev-wordpress-fcdbgyfxfuetftf5.westus2-01.azurewebsites.net/wp-admin";
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


  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("✅ Browser closed");
    }
  }
}
