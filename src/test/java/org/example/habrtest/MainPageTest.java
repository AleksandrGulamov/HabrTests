package org.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");


    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void forAuthorsTest() {

        WebElement footer = driver.findElement(By.cssSelector("a[href=\'/ru/docs/help/\']"));
        footer.click();

        assertTrue(driver.findElement(By.cssSelector("a[href=\"/ru/docs/authors/\"]")).isDisplayed(), "Для авторов не найден");
    }



    @Test
    public void sectionDevelopment() {

        WebElement header = driver.findElement(By.cssSelector(".tm-main-menu__item_active[data-test-id='main-menu-item']"));
       header.click();

        assertTrue(driver.findElement(By.cssSelector(".tm-section-name__text")).isDisplayed(), "Разработка не найден");

    }

    @Test
    public void allStreams() {
        WebElement header2 = driver.findElement(By.cssSelector(".tm-main-menu__item[href='/ru/articles/']"));
        header2.click();

        assertTrue(driver.findElement(By.cssSelector(".tm-section-name__text")).isDisplayed(), "Раздел 'Все потоки' не найден");

        assertTrue(driver.findElement(By.xpath("//h2[text()='Лучшие блоги']")).isDisplayed(), "Раздел 'Лучшие блоги' не найден");

        assertTrue(driver.findElement(By.xpath("//*[(text()='Сбер')]")).isDisplayed(), "'Сбер' не найден в разделе 'Лучшие блоги'");

    }
}

