import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;

class MainPage extends Page {


    @FindBy(css = "div.top-tags a")
    private List<WebElement> categories;
    @FindBy(className = "item")
    private List<WebElement> games;

    MainPage(WebDriver driver) {
        super(driver);
    }

    void scrollToTheEnd() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        By gamesLocator = By.className("item");
        int count = games.size();
        do {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                count = wait.until(numberOfElementsToBeMoreThan(gamesLocator, count)).size();
            } catch (TimeoutException e) {
                break;
            }
        } while (true);
    }

    CategoryPage openCategory(String name) {
        Optional<WebElement> category = categories.stream()
                .filter(cat -> cat.getText().contains(name)).findAny();
        assertTrue(name + " category not found", category.isPresent());
        category.get().click();
        //By headerLocator = By.xpath(String.format("//h1[contains(text(), '%s')]", name));
        //wait.until(presenceOfElementLocated(headerLocator));
        return new CategoryPage(driver);
    }

    private List<Game> getGames() {
        return games.stream().filter(WebElement::isDisplayed).map(Game::new).collect(toList());
    }

    void assertSortedByRating() {
        List<Game> games = getGames();
        int previous = games.get(0).getRating();
        games.remove(0);
        for (Game game : games) {
            int rating = game.getRating();
            assertTrue("Games are sorted improperly", rating <= previous);
            previous = rating;
        }
    }
}
