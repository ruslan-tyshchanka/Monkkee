package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    private final String USER_EMAIL = "ruslantyshchenko99@gmail.com";
    private final String USER_PASSWORD = "qNmepQpUA7tn$GW";
    private final String ERROR_MESSAGE_FOR_EMPTY_FIELD = "Mandatory field";
    private final String ERROR_MESSAGE_FOR_INVALID_CREDENTIALS = "Login failed";

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"", USER_PASSWORD},
                {USER_EMAIL, ""},
                {"", ""},
                {"test@test.com", USER_PASSWORD},
                {USER_EMAIL, "123456"},
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
        } else if (user != USER_EMAIL && user != "" || password != USER_PASSWORD && password != "") {
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
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
    }



}
