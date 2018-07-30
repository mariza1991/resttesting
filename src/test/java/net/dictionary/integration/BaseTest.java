package net.dictionary.integration;

import com.codeborne.selenide.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private static final String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private static final String API_KEY = "trnsl.1.1.20180722T143359Z.9720535089b55317.dddebaf0083a8a7df6d7636f612b6e72cde21796";
    public static String input = "world";
    public static String expectedTranslate;

    @BeforeTest
    public void before() throws UnirestException {

        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");

        String json = Unirest.get(API_URL).queryString("key", API_KEY)
                .queryString("lang", "en-ru")
                .queryString("text", input)
                .asString()
                .getBody();

        expectedTranslate = JsonPath.read(json, "$.text[0]");
    }
}
