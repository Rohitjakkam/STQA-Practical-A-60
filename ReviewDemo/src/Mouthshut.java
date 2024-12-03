import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Mouthshut {

    WebDriver driver;

    public Mouthshut() {
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Disable notifications
        prefs.put("profile.default_content_setting_values.notifications", 2);

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HOME\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(options);

        // Maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void scrapeReviews() {
        try {
            // Navigate to the desired URL
            String url = "https://www.justdial.com/Nashik/SNJB-s-Late-Sau-Kantabai-Bhavarlalji-Jain-College-OfEngineering-Neminagar-Chandwad/0253PX253-X253-151019113056-B2P9_BZDET/reviews/page-6";
            driver.get(url);

            // Collect reviews from the page
            List<WebElement> reviews = driver.findElements(By.cssSelector(".col-sm-12.mrg-tp-md.user-review-txt"));

            // Save reviews to a text file
            PrintWriter writer = new PrintWriter("C:\\Users\\HOME\\eclipse-workspace\\ReviewDemo\\Snjb.txt", "UTF-8");

            for (WebElement review : reviews) {
                writer.println(review.getText());
                writer.println("------------------------------------------------------------");
            }

            writer.close();
            System.out.println("Reviews have been successfully saved.");

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        Mouthshut mouthshut = new Mouthshut();
        mouthshut.scrapeReviews();
    }
}
