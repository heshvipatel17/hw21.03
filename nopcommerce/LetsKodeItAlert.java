package nopcommerce;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LetsKodeItAlert {
    private WebDriver driver;
    private String baseUrl = "https://learn.letskodeit.com/p/practice";
    private JavascriptExecutor js;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setPosition(new Point(-2000, 0));//display 2
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    @Test
    public void tacleAlert() throws InterruptedException {
        //element of Practice
        driver.findElement(By.linkText("Practice")).click();
        Thread.sleep(2000);

        //to scrool the page down
        js.executeScript("window.scrollBy(0, -1800);");
        Thread.sleep(2000);

        //element of Alert
        driver.findElement(By.xpath("//input[@id='alertbtn']"));
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @After
    public void closeBroser() {
        // driver.quit();
    }


}