package nopcommerce;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ElectronicsTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void hoverElectronicsAndClicktoCamaraAndPhoto() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("heshvipatel17@gmail.com");
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Abc@123");
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
    }
    @Test
    public void camaraAndPhotoShortByDecending(){

    }
}

