package tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import static org.junit.Assert.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.*;

public class FindPostTest {

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

  //проверяем работоспособность поиска статей по вводу слова (если в url ключевое слово есть, то поиск считается успешным)
  @Test
  public void findPost() {
    driver.get("https://pikabu.ru/");
    WebElement webElement = driver.findElement(By.xpath("//input"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    driver.findElement(By.xpath("//input[@name=\'q\']")).sendKeys("test");
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[contains(.,'Показать все результаты')]")));
    driver.findElement(By.xpath("//i[contains(.,'Показать все результаты')]")).click();
    driver.findElement(By.xpath("(//input[@name=\'q\'])[2]")).click();
    System.out.println(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().contains("test"));
  }
}
