package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

    WebDriver driver;

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By bannerText = By.xpath("//*[@id='collapsibleSection']/section[1]/div[1]/div/div/h2");

    // Actions
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getBannerText() {
        return driver.findElement(bannerText).getText();
    }
}