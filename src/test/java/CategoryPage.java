import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

class CategoryPage extends MainPage {
    @FindBy(css = "ul.item-tags a")
    private List<WebElement> subcategories;
    @FindBy(css = "#sort option")
    private List<WebElement> options;

    CategoryPage(WebDriver driver) {
        super(driver);
    }

    CategoryPage openSubcategory(String name) {
        Optional<WebElement> category = subcategories.stream()
                .filter(cat -> cat.getText().contains(name)).findAny();
        assertTrue(name + " category not found", category.isPresent());
        category.get().click();
        return new CategoryPage(driver);
    }

    void sort(By option) {
        String value;
        switch (option) {
            case Rating:
                value = "rating";
                break;
            case Date:
                value = "date";
                break;
            default:
                value = null;
                break;
        }
        options.stream().filter(opt -> opt.getAttribute("value").equals(value))
                .findFirst().get().click();
    }

}
