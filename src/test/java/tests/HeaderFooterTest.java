package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class HeaderFooterTest extends BaseTest {

    @DataProvider
    public Object[][] headerNavigation() {
        return new Object[][]{
                {"Settings", "Language selection"},
                {"Logout", "Login"}
        };
    }

    @Test(dataProvider = "headerNavigation", description = "Verify redirections of header buttons")
    public void goToSHeaderSection(String headerButton, String pageTitle) {
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.clickOnHeader(headerButton);
        settingsPage.waitForPageTitle(pageTitle);
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                pageTitle,
                "Incorrect page is displayed"
        );
    }

        @DataProvider
        public Object[][] footerNavigation() {
            return new Object[][]{
                    {"Homepage", "/en"},
                    {"Blog", "/en/blog"},
                    {"Terms of use", "/en/terms-of-use"},
                    {"Privacy policy", "/en/privacy-policy"},
                    {"Legal notice", "/en/legal_notice"}
            };
        }

        @Test(dataProvider = "footerNavigation", description = "Verify redirections of footer buttons")
        public void goToFooterSection(String footerButton, String relativeUrl) {
            loginPage.open();
            entriesPage.clickOnFooter(footerButton);
            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    BASE_URL+relativeUrl,
                    "Incorrect page is displayed"
            );
    }

}
