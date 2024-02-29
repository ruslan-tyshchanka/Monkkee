package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreationPage extends BasePage {

    private final By ENTRY_TEXTBOX = By.id("editable");
    final String APPLY_FORMAT_BUTTON = " //a[contains(@title, '%s')]";
    final String COLORBOX_OPTION = "//a[@class = 'cke_colorbox' and @title = '%s']";
    private final By TAG_NAME_INPUT = By.id("new-tag");
    private final By ADD_TAG_BUTTON = By.id("assign-new-tag");
    final String EXISTING_TAG = "//*[contains(@class, 'tag') and contains(text(),'%s')]";
    private final By AUTOMATIC_COLOR = By.cssSelector("[title='Automatic']");
    private final By BACK_TO_OVERVIEW = By.id("back-to-overview");
    //private final By TAG_DROPDOWN_
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
    private final By NO_ENTRIES_FOUND = By.xpath("//*[contains(text(), 'No entries found')]");


    public CreationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreationPageOpened() {
        return driver.findElement(ENTRY_TEXTBOX).isDisplayed();
    }

    public void clickFormatIcon(String formatType) {
        By FORMAT_BUTTON = By.xpath(String.format(APPLY_FORMAT_BUTTON, formatType));
        driver.findElement(FORMAT_BUTTON).click();
    }

    public void writeEntry(String text) {
        driver.findElement(ENTRY_TEXTBOX).sendKeys(text);
    }

    public void changeColor(String colorTitle) {
        By COLOR_OPTION = By.xpath(String.format(COLORBOX_OPTION, colorTitle));
        By TEXT_COLOR_ICON = By.xpath(String.format(APPLY_FORMAT_BUTTON, "Text Color"));
        driver.findElement(TEXT_COLOR_ICON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
        driver.switchTo().frame(0).findElement(COLOR_OPTION).click();
        driver.switchTo().defaultContent();
    }

    public void changeColorToDefault() {
        By TEXT_COLOR_ICON = By.xpath(String.format(APPLY_FORMAT_BUTTON, "Text Color"));
        driver.findElement(TEXT_COLOR_ICON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
        driver.switchTo().frame(0).findElement(AUTOMATIC_COLOR).click();
        driver.switchTo().defaultContent();
    }

    public void addNewline() {
        driver.findElement(ENTRY_TEXTBOX).sendKeys(Keys.ENTER);
    }

    public void addTag(String tagName) {
        driver.findElement(TAG_NAME_INPUT).sendKeys(tagName);
        driver.findElement(ADD_TAG_BUTTON).click();
    }

    public int findTagByName(String tagName) {
        By EXISTING_TAG_NAME = By.xpath(String.format(EXISTING_TAG, tagName));
        return driver.findElements(EXISTING_TAG_NAME).size();
    }

    public void removeTag(String tagName) {
        By EXISTING_TAG_NAME = By.xpath(String.format(EXISTING_TAG, tagName));
        driver.findElement(EXISTING_TAG_NAME).click();
    }

    public void backToOverviewPage() {
        driver.findElement(BACK_TO_OVERVIEW).click();
    }

    public void openLinkModal() {
        driver.findElement(LINK_ICON).click();
    }

    public void removeLinksFromText() { driver.findElement(UNLINK_ICON).click(); }

    public int isLinkModalPresent() {
        return driver.findElements(LINK_MODAL).size();
    }

    public void writeInLinkModal(String section, String text) {
        By LINK_MODAL_INPUT = By.xpath(String.format(INPUT_LINK_MODAL, section));
        driver.findElement(LINK_MODAL_INPUT).sendKeys(text);
    }

    public void clickButtonInLinkModal(String button) {
        By LINK_MODAL_BUTTON = By.xpath(String.format(BUTTON_LINK_MODAL, button));
        driver.findElement(LINK_MODAL_BUTTON).click();
    };

    public int countLinksInTextbox(String linkText, String linkAddress) {
        By TEXTBOX_LINK = By.xpath(String.format(LINK_IN_TEXTBOX, linkText, linkAddress));
        return driver.findElements(TEXTBOX_LINK).size();
    }

    public void clickAddImageIcon() { driver.findElement(IMAGE_ICON).click(); }

    public int findImageModalOpened() { return driver.findElements(IMAGE_MODAL).size(); }

    public int countImageDisplayedInTextbox() { return driver.findElements(IMAGE_IN_TEXTBOX).size(); }

    public void removePictureFromTextbox() { driver.findElement(ENTRY_TEXTBOX).sendKeys(Keys.BACK_SPACE); }

    public void expandToolbar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EXPAND_TOOLBAR));
        driver.findElement(EXPAND_TOOLBAR).click(); }

    public void reduceToolbar() { driver.findElement(REDUCE_TOOLBAR).click(); }

    public int findExpandToolbarIcon() { return driver.findElements(EXPAND_TOOLBAR).size(); }

    public int findReduceToolbarIcon() { return driver.findElements(REDUCE_TOOLBAR).size(); }

    public void saveEntry() { driver.findElement(SAVE_BUTTON).click(); }

    public void deleteEntry() {
        driver.findElement(DELETE_ENTRY_BUTTON).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Do you really want to delete the entry?");
        alert.accept();
    }

    public boolean isEntryDeleted() { return driver.findElement(NO_ENTRIES_FOUND).isDisplayed(); }
}
