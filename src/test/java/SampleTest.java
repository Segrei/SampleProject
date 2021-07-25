import com.sun.source.doctree.SeeTree;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SampleTest {
  protected static WebDriver driver;
  private Logger logger = LogManager.getLogger(SampleTest.class);
  String env = System.getProperty("browser", "chrome");

  @BeforeEach
  public void setUp() {
    logger.info("env = " + env);
    driver = WebDriverFactory.getDriver(env.toLowerCase());
    logger.info("Драйвер стартовал!");
  }

  @Test
  public void openPage() {
    driver.get("https://www.dns-shop.ru/");
    driver.manage().window().fullscreen();
    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    logger.info("Открыта страница DNS - " + "https://www.dns-shop.ru/");
    String title = driver.getTitle();
    String currentUrl = driver.getCurrentUrl();
    logger.info("title = "+title.toString());
    logger.info("current URL = "+ currentUrl.toString());

    String yesButtonXpath = ".//a[@class='btn btn-additional']";
    WebElement yesButton = driver.findElement(By.xpath(yesButtonXpath));
    yesButton.click();
    logger.info("Кнопку \"Да\" нажали.");

    String bytTehnikaXpath = ".//a[text()='Бытовая техника']";
    WebElement bytTehnika = driver.findElement(By.xpath(bytTehnikaXpath));
    bytTehnika.click();
    logger.info("В бытовую технику перешли.");

    List<WebElement> elements = driver.findElements(By.xpath("//span[@class='subcategory__title']"));
    for (WebElement element : elements) {
      logger.info("Название категории: " + element.getText());
    }

    logger.info("Добавляем куки.");
    driver.manage().addCookie(new Cookie("Cookie 1", "This Is Cookie 1"));
    Cookie cookie1  = driver.manage().getCookieNamed("Cookie 1");
    logger.info(String.format("Domain: %s", cookie1.getDomain()));
    logger.info(String.format("Expiry: %s",cookie1.getExpiry()));
    logger.info(String.format("Name: %s",cookie1.getName()));
    logger.info(String.format("Path: %s",cookie1.getPath()));
    logger.info(String.format("Value: %s",cookie1.getValue()));
    logger.info("--------------------------------------");

    logger.info("Куки, от ДНС:");
    Set<Cookie> cookies = driver.manage().getCookies();
    for(Cookie cookie : cookies) {
      logger.info(String.format("Domain: %s", cookie.getDomain()));
      logger.info(String.format("Expiry: %s", cookie.getExpiry()));
      logger.info(String.format("Name: %s", cookie.getName()));
      logger.info(String.format("Path: %s", cookie.getPath()));
      logger.info(String.format("Value: %s", cookie.getValue()));
      logger.info("--------------------------------------");
    }
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @AfterEach
  public void setDown() {
    if (driver != null) {
      driver.quit();
      logger.info("Драйвер остановлен!");
    }
  }


  public WebDriver getDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
    options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
    options.setAcceptInsecureCerts(false);
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    options.addArguments("--start-maximized");
    options.addArguments("--incognito");

    return new ChromeDriver(options);
  }
}