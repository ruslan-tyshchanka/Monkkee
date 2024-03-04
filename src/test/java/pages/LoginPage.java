package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_INPUT = By.id("login");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.cssSelector("[type=submit]");
    private final By LOGIN_ERROR_MESSAGE = By.cssSelector(".alert");
    final String FIELD_ERROR_MESSAGE_PATH = "//*[contains(text(), '%s')]/parent::*//*[contains(@class, 'help-block')]";
    private final By PASSWORD_REMINDER_BUTTON = By.xpath("//a[@href='/account/password_reminder']");
    private final By REGISTRATION_BUTTON = By.xpath("//a[@href='/account/registration']");
    final String CHANGE_LANGUAGE_BUTTON = "//*[@class='language-switcher']/a[text()='%s']";

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    @Step("Open app")
    public void open() {
        log.info("Opening "+BASE_URL);
        driver.get(BASE_URL + "/app");
    }

    @Step("Login")
    public void login(String user, String password) {
        log.info("Writing '"+user+"' to username input");
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        log.info("Writing '"+password+"' to password input");
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        log.info("Logging in");
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Return login error message")
    public String returnLoginError() {
        log.info("Getting login error message");
        return driver.findElement(LOGIN_ERROR_MESSAGE).getText();
    }

    @Step("Return field error message")
    public String returnFieldError (String fieldName) {
        By FIELD_ERROR_MESSAGE = By.xpath(String.format(FIELD_ERROR_MESSAGE_PATH, fieldName));
        log.info("Getting login error field message");
        if (driver.findElements(FIELD_ERROR_MESSAGE).size() != 0) {
            return driver.findElement(FIELD_ERROR_MESSAGE).getText();
        } else {
            return "Field is filled in";
        }
    }

    @Step("Click 'Send Reminder' button")
    public void clickSendReminderButton() {
        log.info("Clicking 'Send a Reminder' button");
        driver.findElement(PASSWORD_REMINDER_BUTTON).click();
    }

    @Step("Clikc 'Register' button")
    public void clickRegisterButton() {
        log.info("Clicking 'Register' button");
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Get page title")
    public String getPageTitle() {
        log.info("Getting a page title");
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Switch language")
    public void switchLanguage(String targetLanguage) {
        log.info("Switching to language code "+targetLanguage);
        By LANGUAGE_BUTTON = By.xpath(String.format(CHANGE_LANGUAGE_BUTTON, targetLanguage));
        driver.findElement(LANGUAGE_BUTTON).click();
    }

    @Step("Get reminder button text")
    public String getReminderButtonText() {
        log.info("Getting translated text of 'Send a Reminder' button");
        return driver.findElement(PASSWORD_REMINDER_BUTTON).getText();
    }
}
