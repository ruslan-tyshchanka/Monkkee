package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CleanupTest extends BaseTest{
    @Test(description = "Clening up test data")
    public void cleanupEntries() {
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        driver.findElement(By.xpath("//input[@title='Select all']")).click();
        driver.findElement(By.xpath("//a[@title='Delete selected entries']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Do you really want to delete the selected entries?");
        alert.accept();
    }

    @Test(description = "Delete all tags")
    public void deleteAllTags() {
        loginPage.open();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
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
}
