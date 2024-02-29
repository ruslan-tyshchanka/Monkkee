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
}
