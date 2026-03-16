package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open website
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Enter username
        driver.findElement(By.id("username")).sendKeys("student");

        // Enter password
        driver.findElement(By.id("password")).sendKeys("Password123");

        // Click login
        driver.findElement(By.id("submit")).click();

        // Wait 5 seconds
        Thread.sleep(5000);

        driver.quit();
    }
}
