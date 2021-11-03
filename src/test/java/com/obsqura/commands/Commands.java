package com.obsqura.commands;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
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
        // driver.close();
        //driver.quit();
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

    @Test(priority = 7, enabled = false)
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
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,750)");
        WebElement promptButton = driver.findElement(By.xpath("//button[@id='promtButton']"));
        promptButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Automation");
        alert.accept();
    }

    @Test(priority=11,enabled=false)
    public void verifySampleFormDemo(){
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement text1 = driver.findElement(By.id("single-input-field"));
        String expectedValue = "Hello World";
        text1.sendKeys(expectedValue);
        WebElement button1= driver.findElement(By.id("button-one"));
        button1.click();
        WebElement text=driver.findElement(By.id("message-one"));
        String actualValue=text.getText().substring(15);
        System.out.println(actualValue);
        Assert.assertEquals(actualValue,expectedValue,"Texts not matching");
    }

    @Test(priority=12,enabled=false)
    public void verifyMultipleInputDemo(){
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        int a=10,b=20;
        int expectedMessage=a+b;
        WebElement valueA = driver.findElement(By.id("value-a"));
        valueA.sendKeys(""+a);
        WebElement valueB = driver.findElement(By.id("value-b"));
        valueB.sendKeys(""+b);
        WebElement button2= driver.findElement(By.id("button-two"));
        button2.click();
        String message = driver.findElement(By.id("message-two")).getText().substring(14);
        int actualMessage =Integer.parseInt(message);
        Assert.assertEquals(actualMessage,expectedMessage,"Values not matching");
    }

    @Test(priority = 13, enabled = false)
    public void verifySimpleCheckboxDemo() {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement checkBox = driver.findElement(By.id("gridCheck"));
        checkBox.click();
        boolean value = checkBox.isSelected();
        Assert.assertTrue(value, "Checkbox is not selected");
    }

    @Test(priority = 14 ,enabled = false)
    public void verifyMultipleCheckboxDemo(){
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement button2 = driver.findElement(By.id("button-two"));
        button2.click();
        String valueTrue = driver.findElement(By.id("is_checked")).getAttribute("value");
        Assert.assertEquals(valueTrue,"true","Not selected");
        }

    @Test(priority = 15, enabled = false)
    public void verifyMultipleWindow() {
        driver.get("http://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window Handle id : " + parentWindow);
        WebElement clickHere = driver.findElement(By.linkText("Click Here"));
        clickHere.click();
        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows);

        Iterator<String> handles = windows.iterator();
        while (handles.hasNext()) {
            String childWindow = handles.next();
            if (!(childWindow.equals(parentWindow))) {
                driver.switchTo().window(childWindow);
                WebElement email = driver.findElement(By.name("emailid"));
                email.sendKeys("roniyast@gmail.com");
                WebElement submit = driver.findElement(By.name("btnLogin"));
                submit.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
    @Test(priority = 16, enabled = false)
    public void verifyDropDowns() {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        WebElement dropDown = driver.findElement(By.name("country"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("INDIA");
        //select.selectByValue("INDIA");
        //select.selectByIndex(10);
    }
    @Test(priority = 17,enabled = false)
    public void verifyDropDownValues(){
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        List<String> expectedColourValue=new ArrayList<>();
        expectedColourValue.add("Red");
        expectedColourValue.add("Yellow");
        expectedColourValue.add("Green");
        WebElement colourDropDown=driver.findElement(By.id("single-input-field"));
        Select select=new Select(colourDropDown);
        List<WebElement> actualColourWebElements=select.getOptions();
        List<String> actualColourValue=new ArrayList<String>();
        for(int i=1;i<actualColourWebElements.size();i++){
            actualColourValue.add(actualColourWebElements.get(i).getText());
        }
        Assert.assertEquals(actualColourValue,expectedColourValue,"drop down value mismatch found in colour list");
    }
    @Test(priority=18,enabled=true)
    public void verifyMultiSelectDropDown()  {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        List<String> expectedColourValueList=new ArrayList<>();
        expectedColourValueList.add("Red");
        expectedColourValueList.add("Yellow");
        expectedColourValueList.add("Green");

        WebElement multiSelectDropDown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(multiSelectDropDown);
        select.selectByIndex(0);
       /* String actualFirstSelected = select.getFirstSelectedOption().getText();
        String expectedColourValue = expectedColourValueList.get(0);
        Assert.assertEquals(actualFirstSelected,expectedColourValue,"Values not matching");*/

        select.selectByIndex(1);
        select.selectByIndex(2);
        List<WebElement> actualColourValueWebElement = select.getAllSelectedOptions();
        List<String> actualColourValueList = new ArrayList<>();
        for(int i=0;i<actualColourValueWebElement.size();i++){
            actualColourValueList.add(actualColourValueWebElement.get(i).getText());
        }
        System.out.println(actualColourValueList);
        Assert.assertEquals(actualColourValueList,expectedColourValueList,"Values not matching");

        //select.deselectAll();
        //select.deselectByIndex(0);
        //select.deselectByValue("Red");
        //select.deselectByVisibleText("Red");
    }

}
