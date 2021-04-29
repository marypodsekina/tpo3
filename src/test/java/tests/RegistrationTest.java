package tests;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;
import java.util.*;

public class RegistrationTest {

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

  //проверка регистрации (если появляется форма для кода подтверждения, то регистрация работает)
  @Test
  public void registration() {
    driver.get("https://pikabu.ru/");
    driver.findElement(By.xpath("//form[@id=\'signin-form\']/div[7]/span")).click();
    driver.findElement(By.xpath("(//input[@name=\'username\'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name=\'username\'])[2]")).sendKeys("mpodsekina");
    driver.findElement(By.xpath("//input[@name=\'phone\']")).click();
    driver.findElement(By.xpath("//input[@name=\'phone\']")).sendKeys("89111943117");
    driver.findElement(By.xpath("//input[@name=\'email\']")).click();
    driver.findElement(By.xpath("//input[@name=\'email\']")).sendKeys("podsekina@gmail.com");
    driver.findElement(By.xpath("(//input[@name=\'password\'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name=\'password\'])[2]")).sendKeys("C37n_Q6C2Rn_bTr");
    driver.findElement(By.xpath("//div[8]/button/span")).click();
    WebDriverWait wait = new WebDriverWait(driver, 120);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[5]/div/div/div/div")));
    assertEquals(driver.findElement(By.xpath("//div[5]/div/div/div/div")).getText(), "Введите код из SMS сообщения");
    driver.findElement(By.xpath("//div[5]/div/div/div/div")).click();
    driver.findElement(By.xpath("//input[@name=\'confirm\']")).sendKeys("1234");
  }
}
