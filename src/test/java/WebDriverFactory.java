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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;

public class WebDriverFactory {
  private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

  public static WebDriver getDriver(String browserName) {
    ChromeOptions options = new ChromeOptions();
    switch (browserName) {
      case "chrome":
      case "\'chrome\'":
        WebDriverManager.chromedriver().setup();
        logger.info("Драйвер для браузера Google Chrome");
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
        options.setAcceptInsecureCerts(false);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        return new ChromeDriver(options);
      case "firefox":
      case "\'firefox\'":
        WebDriverManager.firefoxdriver().setup();
        logger.info("Драйвер для браузера Mozilla Firefox");
        return new FirefoxDriver();
      default:
        throw new RuntimeException("Incorrect browser name");
    }
  }
}
