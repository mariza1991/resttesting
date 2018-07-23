package net.dictionary.homework;

import io.restassured.RestAssured;
import net.dictionary.homework.steps.LookupSteps;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class LookupTest extends BaseTest {

    @Test
    public void lookupTest() {
        RestAssured.useRelaxedHTTPSValidation();
        LookupSteps lookup = new LookupSteps();
        String myPath = lookup.getPathFormated(lookup.API_KEY, "en-ru", "mother");

        given()
                .when()
                .get(lookup.API_URL + myPath)
                .then()
                .statusCode(200)
                .body("def[0].text", equalTo("mother"))
                .body("def[0].tr.text", hasItem("мать"))
                .body("def[1].pos", equalTo("adjective"));
    }
}
