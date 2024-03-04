package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TagsPage extends BasePage{

private final By MANAGE_TAGS_BUTTON = By.xpath("//*[text()='Manage tags']");
private final By MANAGE_TAGS_HEADER = By.xpath("//h1[contains(text(), 'Manage Tags')]");
private final By BACK_TO_OVERVIEW_BUTTON = By.xpath("//*[@title='Back to overview']");
final String EDIT_TAG_BUTTON = "//*[contains(@class,'tag') and contains(text(),'%s')]/parent::*//*[contains(@ng-href, 'edit')]";
final String DELETE_TAG_BUTTON = "//*[contains(@class,'tag') and contains(text(),'%s')]/parent::*//*[contains(@ng-click, 'delete')]";
private final By TAG_ENTITY = By.xpath("//*[contains(@class,'tag')]");
private final By TAG_ENTITY_FIRST = By.xpath("//*[contains(@class,'tag')][1]");
private final By TAG_INPUT = By.xpath("//input[@id='tag']");
private final By SUBMIT_BUTTON = By.xpath("//*[@type='submit']");
final String TAG_PRESENCE = "//*[contains(@class,'tag') and contains(text(),'%s')]";

public TagsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Tags management page")
    public void open() {
    log.info("Opening 'Manage Tags' page");
        driver.findElement(MANAGE_TAGS_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MANAGE_TAGS_HEADER));

    }

    @Step("Count tags")
    public int countTags() {
        log.info("Counting tags on page");
        return driver.findElements(TAG_ENTITY).size();
}

@Step("Edit tag name")
    public void editTagName(String oldName, String newName) {
        log.info("Editing tag name from '"+oldName+"' to '"+newName+"'");
        By EDIT_TAG = By.xpath(String.format(EDIT_TAG_BUTTON, oldName));
        driver.findElement(EDIT_TAG).click();
        driver.findElement(TAG_INPUT).clear();
        driver.findElement(TAG_INPUT).sendKeys(newName);
        driver.findElement(SUBMIT_BUTTON).click();
    }

    @Step("Count tags by name")
    public int countTagsByName(String tagName) {
        log.info("Counting number of tag with name "+tagName);
        By TAG_DISPLAYED = By.xpath(String.format(TAG_PRESENCE, tagName));
        return driver.findElements(TAG_DISPLAYED).size();
    }

    @Step("Delete tag")
    public void deleteTag(String tagName) {
        log.info("Deleting tag "+tagName);
        By DELETE_TAG = By.xpath(String.format(DELETE_TAG_BUTTON, tagName));
        driver.findElement(DELETE_TAG).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Do you really want to delete the tag? All entries related to the tag remain unchanged.");
        alert.accept();
    }

    @Step("Back to overview page")
    public void backToOverview() {
        log.info("Going back to Overview page");
        driver.findElement(BACK_TO_OVERVIEW_BUTTON).click();
    }

    @Step("Get first tag name")
    public String getFirstTagName() {
        log.info("Getting the name of the first tag");
        return driver.findElement(TAG_ENTITY_FIRST).getText();
    }
}
