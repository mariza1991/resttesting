package net.dictionary;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {

    //TODO распарсить джейсон
    @Test
    public void test(){
        DictionaryClient client = new DictionaryClient();
        String translation = client.sendGet("Привет", "ru-en");
        assertThat(translation, equalTo("Hi"));
    }
}
