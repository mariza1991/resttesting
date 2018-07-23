package net.dictionary.homework.steps;

public class GetLangSteps extends BaseSteps {

    public static final String API_URL = "/getLangs";

    public String getPathFormated(String key) {
        return String.format("?key=%s", key);
    }
}
