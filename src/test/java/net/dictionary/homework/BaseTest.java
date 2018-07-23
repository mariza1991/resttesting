package net.dictionary.homework;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void before() {
        RestAssured.baseURI = "https://dictionary.yandex.net/api/v1/dicservice.json";
    }
}
