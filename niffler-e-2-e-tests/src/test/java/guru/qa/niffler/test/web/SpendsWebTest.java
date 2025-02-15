package guru.qa.niffler.test.web;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.jupiter.annotation.ApiLogin;
import guru.qa.niffler.jupiter.annotation.GenerateSpend;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class SpendsWebTest extends BaseWebTest {

    @GenerateSpend(
        username = "dima",
        description = "QA GURU ADVANCED VOL 2",
        currency = CurrencyValues.RUB,
        amount = 52000.00,
        category = "Обучение"
    )
    @ApiLogin(username = "dima", password = "12345")
    @AllureId("101")
    @Test
    void spendShouldBeDeletedByActionInTable(UserJson user, SpendJson spend) {
        Selenide.open(CFG.getFrontUrl() + "/main");

        $(".spendings-table tbody").$$("tr")
            .find(text(spend.getDescription()))
            .$$("td").first()
            .scrollTo()
            .click();

        $$(".button_type_small").find(text("Delete selected"))
            .click();

        $(".spendings-table tbody")
            .$$("tr")
            .shouldHave(CollectionCondition.size(0));
    }

    @GenerateSpend(
        username = "dima",
        description = "QA GURU ADVANCED VOL 2",
        currency = CurrencyValues.RUB,
        amount = 52000.00,
        category = "Обучение"
    )
    @ApiLogin(username = "dima", password = "12345")
    @AllureId("101")
    @Test
    void spendInTableShouldBeEqualToGiven(SpendJson spend) {
        Selenide.open(CFG.getFrontUrl() + "/main");

        $(".spendings-table tbody")
            .$$("tr")
//            .shouldHave(spends(spend));
            .shouldHave(CollectionCondition.size(1));
    }


    void checkThatSpendEqual(SelenideElement spendRow, SpendJson spend) {
        Assertions.assertEquals(spend.getSpendDate().toString(),
            spendRow.$$("td").get(1).getText());
        Assertions.assertEquals(spend.getAmount().toString(), spendRow.$$("td").get(2).getText());
        Assertions.assertEquals(spend.getCurrency().toString(), spendRow.$$("td").get(3).getText());
        Assertions.assertEquals(spend.getCategory().toString(), spendRow.$$("td").get(4).getText());
        Assertions.assertEquals(spend.getDescription().toString(),
            spendRow.$$("td").get(5).getText());
    }
}
