package Services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScrapper {
    private String directoryPath;
    private String telegramGroupLink;
    private WebDriver driver;

    public WebScrapper(String directoryPath, String telegramGroupLink) {
        this.directoryPath = directoryPath;
        this.telegramGroupLink = telegramGroupLink;
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Set the path to chromedriver executable
        driver = new ChromeDriver();
    }

    public void scrappTelegramGroup() {
        // driver.get(telegramGroupLink);
        System.out.println("not implemented");
    }
}
