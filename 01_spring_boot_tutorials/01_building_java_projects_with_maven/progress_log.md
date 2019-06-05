# Building Java Projects with Maven

[LINK](https://spring.io/guides/gs/maven/) to the tutorial.

## Steps taken

Changed JAVA_HOME and PATH variable to reflect newly installed Java 12 version.

```
java -version
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

Installed maven and added it to the PATH variable.

```
mvn -v
Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-04T21:00:29+02:00)
Maven home: C:\Program Files\apache-maven-3.6.1\bin\..
Java version: 12.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-12.0.1
Default locale: de_DE, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

Created package `hello` and simple classes `HelloWorld` and `Greeter`.

Setting up a basic build script. Compiling it with `mvn compile` and packaging it with `mvn package`.

Added dependency `org.joda.time.LocalTime` to class `HelloWorld`. Now running `mvn compile` fails because of missing dependencies in the build script.

Added `dependencies` node to build script and ran `mvn compile` and `mvn package` again. Missing dependencies get resolved and .jar file created again.

Added dependency `junit` to the build script and created test class for `Greeter`. Ran `mvn test` and got confirmation that the test has run successfully.

**End of Tutorial**