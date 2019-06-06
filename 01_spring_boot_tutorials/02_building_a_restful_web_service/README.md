# Building a RESTful Web Service

[LINK](https://spring.io/guides/gs/rest-service/) to the tutorial.

## Steps taken

Create folder structure for project. Create minimal build script that includes dependencies to the spring framework.

Add `Greeting` as a model to be requestable by the web service. Add `GreetingController` to handle HTTP-GET calls to endpoint `greeting/` and return a new `Greeting` object with the inclusion of a given name parameter.

Add `Application` class for simple use of application configuration by the spring framework.

Run `mvn spring-boot:run` to build and run the application, but get an error because the target release `1.12` is not valid. Have to find out how to specify how to build with java 12.

```xml
<...>
<properties>
    <java.version>1.12</java.version>
</properties>
<..>
```

Turns out it's quite easy. Java 12 should be configured as `12`, not `1.12`. 

```xml
<...>
<properties>
    <java.version>12</java.version>
</properties>
<..>
```

However, another error get's thrown. It seems like specifying both the `@RequestMapping` for the actual path and the http-method needs to be done differently than my current way. Let's dive into the spring framework documentation.

```java
@RequestMapping("/greeting")
@RequestMapping(method=GET)
public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
                        String.format(template, name));
}
```

Okay, so the more elegant way to handle this is using a `@GetMapper` at the method level and putting the path mapping with `@RequestMapping("/greeting")` at the class level. However, this introduced new problems.

```java
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
```
Now the code won't compile at all, saying that it cannot find the symbol `GetMapping`. Well, sadly it turns out that I have either been out of the Java game for too long or should use a *real* IDE instead of VisualStudio Code... I just forgot to import the necessary package at the top of my class... Now it works 🙂

Running the project with `mvn spring-boot:run` now actually spawns a local webserver on port 8080. When querying on path `localhost:8080/greeting` I am not greeted by "Hello World!", but instead with an error message.

```
WARN 16568 --- [nio-8080-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotWritableException: No converter found for return value of type: class hello.Greeting]
```

It seems like the *automatic JSON conversion* that has been promised by the tutorial is not working - or I did mess up along the way again 😉 Let's investigate.

Well, bad things happen if you think you are clever... I tried incorporating some Clean Code principles while writing the code. Instead of using extensive getters and setter, use descriptive function names and just drop the unnecessary get part. If you look at the [Oxford Dictionary entry](https://en.oxforddictionaries.com/definition/get) for the word *get*, you'll notice that the use of that word is hardly unambiguous. So instead of writing methods with names like `getId` and `getContent`, I tried to do it like that.

```java
package hello;

public class Greeting {
    
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long id() {
        return id;
    }

    public String content() {
        return content;
    }
}
```

Now the methods are clear and concise. However, [Jackson](https://github.com/FasterXML/jackson), the package used for the *automatic JSON conversion* of objects is really not happy about this. When writing the methods in the *classic* style, the web service works without any problems. But I don't like it very much. I'll do some digging.

After a few minutes of searching the docs, this turned out to work quite nicely 😁 Now the code is *clean* and I get the expected result when querying the web service.

```java
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
```

Calling `http://localhost:8080/greeting?name=daedal.knickerbockers` in the browser now hands me the following output. Seems to work just fine.

```json
{"id":3,"content":"Hello, daedal.knickerbockers!"}
```

**End of the tutorial**