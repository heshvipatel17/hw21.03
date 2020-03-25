package nopcommerce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookTestH {

    private WebDriver driver;
    private String baseUrl = "https://demo.nopcommerce.com/";
    private JavascriptExecutor js;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void userNavigateToBookPageSuccessfully() throws InterruptedException {
        WebElement booksLink = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[5]/a"));
        //MouseHover on Books
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(booksLink).perform();
        Thread.sleep(2000);
        booksLink.click();

        WebElement booksTxt = driver.findElement(By.xpath("//div[@class='page-title']/h1"));
        String actualTxt = booksTxt.getText();
        String expectedTxt = "Books";
        Assert.assertEquals(actualTxt, expectedTxt);
        Thread.sleep(3000);

    }

    @Test
    public void booksArrangedInAscendingOrderAtoZ() throws InterruptedException {
        //WebElement Books Link
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[5]/a")).click();

        //WebElement list of names of books before sorting them in AtoZ order using dropdown menu
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
    public void bookAddedToWishlistSuccessfully() throws InterruptedException {
        //WebElement Books Link
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[5]/a")).click();
        //WebElement Position dropdown box
        WebElement positionDropDown = driver.findElement(By.cssSelector("select#products-orderby"));
        Select select = new Select(positionDropDown);
        Thread.sleep(2000);
        select.selectByIndex(1);
        //Scroll down page
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        //WebElement for wishlist button
        driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[3]")).click();
        WebElement itemAddedToWishlist = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedTxt = "The product has been added to your wishlist";
        String actualTxt = itemAddedToWishlist.getText();
        Assert.assertEquals(expectedTxt, actualTxt);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
