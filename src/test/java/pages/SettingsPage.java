package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Log4j2
public class SettingsPage extends BasePage {

    final String SETTING_SECTION_LINK = "//li/a[contains(@href, '%s')]";
    final String SETTING_SECTION_TITLE = "//h1[contains(text(),'%s')]";

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void selectSettingSection(String sectionCode) {
        log.info("Selecting setting section "+sectionCode);
        By SETTING_SECTION = By.xpath(String.format(SETTING_SECTION_LINK, sectionCode));
        driver.findElement(SETTING_SECTION).click();
    }

    public void waitForPageTitle(String pageTitle) {
        log.info("Waiting for page title '"+pageTitle+"' to appear");
        By SETTING_SECTION_NAME = By.xpath(String.format(SETTING_SECTION_TITLE, pageTitle));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SETTING_SECTION_NAME));

    }
    public String getPageTitle() {
        log.info("Getting page title");
        return driver.findElement(PAGE_TITLE).getText();
    }

}
