package service;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import domain.BitchuteUpload;
import domain.BitchuteVideo;
import exception.AuthenticateErrorException;

public class UploadService {

    public static final String BITCHUTE_URL = "https://www.bitchute.com/";

    public void launchSeleniumBot(BitchuteUpload bitchuteUpload, BitchuteVideo video) {
        System.setProperty("webdriver.chrome.driver", bitchuteUpload.getChromeDriver());
        WebDriver driver;
        if (bitchuteUpload.isHeadless()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(BITCHUTE_URL);

        if (!successToLogIn(bitchuteUpload, driver)) {
            throw new AuthenticateErrorException("Error during the authentication: user#" + bitchuteUpload.getUser());
        }

        driver.get(BITCHUTE_URL + "/myupload/");
        driver.findElement(By.cssSelector("textarea[name='upload_title']")).sendKeys(video.getTitle());
        driver.findElement(By.cssSelector("textarea[name='upload_description'")).sendKeys(video.getDescription());
        driver.findElement(By.id("fileupload")).sendKeys(video.getVideoPath());
        driver.findElement(By.id("fileupload")).sendKeys(video.getCoverPath());
        tryToFinish(driver.findElement(By.id("finish-button")));
    }

    private void tryToFinish(WebElement finishButton) {
        try {
            finishButton.click();
        } catch (ElementClickInterceptedException exception) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tryToFinish(finishButton);
        }
    }

    private boolean successToLogIn(BitchuteUpload bitchuteUpload, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable( //
                driver.findElement(By.className("unauth-link")).findElements(By.tagName("a")).stream().findFirst().orElseThrow())).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_username"))).sendKeys(bitchuteUpload.getUser());
        driver.findElement(By.id("id_password")).sendKeys(bitchuteUpload.getPassword());
        driver.findElement(By.id("auth_submit")).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auth_login_error")));
        } catch (TimeoutException exception) {
            return true;
        }
        return driver.findElement(By.id("auth_login_error")).getAttribute("class").contains("hidden");
    }


}
