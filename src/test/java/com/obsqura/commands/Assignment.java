package com.obsqura.commands;

import javafx.beans.value.WeakChangeListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.tools.JavaCompiler;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Assignment {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "H:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver= new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "");
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setup() {
        testInitialize("chrome");
    }
    @AfterMethod
    public void tearDown(){
       // driver.close();
    }
    @Test(priority=1,enabled=true)
    public void test() {
        driver.get("https://demoqa.com/elements");

        /*String title = driver.getTitle();
        System.out.println("Title : "+title);
        String pageSource = driver.getPageSource();
        System.out.println("Page Source : "+pageSource);
        String currentURL= driver.getCurrentUrl();
        System.out.println("URL : "+currentURL);*/

        /*WebElement textBoxList = driver.findElement(By.xpath("//li[@id='item-0']"));
        textBoxList.click();
        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("abc");
        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("abc@gmail.com");
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("abc House ; cde lane ; fgh Po ; ijk ");
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("abc House ; cde lane ; fgh Po ; ijk");
        WebElement submit = driver.findElement(By.id("submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",submit);
        submit.click();*/

        /*WebElement checkBoxList = driver.findElement(By.xpath("//li[@id='item-1']"));
        checkBoxList.click();
        WebElement checkBox = driver.findElement(By.className("rct-checkbox"));
        if(!(checkBox.isSelected())){
            checkBox.click();
        }
        WebElement downArrowButton = driver.findElement(By.xpath("//button[@class='rct-collapse rct-collapse-btn']"));
        downArrowButton.click();
        boolean checkboxClicked = driver.findElement(By.id("tree-node-home")).isSelected();
        System.out.println(checkboxClicked);
        Assert.assertEquals(true,checkboxClicked,"Not selected");*/

       /* JavascriptExecutor js =(JavascriptExecutor)driver;
        WebElement radioButtonList = driver.findElement(By.xpath("//li[@id='item-2']"));
        js.executeScript("window.scrollBy(0,100);");
        radioButtonList.click();
        js.executeScript("document.getElementById('yesRadio').click();");
        WebElement radioYes = driver.findElement(By.id("yesRadio"));
        //radioYes.click();cccc
        Assert.assertEquals(true,radioYes.isSelected(),"Not Selected");

        boolean value= driver.findElement(By.id("noRadio")).isEnabled();
        Assert.assertEquals(false,value,"Not Disabled");*/
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500);");

        WebElement hyperLinkList = driver.findElement(By.xpath("//li[@id='item-5']"));
        hyperLinkList.click();
        String parentWindow = driver.getWindowHandle();
        List<WebElement> firstLink = driver.findElements(By.partialLinkText("Home"));
        //System.out.println(firstLink);
        System.out.println("1 "+firstLink.get(0).getText());
        System.out.println("2 "+firstLink.get(1).getText());
        firstLink.get(0).click();
        Set<String> windows = driver.getWindowHandles();
        //System.out.println(windows);
        Iterator<String> it = windows.iterator() ;
        while(it.hasNext()){
            String childWindow = it.next();
           if(!(childWindow.equals(parentWindow))){
               driver.switchTo().window(childWindow);
               //System.out.println(driver.getCurrentUrl());
              Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/","Url doesnot match");
            }
        }
       driver.switchTo().defaultContent();

        //driver.switchTo().window(parentWindow);
    }
}
