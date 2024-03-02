package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class TagsTest extends BaseTest{
    @Test(description = "Assign tag")
    public void assignTags() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
    }

    @Test(description = "Remove assigned tag")
    public void removeTag() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
        creationPage.removeTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                0,
                "Tag wasn't removed"
        );
    }

    @Test(description = "Verify assigned tag from Entries page")
    public void verifyTag() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
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
                entriesPage.findRecentEntryByTag(tagName),
                1,
                "Entry with specified tag is not found"
        );
    }

    @Test(description = "Find existing tag")
    public void findTag() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        String entryText = faker.aviation().aircraft();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
        creationPage.expandToolbar();
        creationPage.writeEntry(entryText);
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
        creationPage.saveEntry();
        creationPage.backToOverviewPage();
        tagsPage.open();
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                "Manage Tags",
                "Page is not opened"
        );
        Assert.assertEquals(
                tagsPage.countTagsByName(tagName),
                1,
                "Tag is not found"
        );
    }

    @Test(description = "Edit existing tag")
    public void editTag() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        String tagNameUpd = faker.food().fruit();
        String entryText = faker.aviation().aircraft();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
        creationPage.expandToolbar();
        creationPage.writeEntry(entryText);
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
        creationPage.saveEntry();
        creationPage.backToOverviewPage();
        tagsPage.open();
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                "Manage Tags",
                "Page is not opened"
        );
        tagsPage.editTagName(tagName, tagNameUpd);
        Assert.assertEquals(
                tagsPage.countTagsByName(tagNameUpd),
                1,
                "Tag wasn't updated"
        );
        Assert.assertEquals(
                tagsPage.countTagsByName(tagName),
                0,
                "Previous tag name is still displayed"
        );
    }

    @Test(description = "Delete tag by name")
    public void deleteTag() {
        String tagName = faker.country().name()+" "+faker.food().fruit();
        String entryText = faker.aviation().aircraft();
        loginPage.open();
        loginPage.login(validUser, validPassword);
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
        creationPage.addTag(tagName);
        Assert.assertEquals(
                creationPage.findTagByName(tagName),
                1,
                "Tag wasn't assigned"
        );
        creationPage.expandToolbar();
        creationPage.writeEntry(entryText);
        Assert.assertEquals(
                creationPage.findReduceToolbarIcon(),
                1,
                "Toolbar is not expanded"
        );
        creationPage.saveEntry();
        creationPage.backToOverviewPage();
        tagsPage.open();
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                "Manage Tags",
                "Page is not opened"
        );
        tagsPage.deleteTag(tagName);
        Assert.assertEquals(
                tagsPage.countTagsByName(tagName),
                0,
                "Tag wasn't deleted"
        );
    }

    @Test(description = "Delete all tags")
    public void deleteAllTags() {
        loginPage.open();
        loginPage.login(validUser, validPassword);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        tagsPage.open();
        Assert.assertEquals(
                settingsPage.getPageTitle(),
                "Manage Tags",
                "Page is not opened"
        );
        int numberOfTags = tagsPage.countTags();
        if(numberOfTags>0) {
        for (int i = 0; i < numberOfTags; i++) {
            tagsPage.deleteTag(tagsPage.getFirstTagName());
        }
        Assert.assertEquals(
                tagsPage.countTags(),
                0,
                "Not all tags were deleted"
        );
        }
    }

    @Test(description = "Go home from Tags page")
    public void goHomeFromTagsPage() {
        loginPage.open();
        loginPage.login(validUser, validPassword);
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "Login failed or create entry button was modified"
        );
        tagsPage.open();
        tagsPage.backToOverview();
        Assert.assertEquals(
                entriesPage.isCreateEntryButtonPresent(),
                true,
                "User is not on Homepage"
        );
    }
}
