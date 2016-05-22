import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Integer.parseInt;

class Game {
    private WebElement el;

    Game(WebElement el) {
        this.el = el;
    }

    String getName() {
        return el.findElement(By.cssSelector("div.game-title")).getText().trim();
    }

    int getRating() {
        try {
            return parseInt(el.findElement(By.cssSelector("div.item-rating")).getText().trim().replace("%", ""));
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
