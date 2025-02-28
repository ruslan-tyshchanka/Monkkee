package tests;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class LoginTest extends BaseTest{

    private final String ERROR_MESSAGE_FOR_EMPTY_FIELD = "Mandatory field";
    private final String ERROR_MESSAGE_FOR_INVALID_CREDENTIALS = "Login failed";

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"", validPassword},
                {validUser, ""},
                {"", ""},
                {"test@test.com", validPassword},
                {validUser, "123456"},
                {"test@test.com", "123456"}
        };
    }

    @Test(dataProvider = "loginData", description = "Login with empty/invalid credentials")
    public void loginWithInvalidCredentials(String user, String password) {
        loginPage.open();
        loginPage.login(user, password);
        if (user == "") {
            Assert.assertEquals(
                    loginPage.returnFieldError("User"),
                    ERROR_MESSAGE_FOR_EMPTY_FIELD,
                    "Error message is not displayed for empty email"
            );
        } else if (password == "") {
            Assert.assertEquals(
                    loginPage.returnFieldError("Password"),
                    ERROR_MESSAGE_FOR_EMPTY_FIELD,
                    "Error message is not displayed for empty password"
            );
        } else if (user != validUser && user != "" || password != validPassword && password != "") {
            Assert.assertEquals(
                    loginPage.returnLoginError(),
                    ERROR_MESSAGE_FOR_INVALID_CREDENTIALS,
                    "Error message is not displayed for invalid credentials"
            );
        }
    }

    @Test(description = "Login with valid credentials")
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(validUser, validPassword);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
    }

    @Test(description = "Open Send a reminder page")
    public void openReminderPage() {
        loginPage.open();
        loginPage.clickSendReminderButton();
        Assert.assertEquals(
                loginPage.getPageTitle(),
                "Send yourself a password reminder",
                "Reminder page is not opened"
        );
    }

    @Test(description = "Open Send a registration page")
    public void openRegistrationPage() {
        loginPage.open();
        loginPage.clickRegisterButton();
        Assert.assertEquals(
                loginPage.getPageTitle(),
                "Registration",
                "Registration is not opened"
        );
    }

    @DataProvider
    public Object[][] changeLanguage() {
        return new Object[][]{
                {"DE", "Passworthinweis schicken"},
                {"FR", "Rappel de mot de passe"},
                {"PT", "Enviar lembrete de senha"}
        };
    }

    @Test(dataProvider = "changeLanguage", description = "Change login page language")
    public void verifyLanguageByReminderText(String language, String expectedReminderText) {
        loginPage.open();
        loginPage.switchLanguage(language);
        Assert.assertEquals(
                loginPage.getReminderButtonText(),
                expectedReminderText,
                "Language wasn't updated"
        );
    }
}
