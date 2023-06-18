package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.component.Header;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage<ProfilePage>{

    public static final String addNewCategory = "Add new category";

    private final SelenideElement addCategoryInput = $("input[name=category]");
    private final SelenideElement createCategoryButton = $(byText("Create"));
    private final SelenideElement profileAvatarButton = $("button[class='profile__avatar-edit']");
    private final SelenideElement closeAvatarIconButton = $("button[class='button-icon button-icon_type_close']");

    private final Header header = new Header();
    public Header getHeader() {
        return header;
    }
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

    public ProfilePage CloseAvatarIconIsOnPage(){
        profileAvatarButton.click();
        closeAvatarIconButton.shouldBe(Condition.visible);
        return this;
    }
}
