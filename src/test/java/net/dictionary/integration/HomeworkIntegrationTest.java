package net.dictionary.integration;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeworkIntegrationTest extends BaseTest {

    @Test
    public void integrationTest() {
        open("https://translate.yandex.ru/");
        $(byId("fakeArea")).setValue(input).pressEnter();
        $(byId("translation")).shouldHave(text(expectedTranslate));
    }
}
