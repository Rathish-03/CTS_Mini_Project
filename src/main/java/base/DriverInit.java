package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DriverInit {
    protected WebDriver driver;

    public WebDriver setUp(String browser){

        String websiteLink = "https://www.yatra.com/";
        switch(browser.toLowerCase()){
            case "edge":
                System.setProperty(
                        "webdriver.edge.driver",
                        "C:\\Users\\2497788\\Downloads\\edgedriver_win64\\msedgedriver.exe"
                );
                driver = new EdgeDriver();
                break;

            case "chrome":
                driver = new ChromeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(websiteLink);

        return driver;
    }

    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}