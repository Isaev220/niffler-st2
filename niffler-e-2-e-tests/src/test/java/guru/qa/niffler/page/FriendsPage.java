package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import guru.qa.niffler.page.component.Header;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class FriendsPage extends BasePage<FriendsPage> {
  public static final String notFriendsText = "There are no friends yet!";

  private final Header header = new Header();

  public Header getHeader() {
    return header;
  }

  @Override
  public FriendsPage checkThatPageLoaded() {
    $(byText(notFriendsText)).shouldBe(Condition.visible);
    return this;
  }
}
