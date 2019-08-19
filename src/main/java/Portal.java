import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Portal {

    static WebElement idField(WebDriver driver) {
        return driver.findElement(By.id("login"));
    }

    static WebElement passField(WebDriver driver) {
        return driver.findElement(By.id("password"));
    }

    static WebElement enterPortal(WebDriver driver) {
        return driver.findElement(By.id("submit"));
    }
}
