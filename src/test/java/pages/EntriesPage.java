package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntriesPage extends BasePage{

    private final By CREATE_ENTRY_BUTTON = By.cssSelector("[title='Create an entry']");

    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreateEntryButtonPresent () {
        return driver.findElement(CREATE_ENTRY_BUTTON).isDisplayed();
    }

    public void goToCreationPage() {
        driver.findElement(CREATE_ENTRY_BUTTON).click();
    }
}
