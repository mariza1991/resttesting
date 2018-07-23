package org.freesound.integration;

import com.codeborne.selenide.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class FirstIntegrationTest {

    private static final String URL = "https://freesound.org";

    private static final String API_PATH = "/apiv2/search/text/";

    private static final String API_KEY = "rcBTauOX7I4qJbioXWtd04GWp1f9lLq9RFd2rXpJ";

    private static String query = "dog";

    private static String filename;
    private static String username;
    private static String soundId;

    @BeforeTest
    public void before() throws UnirestException
    {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.baseUrl = URL;
        System.setProperty("selenide.baseUrl", URL);

        String json = Unirest.get(URL + API_PATH).queryString("token", API_KEY)
                .queryString("query", query)
                .asString()
                .getBody();

        filename = JsonPath.read(json, "$.results[0].name");
        username = JsonPath.read(json, "$.results[0].username");
    }

    @Test
    public void testUi()
    {
        open("/search/?q=" + query);
        $$("div.sound_filename").get(0).should(text(filename));
    }
}
