package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage<ProfilePage>{

    public static final String addNewCategory = "Add new category";

    private final SelenideElement addCategoryInput = $("input[name=category]");
    private final SelenideElement createCategoryButton = $(byText("Create"));
    @Override
    public ProfilePage checkThatPageLoaded() {
        $(byText(addNewCategory)).shouldBe(Condition.visible);
        return this;
    }
    public ProfilePage addNewCategory(String categoryName){
        addCategoryInput.setValue(categoryName);
        createCategoryButton.click();
        return this;
    }
}
