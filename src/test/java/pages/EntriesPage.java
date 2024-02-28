package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntriesPage extends BasePage{

    private final By CREATE_ENTRY_BUTTON = By.cssSelector("[title='Create an entry']");
    final String RECENT_ENTRY_RECORD = "(//*[text()='%s']/parent::*[@class='entry'])[1]";

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

    public void switchToRecentRecordWithText(String text) {
        By RECENT_ENTITY_TEXT = By.xpath(String.format(RECENT_ENTRY_RECORD, text));
        driver.findElement(RECENT_ENTITY_TEXT).click();
    }
}
