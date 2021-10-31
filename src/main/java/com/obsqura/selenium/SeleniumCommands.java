package com.obsqura.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class SeleniumCommands {
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
       // driver.navigate().to("http://demowebshop.tricentis.com/");
        //driver.close();
    }
public static void main(String args[]){
    SeleniumCommands.testInitialize("chrome");
/*
    WebElement rememberMe = driver.findElement(By.id("RememberMe"));
    //rememberMe.click();
    //rememberMe.click();
    boolean rememberMeValue = rememberMe.isSelected();
    System.out.println("Remember me Value : "+rememberMeValue);
    WebElement loginButton = driver.findElement(By.className("login-button"));
    boolean loginButtonValue=loginButton.isEnabled();
    //boolean loginButtonValue=loginButton.isDisplayed();
    System.out.println("Login Button Value : "+loginButtonValue);*/

    WebElement registerMenu = driver.findElement(By.className("ico-register"));
    registerMenu.click();
    List<WebElement> genders=driver.findElements(By.name("Gender"));
    for(int i=0;i<genders.size();i++){
        String value=genders.get(i).getAttribute("id");
        if(value.equals("gender-female")){
            genders.get(i).click();
        }
    }


  /*  WebElement femaleRadioButton = driver.findElement(By.id("gender-female"));
    femaleRadioButton.click();
    boolean status = femaleRadioButton.isSelected();
    System.out.println("Radio Button Status : "+status);*/
    /*//navigation
    driver.navigate().back();
    driver.navigate().forward();
    driver.navigate().refresh();*/
}
}
