package com.obsqura.selenium;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {

    static WebDriver driver;

    public static void testInitialize(String browser) {

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
        steps();
    }

    public static void steps() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/login");
        //driver.close();
    }

    public static void main(String args[]) {

        BrowserLaunch.testInitialize("chrome");
        String title = driver.getTitle();
        System.out.println("Title :" + title);
        String url = driver.getCurrentUrl();
        System.out.println("URL :" + url);
        String pageSource = driver.getPageSource();
        //System.out.println("PageSource :" + pageSource);

        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        String loginMenuText =loginMenu.getText();
        System.out.println("Login Menu Text : "+loginMenuText);
        loginMenu.click();  
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("roniyast");
        userName.clear();
        String classValue = userName.getAttribute("class");
        System.out.println("ClassValue : "+classValue);
        String tagName = userName.getTagName();
        System.out.println("Tag Name : "+tagName);
        WebElement password = driver.findElement(By.id("Password"));
        //password.sendKeys("user1234");
        WebElement submit = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
        //System.out.println("Submit : "+submit);
        //submit.click();
        // driver.close();
        //launch.testInitialize("firefox");
        //launch.testInitialize("edge");

  /*      // WebElements

        WebElement userName1 = driver.findElement(By.id("Email"));
        userName1.sendKeys("roniyast");
        userName1.click();

        WebElement name = driver.findElement((By.name("Email")));
        WebElement className = driver.findElement((By.className("email")));
        WebElement xpath = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        WebElement cssSelector = driver.findElement(By.cssSelector("#Email"));
        WebElement tagName = driver.findElement(By.tagName("input"));
        WebElement linkText = driver.findElement(By.linkText("Log In"));
        WebElement partialLinkText = driver.findElement(By.partialLinkText("log"));*/

    }
}
