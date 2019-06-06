package hello;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class GreetingTest {

    @Test
    public void shouldNotHaveContentWhenEmpty() {
        Greeting cut = new Greeting();
        assertThat(cut.content(), is(nullValue()));
    }

    @Test
    public void shouldHaveContentWhenConstructedWith() {
        String content = "foo";
        Greeting cut = new Greeting(content);
        assertThat(cut.content(), containsString(content));
    }

    @Test
    public void shouldBeWritableToJson() {
        String content = "foo";
        Greeting cut = new Greeting(content);

        String expectedJson = "{\"content\":\"" + content + "\"}";
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = "";
        try {
            actualJson = mapper.writeValueAsString(cut);
        } catch (JsonProcessingException jpe) {
            fail(jpe.getMessage());
        }
        assertThat(actualJson, containsString(expectedJson));
    }
}