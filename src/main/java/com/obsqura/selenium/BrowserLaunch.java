package com.obsqura.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {

    WebDriver driver;


    public void testInitialize(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            steps();
        } else if (browser.equalsIgnoreCase("firefox")) {
            steps();
        } else if (browser.equalsIgnoreCase("edge")) {
            steps();

        }
    }

    public void steps() {
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().window().maximize();
        driver.close();
    }

    public static void main(String args[]) {

        BrowserLaunch launch = new BrowserLaunch();

        System.setProperty("webdriver.chrome.driver", "H:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
        launch.driver = new ChromeDriver();
        launch.testInitialize("chrome");

        System.setProperty("webdriver.gecko.driver", "H:\\selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
        launch.driver = new FirefoxDriver();
        launch.testInitialize("firefox");

        System.setProperty("webdriver.edge.driver", "H:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
        launch.driver = new EdgeDriver();
        launch.testInitialize("edge");
    }
}
