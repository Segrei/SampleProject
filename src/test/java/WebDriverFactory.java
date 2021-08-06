import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;

public class WebDriverFactory {
  private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

  public static WebDriver getDriver(String browserName, String optionName) {

    PageLoadStrategy pageLoadStrategy=null;

    switch (optionName) {
      case "normal":
        pageLoadStrategy = PageLoadStrategy.NORMAL;
        break;
      case "eager":
        pageLoadStrategy = PageLoadStrategy.EAGER;
        break;
      case "none":
        pageLoadStrategy = PageLoadStrategy.NONE;
        break;
      default:
        pageLoadStrategy = PageLoadStrategy.NORMAL;
        logger.info("Стратегии загрузки с таким названием нет. По умолчанию NORMAL");
        break;
    }

    switch (browserName) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        logger.info("Драйвер для браузера Google Chrome");
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, pageLoadStrategy);
        logger.info("Стратегии загрузки страницы: " + pageLoadStrategy.toString());

        optionsChrome.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        optionsChrome.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

        optionsChrome.addArguments("--start-fullscreen");
        optionsChrome.addArguments("--incognito");
        return new ChromeDriver(optionsChrome);
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        logger.info("Драйвер для браузера Mozilla Firefox");

        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, pageLoadStrategy);
        optionsFirefox.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        optionsFirefox.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

        optionsFirefox.addArguments("-kiosk");
        optionsFirefox.addArguments("-private");
        return new FirefoxDriver(optionsFirefox);

      default:
        throw new RuntimeException("Incorrect browser name");
    }
  }
}
