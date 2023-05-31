package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import guru.qa.niffler.page.component.Header;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage<MainPage>{
  public static final String historyText = "History of spendings";

  private final Header header = new Header();

  public Header getHeader() {
    return header;
  }

  @Override
  public MainPage checkThatPageLoaded() {
    $(byText(historyText)).shouldBe(Condition.visible);
    return this;
  }
}
