package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreationPage extends BasePage{

    private final By ENTRY_TEXTBOX = By.id("editable");
    final String APPLY_FORMAT_BUTTON = " //a[contains(@title, '%s')]";
    final String COLORBOX_OPTION = "//a[@class = 'cke_colorbox' and @title = '%s']";
    private final By TAG_NAME_INPUT = By.id("new-tag");
    private final By ADD_TAG_BUTTON = By.id("assign-new-tag");
    final String EXISTING_TAG = "//*[contains(@class, 'tag') and contains(text(),'%s')]";
    private final By AUTOMATIC_COLOR = By.cssSelector("[title='Automatic']");
    private final By BACK_TO_OVERVIEW = By.id("back-to-overview");
    //private final By TAG_DROPDOWN_


    public CreationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreationPageOpened() {
        return driver.findElement(ENTRY_TEXTBOX).isDisplayed();
    }
    public void clickFormatIcon (String formatType) {
        By FORMAT_BUTTON = By.xpath(String.format(APPLY_FORMAT_BUTTON, formatType));
        driver.findElement(FORMAT_BUTTON).click();
    }

    public void writeEntry (String text) {
        driver.findElement(ENTRY_TEXTBOX).sendKeys(text);
    }

    public void changeColor (String colorTitle) {
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
}
