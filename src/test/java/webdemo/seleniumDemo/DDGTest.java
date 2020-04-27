package webdemo.seleniumDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DDGTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testTitlePage() {
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }

    @Test
    public void testSource(){
        String source = driver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }

    @Test
    public void testClick(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("Koty");
        driver.findElement(By.id("search_button_homepage")).click();
        assertTrue(driver.getCurrentUrl().contains("Koty"));
    }

    // inna metoda na klikniecie: submit() zamiast click()
    @Test
    public void testSubmit(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("Koty");
        driver.findElement(By.id("search_button_homepage")).submit();
        assertEquals("Koty at DuckDuckGo", driver.getTitle());
    }

    // pierwszy i trzeci otrzymany wynik
    @Test
    public void testResult(){
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("Koty");
        driver.findElement(By.id("search_button_homepage")).click();
        driver.findElement(By.id("r1-0")).click(); // pierwszy
        String url1 = driver.getCurrentUrl();
        driver.navigate().back();
        driver.findElement(By.id("r1-2")).click(); // trzeci
        String url3 = driver.getCurrentUrl();
        assertNotEquals(url1, url3);
    }

    // pierwszy i trzeci otrzymany wynik (inaczej)
    @Test
    public void testResult_2() {
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("Koty");
        driver.findElement(By.id("search_button_homepage")).click();
        List<WebElement> list = driver.findElements(By.className("result__a"));
        list.get(0).click();
        String url1 = driver.getCurrentUrl();
        list.get(2).click();
        String url3 = driver.getCurrentUrl();
        assertNotEquals(url1, url3);
    }

    // gdy nie znajdzie dostajemy wyjatek
    @Test
    public void testNotExisting() {
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("not existing selector haha")));
    }

    // gdy findElements nie znajdzie daje pusta liste
    @Test
    public void testNotExistingElements() {
        assertTrue(driver.findElements(By.partialLinkText("not existing partialLinkText haha")).isEmpty());
    }
}
