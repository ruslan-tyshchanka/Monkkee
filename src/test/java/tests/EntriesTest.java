package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

@Log4j2
public class EntriesTest extends BaseTest{

    @Test(description = "Create an entry with formatting")
    public void createEntryWithFormatting() {
        log.info("Creating an entry with some formatting options included");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.clickFormatIcon("Bold");
        creationPage.writeEntry("Heading");
        creationPage.clickFormatIcon("Bold");
        creationPage.addNewline();
        creationPage.changeColor("Vivid Yellow");
        creationPage.writeEntry("Coldplay");
        creationPage.changeColorToDefault();
        creationPage.addNewline();
        creationPage.writeEntry("Back to normal");
        creationPage.backToOverviewPage();

        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Create entry button is not opened"
        );
    }

    @Test(description = "Verify tags CRUD")
    public void assignTags() {
        log.info("Going through E2E flow with tags on Entry Creation page");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.addTag("Tag1");
        Assert.assertEquals(
                creationPage.findTagByName("Tag1"),
                1,
                "Tag wasn't assigned"
        );
        creationPage.removeTag("Tag1");
        Assert.assertEquals(
                creationPage.findTagByName("Tag1"),
                0,
                "Tag wasn't removed"
        );
    }

    @Test(description = "Add link")
    public void addLinkToNewEntry() {
        log.info("Adding a link to an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.openLinkModal();
        Assert.assertEquals(
                creationPage.isLinkModalPresent(),
                1,
                "Link modal is not opened"
        );
        creationPage.writeInLinkModal("Display Text", "Link to Google");
        creationPage.writeInLinkModal("URL","https://www.google.com/");
        creationPage.clickButtonInLinkModal("OK");
        Assert.assertEquals(
                creationPage.countLinksInTextbox("Link to Google", "https://www.google.com/"),
                1,
                "Link is not displayed"
        );
    }

    @Test(description = "Cancel link creation")
    public void cancelLinkCreationInNewEntry() {
        log.info("Cancelling link from a Link modal on Entry Creation page");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.openLinkModal();
        Assert.assertEquals(
                creationPage.isLinkModalPresent(),
                1,
                "Link modal is not opened"
        );
        creationPage.writeInLinkModal("Display Text", "Link to Google");
        creationPage.writeInLinkModal("URL","https://www.google.com/");
        creationPage.clickButtonInLinkModal("Cancel");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You have changed some options. Are you sure you want to close the dialog window?");
        alert.accept();
        Assert.assertEquals(
                creationPage.countLinksInTextbox("Link to Google11111", "https://www.google.com/"),
                0,
                "Link was still created"
        );
    }

    @Test(description = "Remove link")
    public void removeLinkFromNewEntry() {
        log.info("Deleting a link which was added to an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.openLinkModal();
        Assert.assertEquals(
                creationPage.isLinkModalPresent(),
                1,
                "Link modal is not opened"
        );
        creationPage.writeInLinkModal("Display Text", "Link to Google");
        creationPage.writeInLinkModal("URL","https://www.google.com/");
        creationPage.clickButtonInLinkModal("OK");
        Assert.assertEquals(
                creationPage.countLinksInTextbox("Link to Google", "https://www.google.com/"),
                1,
                "Link is not displayed"
        );
        creationPage.removeLinksFromText();
        Assert.assertEquals(
                creationPage.countLinksInTextbox("Link to Google", "https://www.google.com/"),
                0,
                "Link is not displayed"
        );
    }

    @Test(description = "Add a picture")
    public void addPicture() {
        log.info("Adding an image to an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.clickAddImageIcon();
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is not displayed"
        );
        creationPage.writeInLinkModal("URL","data:image/png;base64,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");
        creationPage.clickButtonInLinkModal("OK");
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is still opened"
        );
        Assert.assertEquals(
                creationPage.countImageDisplayedInTextbox(),
                1,
                "Image is not displayed in textbox"
        );
    }

    @Test(description = "Cancel a picture")
    public void cancelPicture() {
        log.info("Cancelling image attachment to an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.clickAddImageIcon();
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is not displayed"
        );
        creationPage.writeInLinkModal("URL","data:image/png;base64,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");
        creationPage.clickButtonInLinkModal("Cancel");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You have changed some options. Are you sure you want to close the dialog window?");
        alert.accept();
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is still opened"
        );
        Assert.assertEquals(
                creationPage.countImageDisplayedInTextbox(),
                0,
                "Image is not displayed in textbox"
        );
    }

    @Test(description = "Remove a picture")
    public void removePicture() {
        log.info("Removing an image which was added to an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.clickAddImageIcon();
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is not displayed"
        );
        creationPage.writeInLinkModal("URL","data:image/png;base64,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");
        creationPage.clickButtonInLinkModal("OK");
        Assert.assertEquals(
                creationPage.findImageModalOpened(),
                1,
                "Image modal is still opened"
        );
        Assert.assertEquals(
                creationPage.countImageDisplayedInTextbox(),
                1,
                "Image is not displayed in textbox"
        );
        creationPage.removePictureFromTextbox();
        Assert.assertEquals(
                creationPage.countImageDisplayedInTextbox(),
                0,
                "Image is not displayed in textbox"
        );
    }

    @Test(description = "Expand a toolbar")
    public void expandToolbar() {
        log.info("Expanding a toolbar");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.expandToolbar();
        Assert.assertEquals(
                creationPage.findExpandToolbarIcon(),
                0,
                "Toolbar is not expanded"
        );
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
    }

        @Test(description = "Reduce a toolbar")
        public void reduceToolbar() {
        log.info("Reducing a toolbar");
            loginPage.open();
            loginPage.login(USER_EMAIL, USER_PASSWORD);
            Assert.assertEquals(
                    entriesPage.isCreateEntryButtonPresent(),
                    true,
                    "Login failed or create entry button was modified"
            );
            entriesPage.goToCreationPage();
            Assert.assertEquals(
                    creationPage.isCreationPageOpened(),
                    true,
                    "Creation page is not opened"
            );
            creationPage.expandToolbar();
            Assert.assertEquals(
                    creationPage.findExpandToolbarIcon(),
                    0,
                    "Toolbar is not expanded"
            );
            Assert.assertEquals(
                    creationPage.findReduceToolbarIcon(),
                    1,
                    "Toolbar is not expanded"
            );
            creationPage.reduceToolbar();
            Assert.assertEquals(
                    creationPage.findExpandToolbarIcon(),
                    1,
                    "Toolbar is not expanded"
            );
            Assert.assertEquals(
                    creationPage.findReduceToolbarIcon(),
                    0,
                    "Toolbar is not expanded"
            );
        }

    @Test(description = "CRUD a simple entry")
    public void crudEntry() {
        log.info("Performing a high-level E2E for an entry");
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        entriesPage.goToCreationPage();
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.writeEntry("Test Entry 1");
        creationPage.expandToolbar();
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
        creationPage.saveEntry();
        creationPage.backToOverviewPage();
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Create entry button is not opened"
        );
        Assert.assertEquals(
                entriesPage.findRecentEntryByText("Test Entry 1"),
                1,
                "Saved entry is not found"
        );
        entriesPage.switchToRecentRecordWithText("Test Entry 1");
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.writeEntry("UPD ");
        creationPage.expandToolbar();
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
        creationPage.saveEntry();
        creationPage.backToOverviewPage();
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Create entry button is not opened"
        );
        Assert.assertEquals(
                entriesPage.findRecentEntryByText("UPD Test Entry 1"),
                1,
                "Saved entry is not found"
        );
        entriesPage.switchToRecentRecordWithText("UPD Test Entry 1");
        Assert.assertEquals(
                creationPage.isCreationPageOpened(),
                true,
                "Creation page is not opened"
        );
        creationPage.deleteEntry();
        Assert.assertEquals(
                creationPage.isEntryDeleted(),
                true,
                "Entry is still displayed"
        );
    }
}
