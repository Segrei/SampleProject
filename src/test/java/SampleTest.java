import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Set;

public class SampleTest {
  protected static WebDriver driver;
  private Logger logger = LogManager.getLogger(SampleTest.class);
  String env = System.getProperty("browser", "chrome").toLowerCase();
  String option = System.getProperty("option","normal").toLowerCase();
  @BeforeEach
  public void setUp() {
    logger.info("Браузер выбран: " + env);
    logger.info("Option выбран: " + option);
    driver = WebDriverFactory.getDriver(env,option);
    logger.info("Драйвер стартовал!");
  }

  @Test
  public void openPage() {
    driver.get("https://www.wildberries.ru/seller/375415");
    //driver.manage().window().fullscreen();
    //driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    logger.info("Открыта страница1");
    //String title = driver.getTitle();
    //String currentUrl = driver.getCurrentUrl();
    //logger.info("title = "+title.toString());
    //logger.info("current URL = "+ currentUrl.toString());

    //String yesButtonXpath = ".//a[@class='btn btn-additional']";

    String tovaryXpath = "/html/body/div[1]/main/div[2]/div/div/div[3]/div[2]/div/div[4]/p[1]";

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    WebElement tovary = driver.findElement(By.xpath(tovaryXpath));
    //WebElement tovary2 = driver.findElement(By.xpath(tovaryXpath));
    logger.info("Количество проданных товаров: " + tovary.getText());

    //driver.get("https://www.wildberries.ru/seller/375412");
    //WebElement tovary2 = driver.findElement(By.xpath(tovaryXpath));
    //logger.info("Количество проданных товаров:" + tovary2.getText());
    //WebElement yesButton = driver.findElement(By.xpath(yesButtonXpath));
    //yesButton.click();
    //logger.info("Кнопку \"Да\" нажали.");

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.get("https://www.wildberries.ru/seller/375412");
    logger.info("Открыта страница2");
    //String tovaryXpath2 = "/html/body/div[1]/main/div[2]/div/div/div[3]/div[2]/div/div[4]/p[1]";
    //WebElement tovary2 = driver.findElement(By.xpath(tovaryXpath2));
    //System.out.println(tovary2);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    tovary=driver.findElement(By.xpath(tovaryXpath));
    logger.info("Количество проданных товаров2: "+ tovary.getText());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    //String bytTehnikaXpath = ".//a[text()='Бытовая техника']";
    //WebElement bytTehnika = driver.findElement(By.xpath(bytTehnikaXpath));
    //bytTehnika.click();
    //logger.info("В бытовую технику перешли.");

    //List<WebElement> elements = driver.findElements(By.xpath("//span[@class='subcategory__title']"));
    //for (WebElement element : elements) {
    //  logger.info("Название категории: " + element.getText());
    //}

    //logger.info("Добавляем куки.");
    //driver.manage().addCookie(new Cookie("Cookie 1", "This Is Cookie 1"));
    //Cookie cookie1  = driver.manage().getCookieNamed("Cookie 1");
    //logger.info(String.format("Domain: %s", cookie1.getDomain()));
    //logger.info(String.format("Expiry: %s",cookie1.getExpiry()));
    //logger.info(String.format("Name: %s",cookie1.getName()));
    //logger.info(String.format("Path: %s",cookie1.getPath()));
    //logger.info(String.format("Value: %s",cookie1.getValue()));
    //logger.info("--------------------------------------");

    /*logger.info("Куки, от ДНС:");
    Set<Cookie> cookies = driver.manage().getCookies();
    for(Cookie cookie : cookies) {
      logger.info(String.format("Domain: %s", cookie.getDomain()));
      logger.info(String.format("Expiry: %s", cookie.getExpiry()));
      logger.info(String.format("Name: %s", cookie.getName()));
      logger.info(String.format("Path: %s", cookie.getPath()));
      logger.info(String.format("Value: %s", cookie.getValue()));
      logger.info("--------------------------------------");
    }*/
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
}