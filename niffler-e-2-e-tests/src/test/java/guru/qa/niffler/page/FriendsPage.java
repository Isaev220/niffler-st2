package guru.qa.niffler.page;

import guru.qa.niffler.page.component.Header;

public class FriendsPage extends BasePage<FriendsPage> {

  private final Header header = new Header();

  public Header getHeader() {
    return header;
  }

  @Override
  public FriendsPage checkThatPageLoaded() {
    header.checkThatComponentDisplayed();
    return this;
  }
}
