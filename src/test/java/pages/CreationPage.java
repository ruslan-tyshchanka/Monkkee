package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CreationPage extends BasePage {

    private final By ENTRY_TEXTBOX = By.id("editable");
    final String APPLY_FORMAT_BUTTON = " //a[contains(@title, '%s')]";
    final String COLORBOX_OPTION = "//a[@class = 'cke_colorbox' and @title = '%s']";
    private final By TAG_NAME_INPUT = By.id("new-tag");
    private final By ADD_TAG_BUTTON = By.id("assign-new-tag");
    final String EXISTING_TAG = "//*[contains(@class, 'tag') and contains(text(),'%s')]";
    private final By AUTOMATIC_COLOR = By.cssSelector("[title='Automatic']");
    private final By BACK_TO_OVERVIEW = By.id("back-to-overview");
    private final By LINK_ICON = By.xpath("//*[contains(@class, '_link_icon')]");
    private final By UNLINK_ICON = By.xpath("//*[contains(@class, '_unlink_icon')]");
    private final By LINK_MODAL = By.xpath("//*[contains(@class, 'dialog_body')]");
    final String INPUT_LINK_MODAL = "//*[text()='%s']/parent::*//input";
    final String BUTTON_LINK_MODAL = "//*[contains(@class, 'dialog_body')]//span[text()='%s']";
    final String LINK_IN_TEXTBOX = "//*[@id='editable']//a[text()='%s' and @href='%s']";
    private final By IMAGE_ICON = By.xpath("//*[contains(@class, '_image_icon')]");
    private final By IMAGE_MODAL = By.xpath("//*[text()='Image Properties']");
    private final By IMAGE_IN_TEXTBOX = By.xpath("//*[@id='editable']//img[contains(@src, 'png')]");
    private final By EXPAND_TOOLBAR = By.xpath("//*[@title='Expand toolbar']");
    private final By REDUCE_TOOLBAR = By.xpath("//*[@title='Reduce toolbar']");
    private final By SAVE_BUTTON = By.xpath("//*[@title='Save']");
    private final By DELETE_ENTRY_BUTTON = By.id("delete-entry");


    public CreationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verfiy if Creation page is opened")
    public boolean isCreationPageOpened() {
        log.info("Checking if Entry Creation page is opened");
        return driver.findElement(ENTRY_TEXTBOX).isDisplayed();
    }

    @Step("Click on Format icon")
    public void clickFormatIcon(String formatType) {
        log.info("Clicking on "+formatType+" formatting icon");
        By FORMAT_BUTTON = By.xpath(String.format(APPLY_FORMAT_BUTTON, formatType));
        driver.findElement(FORMAT_BUTTON).click();
    }

    @Step("Write '{text}' to an entry")
    public void writeEntry(String text) {
        log.info("Writing an entry - "+text);
        driver.findElement(ENTRY_TEXTBOX).sendKeys(text);
    }

    @Step("Change color to '{colorTitle}'")
    public void changeColor(String colorTitle) {
        log.info("Checking text color to "+colorTitle);
        By COLOR_OPTION = By.xpath(String.format(COLORBOX_OPTION, colorTitle));
        By TEXT_COLOR_ICON = By.xpath(String.format(APPLY_FORMAT_BUTTON, "Text Color"));
        driver.findElement(TEXT_COLOR_ICON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
        driver.switchTo().frame(0).findElement(COLOR_OPTION).click();
        driver.switchTo().defaultContent();
    }

    @Step("Change color to default")
    public void changeColorToDefault() {
        log.info("Changing text color to default");
        By TEXT_COLOR_ICON = By.xpath(String.format(APPLY_FORMAT_BUTTON, "Text Color"));
        driver.findElement(TEXT_COLOR_ICON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
        driver.switchTo().frame(0).findElement(AUTOMATIC_COLOR).click();
        driver.switchTo().defaultContent();
    }

    @Step("Add newline")
    public void addNewline() {
        log.info("Adding a newline to an entry");
        driver.findElement(ENTRY_TEXTBOX).sendKeys(Keys.ENTER);
    }

    @Step("Add tag")
    public void addTag(String tagName) {
        log.info("Adding tag "+tagName);
        driver.findElement(TAG_NAME_INPUT).sendKeys(tagName);
        driver.findElement(ADD_TAG_BUTTON).click();
    }

    @Step("Find tag by name")
    public int findTagByName(String tagName) {
        log.info("Finding tag by name "+tagName);
        By EXISTING_TAG_NAME = By.xpath(String.format(EXISTING_TAG, tagName));
        return driver.findElements(EXISTING_TAG_NAME).size();
    }

    @Step("Remove tag")
    public void removeTag(String tagName) {
        log.info("Removing tag "+tagName);
        By EXISTING_TAG_NAME = By.xpath(String.format(EXISTING_TAG, tagName));
        driver.findElement(EXISTING_TAG_NAME).click();
    }

    @Step("Go back to Overview page")
    public void backToOverviewPage() {
        log.info("Going back to Overview page");
        driver.findElement(BACK_TO_OVERVIEW).click();
    }

    @Step("Open link modal")
    public void openLinkModal() {
        log.info("Opening link management modal");
        driver.findElement(LINK_ICON).click();
    }

    @Step("Remove links from text")
    public void removeLinksFromText() {
        log.info("Removing all links from entry text");
        driver.findElement(UNLINK_ICON).click(); }

    @Step("Verify if link modal is opened")
    public int isLinkModalPresent() {
        log.info("Checking if link management modal is opened");
        return driver.findElements(LINK_MODAL).size();
    }

    @Step("Write in link modal")
    public void writeInLinkModal(String section, String text) {
        log.info("Writing '"+text+"' in field '"+section+"'");
        By LINK_MODAL_INPUT = By.xpath(String.format(INPUT_LINK_MODAL, section));
        driver.findElement(LINK_MODAL_INPUT).sendKeys(text);
    }

    @Step("Click {button}")
    public void clickButtonInLinkModal(String button) {
        log.info("Clicking '"+button+"' in link management modal");
        By LINK_MODAL_BUTTON = By.xpath(String.format(BUTTON_LINK_MODAL, button));
        driver.findElement(LINK_MODAL_BUTTON).click();
    };

    @Step("Count links in textbox")
    public int countLinksInTextbox(String linkText, String linkAddress) {
        log.info("Counting number of links with text '"+linkText+"' and address '"+linkText+"'");
        By TEXTBOX_LINK = By.xpath(String.format(LINK_IN_TEXTBOX, linkText, linkAddress));
        return driver.findElements(TEXTBOX_LINK).size();
    }

    @Step("Click Add image icon")
    public void clickAddImageIcon() {
        log.info("Clicking on Add Image icon");
        driver.findElement(IMAGE_ICON).click();
    }

    @Step("Find image modal")
    public int findImageModalOpened() {
        log.info("Checking if image management modal is opened");
        return driver.findElements(IMAGE_MODAL).size();
    }

    @Step("Count images in textbox")
    public int countImageDisplayedInTextbox() {
        log.info("Counting images displayed in textbox");
        return driver.findElements(IMAGE_IN_TEXTBOX).size();
    }

    @Step("Remove pictures from an entry")
    public void removePictureFromTextbox() {
        log.info("Removing a picture from an entry");
        driver.findElement(ENTRY_TEXTBOX).sendKeys(Keys.BACK_SPACE);
    }

    @Step("Expand toolbar")
    public void expandToolbar() {
        log.info("Expanding a toolbar");
        wait.until(ExpectedConditions.visibilityOfElementLocated(EXPAND_TOOLBAR));
        driver.findElement(EXPAND_TOOLBAR).click(); }

    @Step("Reduca toolbar")
    public void reduceToolbar() {
        log.info("Reducing a toolbar");
        driver.findElement(REDUCE_TOOLBAR).click();
    }

    @Step("Find expand toolbar icon")
    public int findExpandToolbarIcon() {
        log.info("Finding 'Expand Toolbar' icon");
        return driver.findElements(EXPAND_TOOLBAR).size();
    }

    @Step("Find redul toolbar icon")
    public int findReduceToolbarIcon() {
        log.info("Finding 'Reduce Toolbar' icon");
        return driver.findElements(REDUCE_TOOLBAR).size();
    }

    @Step("Save entry")
    public void saveEntry() {
        log.info("Saving an entry");
        driver.findElement(SAVE_BUTTON).click();
    }

    @Step("Delete entry")
    public void deleteEntry() {
        log.info("Deleting an entry");
        driver.findElement(DELETE_ENTRY_BUTTON).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Do you really want to delete the entry?");
        alert.accept();
    }
}
