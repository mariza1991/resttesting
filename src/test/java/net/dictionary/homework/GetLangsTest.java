package net.dictionary.homework;

import net.dictionary.homework.steps.GetLangSteps;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.not;

public class GetLangsTest extends BaseTest {

    @Test
    public void getLangTest() {
        GetLangSteps getLang = new GetLangSteps();
        String myPath = getLang.getPathFormated(getLang.API_KEY);

        given()
                .when()
                .get(getLang.API_URL + myPath)
                .then()
                .statusCode(200)
                .body("$",hasItem("ru-ru"))
                .body(".",not(hasItem("it-!")));
    }
}
