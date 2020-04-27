package webdemo.seleniumDemo;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XPathDemoTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://seleniumdemo.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testHTMNode(){
        WebElement element = driver.findElement(By.xpath("html"));
        assertNotNull(element);
    }

    @Test
    public void testCountLinks() {
        List<WebElement> list = driver.findElements(By.xpath("//a"));
        assertEquals(23, list.size());
    }

    @Test
    public void testCountImages() {
        List<WebElement> list = driver.findElements(By.xpath("//img"));
        assertEquals(2, list.size());
    }

//    @Test
//    @Ignore
//    public void clickAllTheLinks() {
//        List<WebElement> list = driver.findElements(By.xpath("//a"));
//        int linksCount = list.size();
//        String[] links = new String[linksCount];
//        for(int i = 0; i < linksCount; i++) {
//            links[i] = list.get(i).getAttribute("href");
//        }
//        for(int i = 0; i < linksCount; i++) {
//            driver.navigate().to(links[i]);
//            driver.navigate().back();
//        }
//    }

    @Test
    public void clickFormTextFields() throws InterruptedException {
        List<WebElement> list = driver.findElement(By.xpath("//form")).findElements(By.xpath("//input[@type=\"text\"]"));
        assertEquals(4, list.size());
    }
}
