package nopcommerce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ElectronicsTest {
    private WebDriver driver;
    String baseUrl = "https://demo.nopcommerce.com/";
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
    public void hoverElectronicsAndClicktoCamaraAndPhoto() throws InterruptedException {

        WebElement electonics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        electonics.click();
        Thread.sleep(3000);

        WebElement cameraAndPhoto = driver.findElement(By.xpath("//li[@class='active last']//a[contains(text(),'Camera & photo')]"));
        cameraAndPhoto.click();
        WebElement assertText = driver.findElement(By.xpath("//h1[contains(text(),'Camera & photo')]"));
        Thread.sleep(3000);
        String expectedTxt = "Camera & photo";
        String accutalTxt = assertText.getText();
        Assert.assertEquals(accutalTxt, expectedTxt);
        Thread.sleep(3000);

    }

    @Test
    public void sortPriceLowToHigh() throws InterruptedException {

        WebElement electronicsLink = driver.findElement(By.linkText("Electronics"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsLink).perform();

        WebElement cameraNphoto = driver.findElement(By.linkText("Camera & photo"));
        actions.moveToElement(cameraNphoto).perform();
        cameraNphoto.click();
        Thread.sleep(3000);
        //WebElement Position drop down menu
        WebElement sortByDropDownMenu = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortByDropDownMenu);
        select.selectByIndex(3);
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,500);");
        Thread.sleep(2000);

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
