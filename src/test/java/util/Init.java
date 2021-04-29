package util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Init {
    private final Properties property = new Properties();
    private FileInputStream fileInputStream;
    private WebDriver driver;

    public Init() {
        try {
            fileInputStream = new FileInputStream(".\\src\\test\\resources\\config.properties");
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public void login() {
        driver.get("https://pikabu.ru/");
        driver.findElement(By.xpath("//input[@name='username']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("whereismyson");
        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rTJEaQN6vQpn@m6");
        driver.findElement(By.xpath("//button/span")).click();
    }

    public String getProperty(String name) {
        return property.getProperty(name);
    }
}
