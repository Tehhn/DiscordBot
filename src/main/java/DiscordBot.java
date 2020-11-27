import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DiscordBot {
    WebDriver driver;

    public void invokeBrowser() {
        //Open the browser and Login to Discord Webpage application.
        String driverPath;
        driverPath = "C:\\Users\\Tehhn\\bin\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void navigateLogin(String username,String password) {
        try {
            //Login and going to the friends tab.
            driver.get("https://discord.com");
            // Button has no 'better' property, this is prone to being changed in the future.
            driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/header[2]/nav/div[2]/a")).click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("email")).sendKeys(username);
            driver.findElement(By.xpath("//*[@id=\"app-mount\"]/div[2]/div/div[2]/div/div/form/div/div/div[1]/div[3]/button[2]")).click();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfAnyMessages() {
        /*this method to check if element exists is recommended by official documentation,
        I'd still think there is a smarter solution */

        if (driver.findElements(By.xpath("//div [@class='wrapper-1BJsBx'][@role ='listitem']")).size() > 0) {
            return true;
        } else {
            //System.out.println("False");
            return false;

        }

    }

    public void AutomaticAnswer(String automaticMessage) {
        try {
            Thread.sleep(1000);
            this.driver.findElement(By.xpath("//div [@class='wrapper-1BJsBx'][@role ='listitem']")).click();

            Thread.sleep(1000);

            /*WebElement element1 = driver.findElement(By.cssSelector("[class*='textArea-12jD']"));
            action.sendKeys(element1, ""+ automaticMessage + Keys.ENTER).build().perform();*/

            WebElement TextBox = this.driver.findElement(By.xpath("//div [@role='textbox'][@class = 'markup-2BOw-j slateTextArea-1Mkdgw fontSize16Padding-3Wk7zP']"));
            TextBox.sendKeys(""+ automaticMessage + Keys.ENTER);

            Thread.sleep(2000);
            driver.navigate().back();


        } catch (InterruptedException | NoSuchElementException e) {
            e.printStackTrace();
        }
        System.out.println("I answered to one of the messages!");

    }



    public static void main(String[] args)  {
        //long startTime = System.nanoTime();

        Login login = new Login();
        String[] upm = new String[3];
        try {
            upm = login.loadInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DiscordBot bot = new DiscordBot();
        bot.invokeBrowser();
        bot.navigateLogin(upm[0],upm[1]);

        boolean tempBool = true;
        while(tempBool) {

            if (bot.checkIfAnyMessages()) {

                bot.AutomaticAnswer(upm[2]);
            } else {
                try {
                    System.out.println("No new messages, sleeping for a minute");
                    Thread.sleep(60000);
                    System.out.println("Actively looking for messages again");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tempBool = true;
        }

        //long endTime = System.nanoTime();
        //System.out.println("Time program took in seconds: " + (endTime-startTime)/1000000000);







    }
}
