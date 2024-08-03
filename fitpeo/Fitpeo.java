package fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Fitpeo {

    public static void main(String[] args) {
        // Setup the WebDriver (Ensure that ChromeDriver is set up in PATH or specify its location)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 1. Navigate to the FitPeo Homepage
            driver.get("URL_OF_FITPEO_HOMEPAGE");

           // 2. Navigate to the Revenue Calculator Page
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REVENUE_CALCULATOR_LINK_TEXT")));
           revenueCalculatorLink.click();

           // 3. Scroll Down to the Slider Section
           WebElement sliderSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SLIDER_SECTION_LOCATOR")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            // 4. Adjust the Slider
            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SLIDER_LOCATOR")));
            
            // Function to adjust the slider
            Actions action = new Actions(driver);
            action.clickAndHold(slider).moveByOffset(820, 0).release().perform();

            // Verify the bottom text field value
            WebElement textField = driver.findElement(By.id("TEXT_FIELD_LOCATOR"));
            if ("820".equals(textField.getAttribute("value"))) {
                System.out.println("Slider value set correctly.");
            } else {
                System.out.println("Slider value not set correctly.");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }}







