package nopcommerce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookTest {
    private WebDriver driver;
    private String baseUrl = "https://demo.nopcommerce.com/";
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
    public void userShouldNavigatetoBooksPage() throws InterruptedException {
        driver.findElement(By.linkText("Books")).click();
        WebElement assertTxt = driver.findElement(By.xpath("//div[@class='page-title']/h1 "));
        String expectedTxt = "Books";
        String actualTxt = assertTxt.getText();
        Assert.assertEquals(actualTxt, expectedTxt);
        Thread.sleep(3000);

    }

    @Test
    public void booksShortByAscendingOrder() throws InterruptedException {
        driver.findElement(By.linkText("Books")).click();
        // driver.findElement(By.linkText("Position")).click();
        Thread.sleep(3000);

//        WebElement dropDown = driver.findElement(By.id("products-orderby"));
//        Thread.sleep(3000);
//        Select select = new Select(dropDown);
//        select.selectByVisibleText("Name: A to Z");
//        Thread.sleep(3000);

        List<WebElement> allResults = driver.findElements(By.xpath("//div[@class='product-grid']//h2/a"));
        //Store results in  actualList arraylist
        ArrayList<String> actualList = new ArrayList<>(allResults.size());
        for (int i = 0; i < 3; i++) {
            actualList.add(allResults.get(i).getText());
        }
        System.out.println("The actualList before sorting: " + actualList);
        Collections.sort(actualList);

        //WebElement Position dropdown box
        WebElement positionDropDown = driver.findElement(By.cssSelector("select#products-orderby"));
        Select select = new Select(positionDropDown);
        Thread.sleep(2000);
        select.selectByIndex(1);

        //Scroll down page
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);

        //WebElement list of names of books after sorting them in AtoZ order using dropdown menu
        List<WebElement> allResults1 = driver.findElements(By.xpath("//div[@class='product-grid']//h2/a"));
        //Store results in  expectedList arraylist
        ArrayList<String> expectedList = new ArrayList<>(allResults1.size());
        for (int j = 0; j < 3; j++) {
            expectedList.add(allResults1.get(j).getText());
        }
        System.out.println("The expectedList before sorting: " + expectedList);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void productAddToWishList() throws InterruptedException {
        driver.findElement(By.linkText("Books")).click();
        Thread.sleep(3000);

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Thread.sleep(3000);
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name: A to Z");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[3]")).click();
        Thread.sleep(3000);

        WebElement assertText = driver.findElement(By.xpath("//div[@style='display: block;']//p"));
        String expertText = "The product has been added to your wishlist";
        String actualText = assertText.getText();
        Assert.assertEquals(expertText, actualText);
        Thread.sleep(3000);

    }
    @After
    public void closeBrowser(){
       driver.quit();
    }
}
