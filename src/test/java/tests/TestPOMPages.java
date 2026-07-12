package tests;

import base.DriverInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.OffersPage;
import pages.HolidaysPage;
import utility.ScreenshotUtil;

import java.time.Duration;
import java.util.Set;

public class TestPOMPages {

    DriverInit objDriver;
    WebDriver driver;
    WebDriverWait wait;

    HomePage homePage;
    OffersPage offersPage;
    HolidaysPage holidaysPage;

    String parent;
    String child;

    @BeforeClass
    @Parameters("browser")
    public void setUpBrowser(String browser) {
        objDriver = new DriverInit();
        driver = objDriver.setUp(browser);

        homePage = new HomePage(driver);
        offersPage = new OffersPage(driver);
        holidaysPage = new HolidaysPage(driver);
    }

    @Test(priority = 0)
    public void validatePageTitle() {

        parent = driver.getWindowHandle();

        homePage.clickViewOffers();

        Set<String> winHandles = driver.getWindowHandles();

        for (String win : winHandles) {
            if (!win.equals(parent)) {
                child = win;
                driver.switchTo().window(child);
            }
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.titleContains(
                "Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com"
        ));

        String actualTitle = offersPage.getPageTitle();

        Assert.assertEquals(
                actualTitle,
                "Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com",
                "Page Title Does not Match!"
        );
    }

    @Test(priority = 1)
    public void validateBannerText() {

        String bannerText = offersPage.getBannerText();

        System.out.println(bannerText);

        Assert.assertEquals(
                bannerText,
                "Great Offers & Amazing Deals",
                "Banner Text Does not Match!"
        );
    }

    @Test(priority = 2)
    public void takeScreenshot() {

        ScreenshotUtil.captureScreenshot(driver, "OfferPage");

        System.out.println("Screenshot captured successfully");
    }

    @Test(priority = 3)
    public void clickHolidaysTab() {

        homePage.clickHolidaysTab();

        holidaysPage.collectHolidayData(5);

        System.out.println("=========== FINAL DATA ===========");
        System.out.println("Packages: " + holidaysPage.getHolidayPackages());
        System.out.println("Prices: " + holidaysPage.getPrices());

        Assert.assertTrue(holidaysPage.getHolidayPackages().size() > 0,
                "No packages collected!");
    }

    @AfterClass
    public void closeBrowser() {
        objDriver.tearDown();
    }
}
