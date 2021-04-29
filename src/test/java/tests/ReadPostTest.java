package tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.*;
import util.Init;

import java.util.*;

public class ReadPostTest {

  private final Init init = new Init();
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = init.initWebDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }
  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }

  //выбираем первую статью на стартовой старнице и содержит ли URL /story/ (елси да, то статью можно прочитать)
  @Test
  public void readPost() {
    driver.get("https://pikabu.ru/");
    vars.put("window_handles", driver.getWindowHandles());
    driver.findElement(By.xpath("//h2/a")).click();
    vars.put("win4536", waitForWindow(2000));
    driver.switchTo().window(vars.get("win4536").toString());
    assertTrue(driver.getCurrentUrl().contains("/story/"));

  }
}
