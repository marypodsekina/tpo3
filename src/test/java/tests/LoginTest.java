package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.*;

public class LoginTest {
  private WebDriver driver;
  private final Init init = new Init();
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

  //тест на авторизацию
  @Test
  public void loginTest() {
    init.login();
    //если запросит капчу
    WebDriverWait wait = new WebDriverWait(driver, 120);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'whereismyson')]")));
    //проверка входа
    Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'whereismyson')]")).getText(), "whereismyson");
    //выход из аккаунта
    WebElement webElement = driver.findElement(By.xpath("//div/div/div/div[2]/div[2]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    webElement = driver.findElement(By.xpath("//div/div[2]/button"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
  }

  //тест на неверно введенные данные
  @Test
  public void wrongTest(){
    driver.get("https://pikabu.ru/");
    driver.findElement(By.xpath("//input[@name=\'username\']")).click();
    driver.findElement(By.xpath("//input[@name=\'username\']")).sendKeys("rumashechk");
    driver.findElement(By.xpath("//input[@name='password']")).click();
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("DU_VvtXX9khERxu");
    driver.findElement(By.xpath("//button/span")).click();
    WebDriverWait wait = new WebDriverWait(driver, 120);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,\'Ошибка. Вы ввели неверные данные авторизации\')]")));
    //проверка есть ли кнопка выхода (не должно быть)
    Assert.assertEquals("выйти", driver.findElement(By.xpath("//div/div/div/div[2]/div[2]")).getText());
  }
}
