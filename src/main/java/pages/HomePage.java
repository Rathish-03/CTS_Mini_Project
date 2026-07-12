package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By offersBtn = By.xpath("//div[normalize-space()='View all offers']");
    By holidaysTab = By.linkText("Holidays");

    // Actions
    public void clickViewOffers() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(offersBtn)).click().perform();
    }

    public void clickHolidaysTab() {
        driver.findElement(holidaysTab).click();
    }
}
