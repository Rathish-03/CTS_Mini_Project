package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HolidaysPage {

    WebDriver driver;
    WebDriverWait wait;

    List<String> holidayPackages = new ArrayList<>();
    List<String> prices = new ArrayList<>();

    public HolidaysPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By viewButtons = By.cssSelector(".view-btn");

    public void collectHolidayData(int limit) {

        String parent = driver.getWindowHandle();

        List<WebElement> buttons =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(viewButtons));

        System.out.println("Total packages: " + buttons.size());

        int count = 0;

        for (int i = 0; i < buttons.size(); i++) {

            if (count == limit) break;

            buttons.get(i).click();

            wait.until(d -> driver.getWindowHandles().size() > 1);

            for (String win : driver.getWindowHandles()) {
                if (!win.equals(parent)) {
                    driver.switchTo().window(win);
                    break;
                }
            }

            String pkgName;
            String price;

            try {
                pkgName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.head, h1, .page-heading"))).getText();
            } catch (Exception e) {
                pkgName = "Title not found";
            }

            if (pkgName.equalsIgnoreCase("See all the locations") ||
                    pkgName.contains("can’t be reached")) {

                driver.close();
                driver.switchTo().window(parent);
                continue;
            }

            try {
                WebElement priceBlock = driver.findElement(
                        By.xpath("//p[contains(text(),'Packages available from')]/following-sibling::p")
                );

                price = priceBlock.getText().trim().replaceAll("\\s+", " ");

            } catch (Exception e) {
                price = "Price not available";
            }

            holidayPackages.add(pkgName);
            prices.add(price);

            System.out.println("Package: " + pkgName);
            System.out.println("Price: " + price);
            System.out.println("--------------------------------");

            count++;

            driver.close();
            driver.switchTo().window(parent);
        }
    }

    public List<String> getHolidayPackages() {
        return holidayPackages;
    }

    public List<String> getPrices() {
        return prices;
    }
}