package guru.qa.niffler.page.component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.BaseComponent;
import guru.qa.niffler.page.FriendsPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.page.ProfilePage;

public class Header extends BaseComponent<Header> {

  public Header() {
    super($(".header"));
  }

  private final SelenideElement mainPageBtn = $("a[href*='main']");
  private final SelenideElement friendsPageBtn = $("a[href*='friends']");
  private final SelenideElement profilePageBtn = $("a[href*='profile']");

  @Override
  public Header checkThatComponentDisplayed() {
    self.$(".header__title").shouldHave(text("Niffler. The coin keeper."));
    return this;
  }

  public FriendsPage goToFriendsPage() {
    friendsPageBtn.click();
    return new FriendsPage();
  }

  public MainPage goToMainPage() {
    mainPageBtn.click();
    return new MainPage();
  }

  public ProfilePage goToProfilePage() {
    profilePageBtn.click();
    return new ProfilePage();
  }
}
