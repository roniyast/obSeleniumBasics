package com.obsqura.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        //driver.close();
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

    @Test(priority=3,enabled=true)
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFIle = driver.findElement(By.cssSelector("input#uploadfile_0"));
        chooseFIle.sendKeys("H:\\Automation Testing\\Assignments\\Demo\\sample.txt");
        WebElement acceptTerms = driver.findElement(By.cssSelector("input#terms"));
        acceptTerms.click();
        WebElement submitButton = driver.findElement(By.cssSelector("button#submitbutton"));
        submitButton.click();
    }


}
