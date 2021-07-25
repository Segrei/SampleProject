import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;

public class SampleTest {

  protected static WebDriver driver;
  public Logger logger = LogManager.getLogger(SampleTest.class);
  String env = System.getProperty("browser", "firefox");

  @BeforeEach
  public void setUp() {
    /*WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    logger.info("Драйвер стартовал!");*/
    logger.info("env = " + env);
    driver = WebDriverFactory.getDriver(env.toLowerCase());
    logger.info("Драйвер стартовал!");
  }

  @Test
  public void openPage() {
    driver.get("https://yandex.ru/");
    logger.info("Открыта страница Yandex");
    String title = driver.getTitle();
    logger.info("title = "+title.toString());
    String currentUrl = driver.getCurrentUrl();
    logger.info("current URL = "+ currentUrl.toString());

    String searchInputXpath = ".//input[@class='input__control input__input mini-suggest__input']";
    WebElement searchInput = driver.findElement(By.xpath(searchInputXpath));
    String searchText = "Java";
    searchInput.sendKeys(searchText);

    /*String searchButtonXpath = ".//button[@class='button mini-suggest__button button_theme_websearch button_size_ws-head i-bem button_js_inited']";*/
    String searchButtonXpath = ".//button[@class='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']";
    WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
    searchButton.click();
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @AfterEach
  public void setDown() {
    if(driver != null) {
      driver.quit();
      logger.info("Драйвер остановлен!");
    }
  }
}