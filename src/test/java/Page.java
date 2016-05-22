import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

class Page {
    WebDriver driver;
    WebDriverWait wait;

    Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 2);
        PageFactory.initElements(driver, this);

    }
}
