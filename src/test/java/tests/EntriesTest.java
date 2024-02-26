package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntriesTest extends BaseTest{

    @Test(description = "Verify that Entry can be created")
    public void simpleEntryCreation() {
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

    @Test(description = "Verify tags????")
    public void assignTags() {
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

}
