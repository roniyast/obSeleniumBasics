package com.obsqura.selenium;

import org.openqa.selenium.WebDriver;
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
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().window().maximize();
        //driver.close();
    }

    public static void main(String args[]) {

        BrowserLaunch.testInitialize("chrome");
        String title = driver.getTitle();
        System.out.println("Title :" + title);
        String url = driver.getCurrentUrl();
        System.out.println("URL :" + url);
        String pageSource = driver.getPageSource();
        System.out.println("PageSource :" + pageSource);
        driver.close();
        //launch.testInitialize("firefox");
        //launch.testInitialize("edge");

    }
}
