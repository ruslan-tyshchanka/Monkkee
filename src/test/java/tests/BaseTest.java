package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    EntriesPage entriesPage;
    CreationPage creationPage;
    SettingsPage settingsPage;
    TagsPage tagsPage;
    Faker faker;

    String validUser = System.getProperty("user", PropertyReader.getProperty("mnk.user"));
    String validPassword = System.getProperty("password", PropertyReader.getProperty("mnk.password"));

    final String BASE_URL = "https://monkkee.com";
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext testContext) {
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("start-maximized");
            options.addArguments("headless");
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
        tagsPage = new TagsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void clearAndTearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
