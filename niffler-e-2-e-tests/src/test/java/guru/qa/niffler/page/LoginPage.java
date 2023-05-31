package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage<LoginPage>{
    public static final String URL = "http://127.0.0.1:3000/main";
    private final SelenideElement login = $("a[href*='redirect']");
    private final SelenideElement formHeader = $(".form__header");
    private final SelenideElement signInFormParagraph = $(byText(signIn));
    private final SelenideElement userNameInput = $("input[name=username]");
    private final SelenideElement passwordInput = $("input[type=password]");
    private final SelenideElement formPasswordButton = $(".form__password-button");
    private final SelenideElement signInButton = $("button.form__submit");
    private final SelenideElement signUpHyperLink = $(byAttribute("href", "/register"));

    public static final String signIn = "Please sign in";

    @Override
    public LoginPage checkThatPageLoaded() {
        signInFormParagraph.shouldHave(text(signIn));
        return this;
    }

    public MainPage signIn(String userName, String password) {
        Selenide.open(URL);
        login.click();
        userNameInput.setValue(userName);
        passwordInput.setValue(password);
        signInButton.click();
        return new MainPage();
    }
}
