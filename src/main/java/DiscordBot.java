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

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tehhn\\bin\\chromedriver.exe");
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

        if (driver.findElements(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/nav/div[2]/div[2]/div/div[2]/div/div/div")).size() > 0) {
            //System.out.println("True");
            return true;
        } else {
            //System.out.println("False");
            return false;

        }

    }

    public void AutomaticAnswer(String automaticMessage) {
        try {
            Thread.sleep(500);
            Actions action = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/nav/div[2]/div[2]/div/div[2]/div/div/div"));
            action.click(element).build().perform();
            Thread.sleep(500);
            WebElement element1 = driver.findElement(By.cssSelector("[class*='textArea-12jD']"));
            action.sendKeys(element1, ""+ automaticMessage + Keys.ENTER).build().perform();
            Thread.sleep(500);
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

            if (bot.checkIfAnyMessages() == true) {

                bot.AutomaticAnswer(upm[2]);
            } else {
                try {
                    System.out.println("No new messages, sleeping for a minute");
                    Thread.sleep(60000);
                    System.out.println("Actively looking for messages again");
                    tempBool = true;
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
