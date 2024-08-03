package fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Fitpeo1 {

    public static void main(String[] args) {
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver(); // Ensure chromedriver is in your PATH
        driver.manage().window().maximize();

        try {
            // Step 1: Navigate to the FitPeo Homepage
            driver.get("URL_of_FitPeo_Homepage");  // Replace with the actual URL

            // Step 2: Navigate to the Revenue Calculator Page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Revenue Calculator")));
            revenueCalculatorLink.click();

            // Step 3: Scroll Down to the Slider Section
            WebElement sliderSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider-section-id")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sliderSection);

            // Step 4: Adjust the Slider
            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("slider")));
            Actions actions = new Actions(driver);
            
            // Calculate the required offset based on the slider range and position
            int maxValue = 2000;
            int targetValue = 820;
            int sliderWidth = slider.getSize().getWidth();
            int offset = (targetValue * sliderWidth) / maxValue;
            
            // Move the slider to the desired value
            actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();

            // Validate that the slider's value is updated to 820
            WebElement sliderValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider-value-id")));
            String value = sliderValue.getAttribute("value");
            if ("820".equals(value)) {
                System.out.println("Slider value set correctly.");
            } else {
                System.out.println("Slider value not set correctly.");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
