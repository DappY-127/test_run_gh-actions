package net.phptravels.tests;

import com.aventstack.extentreports.ExtentReports;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties config;
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeAll
    static void reporterSetup() {
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/TestReport.html");
        extent.attachReporter(htmlReporter);
    }


    @BeforeEach
    public void setUp() throws IOException {
        // Load config properties
        config = new Properties();
        String configPath = "src/main/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Error: Unable to load config.properties from path: " + configPath);
            throw e;
        }

        // Set system properties for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        // Create a new ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterEach
    public void tearDown() {
        // Close the browser window
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Properties getConfig() {
        return config;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    @AfterAll
    static void afterAll() {
        extent.flush();
    }

}
