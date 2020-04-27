package webdemo.seleniumDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class LoginTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        driver.get("http://seleniumdemo.com/");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin() {
        String link = driver.findElement(By.className("menu-item-22")).findElement(By.tagName("a")).getAttribute("href");
        driver.navigate().to(link);
        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("slorumutho@mywrld.top");
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("TestTset!@34");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        assertNotNull(driver.findElement(By.className("woocommerce-MyAccount-navigation")));
    }
}
