# Testing the Web Layer

[Link](https://spring.io/guides/gs/testing-web/) to the tutorial.

## Steps taken

Setup basic project with folder structure and maven build script. Add very simple `HomeController` that sends back `"Hello World!"` on a GET-Request. Add `Application` class to make the project executable. Running `mvn spring-boot:run` now builds and runs the application and querying the url `localhost:8080` from the browser results in `"Hello World!"`. So far, so untested.

Write a small *Smoke Test*, this time using `AssertJ` instead of the standard junit asserts. This seems to be even more easily readable than the test cases I wrote for exercise three. Let's stick with that for the moment. Smoke test passes for the application itself, but a *real* test of the individual controllers are still missing.

Reading a bit ahead in the tutorial shows that starting and testing the whole application may not actually be the best possible way to test things. A better solution might be to only test the actual request layer, so that we don't start the *real* application at all but can call all of our controller code non the less. So let's go with that 🙂

Getting the test case for the `HomeController` to run is a bit tougher than I initially thought. The tutorial itself doesn't include that example in writing, so I'm piecing together stuff from the other available examples. Right now, it seems that the result of calling GET on `/` on the HomeController yields an empty result for the test - which is rather stunning as it worked perfectly when being called from the browser.

After fiddling with the *Debug Test* and getting down to the nitty gritty testing at the matcher level, it seems that the test **should** be successful. The mock response from the HomeController is not empty, but is housing a perfectly fine `"Hello World!"` inside. But running the test with `mvn test` still fails with an empty response. After that, I tried `mvn clean` and then `mvn test` again and low and behold, it worked just fine. All tests run and none fail. Sometimes, starting from a clean slate seems to be the best option 😉

**End of Tutorial**