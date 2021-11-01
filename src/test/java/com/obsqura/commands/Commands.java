package com.obsqura.commands;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class Commands {

    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "H:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "H:\\selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "H:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setup() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test(priority = 1, enabled = false)
    public void verifyUserLogin() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("roniyat@gmail.com");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("123456");
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();

        WebElement userNameDisplayed = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = userNameDisplayed.getText();
        String expectedUserName = "roniyat@gmail.com";
        Assert.assertEquals(actualUserName, expectedUserName, "Login Failed");
    }

    @Test(priority = 2, enabled = false)
    public void verifyHomePageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Home Page Title");
    }

    @Test(priority = 3, enabled = false)
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFIle = driver.findElement(By.cssSelector("input#uploadfile_0"));
        chooseFIle.sendKeys("H:\\Automation Testing\\Assignments\\Demo\\sample.txt");
        WebElement acceptTerms = driver.findElement(By.cssSelector("input#terms"));
        acceptTerms.click();
        WebElement submitButton = driver.findElement(By.cssSelector("button#submitbutton"));
        submitButton.click();
    }

    @Test(priority = 4, enabled = false)
    public void verifyConfirmationAlert() {
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement customerId = driver.findElement(By.name("cusid"));
        customerId.sendKeys("123456");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();

        Alert alert = driver.switchTo().alert();
        //alert.accept();
        //alert.dismiss();
        String text = alert.getText();
        alert.accept();
        System.out.println(text);
    }

    @Test(priority = 5, enabled = false)
    public void verifyFramesInHTML() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame("frame1"); //By Id
        //driver.switchTo().frame(1);        //element no
        WebElement frameElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameElement); //Webelement

        //driver.switchTo().parentFrame(); //Methods to switch to parent frame
        driver.switchTo().defaultContent();

        WebElement header1 = driver.findElement(By.className("header-wrapper"));
        String tag = header1.getTagName();
        System.out.println(tag);

    }

    @Test(priority = 6, enabled = false)
    public void verifyTotalNumberOfFrames() {
        driver.get("https://demoqa.com/frames");
        List<WebElement> webElementList = driver.findElements(By.tagName("iframe"));
        int totalFrameNo = webElementList.size();
        System.out.println(totalFrameNo);
    }

    //ToolsQA Alert Examples

    @Test(priority = 7, enabled = true)
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement simpleAlert = driver.findElement(By.id("alertButton"));
        simpleAlert.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 8, enabled = false)
    public void verifyFiveSecWaitAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 9, enabled = false)
    public void verifyToolsQAConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement confirmationAlert = driver.findElement(By.id("confirmButton"));
        confirmationAlert.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 10, enabled = false)
    public void verifyPromptAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement promptButton = driver.findElement(By.xpath("//button[@id='promtButton']"));
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].click()", promptButton);
        promptButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Automation");
        alert.accept();
    }

}
