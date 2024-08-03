package fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Fitpeo2 {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        try {
            // Step 1: Navigate to the FitPeo Homepage
            driver.get("URL_of_FitPeo_Homepage"); // Replace with the actual URL

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator"));
            revenueCalculatorLink.click();

            // Step 3: Scroll Down to the Slider Section
            WebElement sliderSection = driver.findElement(By.xpath("//*[@id='slider-section-id']")); // Replace with the actual XPath
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sliderSection);

            // Step 4: Adjust the Slider
            WebElement slider = driver.findElement(By.xpath("//*[@class='slider']")); // Replace with the actual XPath
            Actions actions = new Actions(driver);

            // Calculate the required offset based on the slider range and position
            int maxValue = 2000;
            int targetValue = 820;
            int sliderWidth = slider.getSize().getWidth();
            int offset = (int) ((targetValue / (double) maxValue) * sliderWidth);

            // Move the slider to the desired value
            actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
            Thread.sleep(2000); // Allow time for the action to complete

            // Validate that the slider's value is updated to 820
            WebElement sliderValueElement = driver.findElement(By.xpath("//*[@id='slider-value-id']")); // Replace with the actual XPath
            String sliderValue = sliderValueElement.getAttribute("value");
            if ("820".equals(sliderValue)) {
                System.out.println("Slider value set correctly.");
            } else {
                System.out.println("Expected 820, but found " + sliderValue);
            }

            // Step 5: Update the Text Field
            WebElement textField = driver.findElement(By.xpath("//*[@id='text-field-id']")); // Replace with the actual XPath
            textField.clear();
            textField.sendKeys("560");
            Thread.sleep(2000); // Allow time for the action to complete

            // Validate that the slider's value is updated to 560
            sliderValue = sliderValueElement.getAttribute("value");
            if ("560".equals(sliderValue)) {
                System.out.println("Slider value updated correctly.");
            } else {
                System.out.println("Expected 560, but found " + sliderValue);
            }

            // Step 6: Select CPT Codes
            String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
            for (String code : cptCodes) {
                WebElement checkbox = driver.findElement(By.xpath("//label[text()='" + code + "']/preceding-sibling::input[@type='checkbox']"));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                Thread.sleep(1000); // Allow time for the action to complete
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
