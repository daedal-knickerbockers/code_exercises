package hello;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class HelloMessage {

    private String name;

    public HelloMessage() {

    }

    public HelloMessage(String name) {
        this.name = name;
    }

    @JsonGetter
    public String name() {
        return this.name;
    }

    public void rename(String name) {
        this.name = name;
    }
}