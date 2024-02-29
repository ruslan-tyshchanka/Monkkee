package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntriesPage extends BasePage{

    private final By CREATE_ENTRY_BUTTON = By.cssSelector("[title='Create an entry']");
    final String RECENT_ENTRY_RECORD = "(//*[text()='%s']/parent::*[@class='entry'])[1]";
    final String RECENT_ENTRY_BY_TAG = "//*[@class='tags']/span[contains(text(), '%s')]";
    final String HEADER_BUTTON_PATH = "//header//span[text()='%s']";
    final String FOOTER_BUTTON_PATH = "//footer//a[text()='%s']";
    private final By SEARCH_SCROLL = By.xpath("//a[@custom-scroll-to='search']");
    private final By SEARCH_INPUT = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath("//button[@title='Search']");
    private final By RESET_SEARCH = By.id("reset-search");
    private final By NO_ENTRIES_MESSAGE = By.xpath("//*[contains(text(),'No entries found')]");
    final String SEARCH_BY_TEXT_RESULTS = "//*[@class='entry']/*[@class=' body' and contains(text(),'%s')]";




    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreateEntryButtonPresent () {
        return driver.findElement(CREATE_ENTRY_BUTTON).isDisplayed();
    }

    public void goToCreationPage() {
        driver.findElement(CREATE_ENTRY_BUTTON).click();
    }

    public int findRecentEntryByText(String text) {
            By RECENT_ENTITY_TEXT = By.xpath(String.format(RECENT_ENTRY_RECORD, text));
            return driver.findElements(RECENT_ENTITY_TEXT).size();
    }

    public int findRecentEntryByTag(String tag) {
        By RECENT_ENTITY_TAG = By.xpath(String.format(RECENT_ENTRY_BY_TAG, tag));
        return driver.findElements(RECENT_ENTITY_TAG).size();
    }

    public void switchToRecentRecordWithText(String text) {
        By RECENT_ENTITY_TEXT = By.xpath(String.format(RECENT_ENTRY_RECORD, text));
        driver.findElement(RECENT_ENTITY_TEXT).click();
    }

    public void clickOnHeader(String headerButton) {
        By HEADER_BUTTON = By.xpath(String.format(HEADER_BUTTON_PATH, headerButton));
        driver.findElement(HEADER_BUTTON).click();
    }

    public void clickOnFooter(String footerButton) {
        By FOOTER_BUTTON = By.xpath(String.format(FOOTER_BUTTON_PATH, footerButton));
        driver.findElement(FOOTER_BUTTON).click();
    }

    public void searchByText(String textToSearch) {
        //if(driver.findElements(SEARCH_SCROLL).size()>0){
        //    driver.findElement(SEARCH_SCROLL).click();
        //}
        driver.findElement(SEARCH_INPUT).sendKeys(textToSearch);
        driver.findElement(SEARCH_BUTTON);
    }

    public int getSearchedEntries(String textToSearch) {
        By SEARCH_BY_TEXT_RESULT = By.xpath(String.format(SEARCH_BY_TEXT_RESULTS, textToSearch));
        return driver.findElements(SEARCH_BY_TEXT_RESULT).size();
    }
}
