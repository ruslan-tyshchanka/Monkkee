package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class EntriesPage extends BasePage{

    private final By CREATE_ENTRY_BUTTON = By.id("create-entry");
    final String RECENT_ENTRY_RECORD = "(//*[text()='%s']/parent::*[@class='entry'])[1]";
    final String RECENT_ENTRY_BY_TAG = "//*[@class='tags']/span[contains(text(), '%s')]";
    final String HEADER_BUTTON_PATH = "//header//span[text()='%s']";
    final String FOOTER_BUTTON_PATH = "//footer//a[text()='%s']";
    private final By SEARCH_INPUT = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath("//button[@title='Search']");
    private final By RESET_SEARCH = By.id("reset-search");
    private final By NO_ENTRIES_MESSAGE = By.xpath("//*[contains(text(),'No entries found')]");
    final String SEARCH_BY_TEXT_RESULTS = "//*[@class='entry']/*[@class=' body' and contains(text(),'%s')]";




    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if entry button is present")
    public boolean isCreateEntryButtonPresent () {
        log.info("Checking if Create Entry button is displayed");
        return driver.findElement(CREATE_ENTRY_BUTTON).isDisplayed();
    }

    @Step("Go to Creation page")
    public void goToCreationPage() {
        log.info("Going to Entry Creation page");
        driver.findElement(CREATE_ENTRY_BUTTON).click();
    }

    @Step("Find recent entry by text '{text}'")
    public int findRecentEntryByText(String text) {
        log.info("Finding the most recent Entry including text "+text);
        By RECENT_ENTITY_TEXT = By.xpath(String.format(RECENT_ENTRY_RECORD, text));
        return driver.findElements(RECENT_ENTITY_TEXT).size();
    }

    @Step("Find recent entry by tag '{tag}'")
    public int findRecentEntryByTag(String tag) {
        log.info("Finding the most recent Entry including tag "+tag);
        By RECENT_ENTITY_TAG = By.xpath(String.format(RECENT_ENTRY_BY_TAG, tag));
        return driver.findElements(RECENT_ENTITY_TAG).size();
    }

    @Step("Switch to recent entry")
    public void switchToRecentRecordWithText(String text) {
        log.info("Clicking on the most recent entry containing text "+text);
        By RECENT_ENTITY_TEXT = By.xpath(String.format(RECENT_ENTRY_RECORD, text));
        driver.findElement(RECENT_ENTITY_TEXT).click();
    }

    @Step("Click on header")
    public void clickOnHeader(String headerButton) {
        log.info("Clicking on '"+headerButton+"' button on page header");
        By HEADER_BUTTON = By.xpath(String.format(HEADER_BUTTON_PATH, headerButton));
        driver.findElement(HEADER_BUTTON).click();
    }

    @Step("Click on footer")
    public void clickOnFooter(String footerButton) {
        log.info("Clicking on '"+footerButton+"' button on page footer");
        By FOOTER_BUTTON = By.xpath(String.format(FOOTER_BUTTON_PATH, footerButton));
        driver.findElement(FOOTER_BUTTON).click();
    }

    @Step("Search by text")
    public void searchByText(String textToSearch) {
        log.info("Search for an entry by text "+textToSearch);
        driver.findElement(SEARCH_INPUT).sendKeys(textToSearch);
        driver.findElement(SEARCH_BUTTON).click();
    }

    @Step("Get searched entries")
    public int getSearchedEntries(String textToSearch) {
        log.info("Counting the number of entries found by search");
        By SEARCH_BY_TEXT_RESULT = By.xpath(String.format(SEARCH_BY_TEXT_RESULTS, textToSearch));
        return driver.findElements(SEARCH_BY_TEXT_RESULT).size();
    }

    @Step("Find no entries message")
    public int findNoEntriesMessage() {
        log.info("Counting the number of 'No entries' messages found");
        return driver.findElements(NO_ENTRIES_MESSAGE).size();
    }

    @Step("Reset search")
    public void resetSearch() {
        log.info("Reset search parameters");
        wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_SEARCH));
        driver.findElement(RESET_SEARCH).click();
    }
}
