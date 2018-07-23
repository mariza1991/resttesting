package net.dictionary;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class RestAssuredTest {

    //https://translate.yandex.ru/

    private static final String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private static final String API_KEY = "trnsl.1.1.20180723T191309Z.3e81a6e3b9e691a5.df28d30ac4035bb899fed11c629d34c23da40f88";

    @BeforeTest
    public void before(){
        RestAssured.baseURI = "https://translate.yandex.net/api/v1.5/tr.json";
    }

    @Test
    public void dictionaryTest() {
        RestAssured.useRelaxedHTTPSValidation(); //SSL
        String additionalPath = getPathFormated(API_KEY, "Привет, мир!", "ru-en");

        given()
                .header("User-Agent", "Mozilla...")
                .header("JWT", "jwt_token")

                .when()
            //    .get(API_URL + additionalPath)
                .get(EndpointUrl.TRANSLATE.addPath(additionalPath))
                .then()
                .statusCode(200)
                //.extract().body().jsonPath().getString("$");
                .body("text", hasItem("Hello world!"))
                .body("lang", equalTo("ru-en"))
                .body("code", equalTo(200));

    }
/**
    @DataProvider
    public Object[][] dictionary(){
        return new Object[][] {
                {"Привет","Hi"},
                {"Клиент","The client"},
                {"Лекция","Lecture"}
        };

    private static String authorizeMe(String login, String password){
        Reques request = new Request();
        OR return UniRest() -> .header("JWT", authorizeMe("login", "password"))
    }*/


    protected static String getPathFormated(String key, String text, String languageFormat) {
        return String.format("?key=%s&text=%s&lang=%s", key, text, languageFormat);
    }

    public static void main(String [] args) throws UnirestException {
        String response = Unirest.get(API_URL + getPathFormated(API_KEY, "Привет", "ru-en")).asString().getBody();
        System.out.println(response);
    }
}
