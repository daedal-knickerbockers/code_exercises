package hello;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class HelloMessageTest {

    @Test
    public void shouldNotHaveNameWhenEmpty() {
        HelloMessage cut = new HelloMessage();
        assertThat(cut.name(), is(nullValue()));
    }

    @Test
    public void shouldHaveNameWhenConstructedWith() {
        String name = "foo";
        HelloMessage cut = new HelloMessage(name);
        assertThat(cut.name(), containsString(name));
    }

    @Test
    public void shouldHaveNewNameWhenRenamed() {
        String oldName = "foo";
        String newName = "bar";
        HelloMessage cut = new HelloMessage(oldName);
        cut.rename(newName);
        assertThat(cut.name(), is(not(containsString(oldName))));
        assertThat(cut.name(), containsString(newName));
    }

    @Test
    public void shouldBeWritableToJson() {
        String name = "foo";
        HelloMessage cut = new HelloMessage(name);

        String expectedJson = "{\"name\":\"" + name + "\"}";
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = "";
        try {
            actualJson = mapper.writeValueAsString(cut);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        assertThat(actualJson, containsString(expectedJson));
    }

    @Test
    public void shouldBeParsableFromJson() {
        String name = "foo";
        HelloMessage expectedCut = new HelloMessage(name);

        String json = "{\"name\":\"" + name + "\"}";
        ObjectMapper mapper = new ObjectMapper();
        HelloMessage actualCut = new HelloMessage();
        try {
            actualCut = mapper.readValue(json, HelloMessage.class);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        assertThat(actualCut.name(), containsString(expectedCut.name()));
    }
}