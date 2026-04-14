package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {
    public static void main(String[] args) {

        // 🔥 Headless setup (MANDATORY for Jenkins)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        // Wait setup
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open site
            driver.get("https://automationexercise.com/");

            // Scroll down
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("window.scrollBy(0,500)");

            // Locate first product
            WebElement product = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//div[@class='product-image-wrapper'])[1]")
                    )
            );

            // Hover over product
            Actions actions = new Actions(driver);
            actions.moveToElement(product).perform();

            // Wait for button to be clickable
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//a[contains(text(),'Add to cart')])[1]")
                    )
            );

            // 🔥 Use JS click (fix for intercepted click)
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", addToCart);

            // Wait for popup and click "View Cart"
            WebElement viewCart = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//u[text()='View Cart']")
                    )
            );
            viewCart.click();

            // Small wait to observe result (optional)
            Thread.sleep(2000);

            System.out.println("Product added to cart successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // Always close browser
        }
    }
}
