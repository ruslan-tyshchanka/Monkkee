package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Function;

@Log4j2
public class SettingsTest extends BaseTest{

    @DataProvider
    public Object[][] settingsSections() {
        return new Object[][]{
                {"locale", "Language selection"},
                {"email", "Email"},
                {"password", "Change password"},
                {"login", "Login alias"},
                {"logout", "Inactivity timeout"},
                {"editor","Editor"},
                {"export", "Export"},
                {"delete_account", "Delete user account"}
        };
    }

    @Test(dataProvider = "settingsSections", description = "Verify the display of settings section")
    public void goToSettingSection(String sectionCode, String sectionTitle) {
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.clickOnHeader("Settings");
        settingsPage.selectSettingSection(sectionCode);
        settingsPage.waitForPageTitle(sectionTitle);
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                sectionTitle,
                "Incorrect section is displayed"
        );
    }
}
