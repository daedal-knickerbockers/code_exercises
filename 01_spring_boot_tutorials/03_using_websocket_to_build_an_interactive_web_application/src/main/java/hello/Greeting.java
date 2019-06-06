package hello;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Greeting {

    private String content;

    public Greeting() {

    }

    public Greeting(String content) {
        this.content = content;
    }

    @JsonGetter
    public String content() {
        return this.content;
    }

}