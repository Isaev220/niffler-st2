package niffler.test;


import com.codeborne.selenide.Configuration;
import niffler.jupiter.annotation.WebTest;

@WebTest
public abstract class BaseWebTest {
  static final String USER = "anton";

  static final String PASSWORD = "1234";
  static {
    Configuration.browserSize = "1920x1080";
  }

}
