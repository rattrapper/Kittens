import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KittensRating {
    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public void MainTest() {
        driver.get("http://y8.com");
        MainPage mainPage = new MainPage(driver);
        CategoryPage girlsGames = mainPage.openCategory("Girls");
        CategoryPage kittenGames = girlsGames.openSubcategory("Котенок");
        kittenGames.sort(By.Rating);
        kittenGames.scrollToTheEnd();
        kittenGames.assertSortedByRating();
    }
}
