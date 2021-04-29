package tests;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.*;

public class EditPostTest {

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

    @Test
    public void writePost() {
        driver.get("https://pikabu.ru/");
        init.login();
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[3]/a/span")));
        driver.findElement(By.xpath("//div[3]/a/span")).click();
        driver.findElement(By.xpath("//span/div")).click();
        driver.findElement(By.xpath("//div[3]/div[2]")).click();
        driver.findElement(By.xpath("//div[3]/div[2]/div/div/div")).click();
        driver.findElement(By.xpath("//div[3]/div[2]/div/div/div")).click();
        {
            WebElement element = driver.findElement(By.xpath("//div[3]/div[2]/div/div/div"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.xpath("//div[3]/div[2]/div/div/div")).click();
        {
            WebElement element = driver.findElement(By.xpath("//div[3]/div[2]/div/div/div"));
            js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = '<p>test</p>'}", element);
        }
        driver.findElement(By.xpath("//section/div/input")).click();
        driver.findElement(By.xpath("//section/div/input")).sendKeys("tester");
        driver.findElement(By.xpath("//section/div/input")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//section/div/input")).sendKeys("testpost");
        driver.findElement(By.xpath("//section/div/input")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//button/span")).click();

    driver.findElement(By.xpath("//div[2]/header/div[2]")).click();
    driver.findElement(By.xpath("//div[5]/div/div/div/div/div/div")).click();
    driver.findElement(By.xpath("//span/span/span")).click();
    {
      WebElement element = driver.findElement(By.xpath("//span/div"));
      js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = 'test1'}", element);
    }
    driver.findElement(By.xpath("//div[3]/div[2]")).click();
    driver.findElement(By.xpath("//p")).click();
    {
      WebElement element = driver.findElement(By.xpath("//button/span"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    {
      WebElement element = driver.findElement(By.xpath("//div[3]/div[2]/div/div/div"));
      js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = '<p>test1</p>'}", element);
    }
    driver.findElement(By.xpath("//button/span")).click();
    driver.findElement(By.xpath("//div[2]/header/div[2]")).click();
    driver.findElement(By.xpath("//div[5]/div/div[2]")).click();
    driver.findElement(By.xpath("//article/div/div/div[2]/div[2]")).click();
    driver.findElement(By.xpath("//article/div/div/div[2]/div")).click();
    }
}
