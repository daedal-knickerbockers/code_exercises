package hello;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Greeting {
    
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonGetter
    public long id() {
        return id;
    }

    @JsonGetter
    public String content() {
        return content;
    }
}