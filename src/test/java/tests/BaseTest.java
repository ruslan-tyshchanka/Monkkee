package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CreationPage;
import pages.EntriesPage;
import pages.LoginPage;
import pages.SettingsPage;
import utils.PropertyReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    EntriesPage entriesPage;
    CreationPage creationPage;
    SettingsPage settingsPage;
    Faker faker;

    final String USER_EMAIL = System.getenv().getOrDefault("user", PropertyReader.getProperty("mnk.user"));;
    final String USER_PASSWORD = System.getenv().getOrDefault("password", PropertyReader.getProperty("mnk.password"));

    final String BASE_URL = "https://monkkee.com";
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext testContext) {
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            //options.addArguments("headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        testContext.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        faker = new Faker();

        loginPage = new LoginPage(driver);
        entriesPage = new EntriesPage(driver);
        creationPage = new CreationPage(driver);
        settingsPage = new SettingsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void clearAndTearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
