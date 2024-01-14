package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private final By USERNAME_INPUT = By.id("login");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.cssSelector("[type=submit]");
    private final By LOGIN_ERROR_MESSAGE = By.cssSelector(".alert");
    final String FIELD_ERROR_MESSAGE_PATH = "//*[contains(text(), '%s')]/parent::*//*[contains(@class, 'help-block')]";
    public LoginPage (WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/app");
    }

    public void login(String user, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String returnLoginError() {
        return driver.findElement(LOGIN_ERROR_MESSAGE).getText();
    }

    public String returnFieldError (String fieldName) {
        By FIELD_ERROR_MESSAGE = By.xpath(String.format(FIELD_ERROR_MESSAGE_PATH, fieldName));
        if (driver.findElements(FIELD_ERROR_MESSAGE).size() != 0) {
            return driver.findElement(FIELD_ERROR_MESSAGE).getText();
        } else {
            return "Field is filled in";
        }
    }
}
