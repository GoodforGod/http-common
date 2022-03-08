# HTTP Common

![GraalVM Ready](https://img.shields.io/badge/GraalVM-Ready-orange?style=plastic)
![Java CI](https://github.com/GoodforGod/http-common/workflows/Java%20CI/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=alert_status)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)

Small library with HTTP common components like HttpStatus, MediaType, URIBuilder, etc.

Have no dependencies.

## Dependency :rocket:

Java 17+

[**Gradle**](https://mvnrepository.com/artifact/io.goodforgod/http-common)
```groovy
implementation "io.goodforgod:http-common:0.9.0"
```

[**Maven**](https://mvnrepository.com/artifact/io.goodforgod/http-common)
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>http-common</artifactId>
    <version>0.9.0</version>
</dependency>
```

## HttpStatus

Library provides *HttpStatus* with code and reasons, all mostly used HttpStatus are present there for easier use.

```java
HttpStatus status = HttpStatus.ACCEPTED;

int code = status.code();
String reason = status.reason();

HttpStatus statusAccepted = HttpStatus.valueOf(201);
```

## MediaType

Library provides *MediaType* class for correctly parsing and interpreting MediaType / ContentTypes.

There are plenty of widely used and preconfigured MediaTypes.

```java
MediaType jsonType = MediaType.APPLICATION_JSON_UTF_8_TYPE;

MediaType type1 = MediaType.of("application/json");

Optional<MediaType> type2 = MediaType.ofExtension("json");
```

## URIBuilder

Library provide URIBuilder for easier and safer way of building URI.

```java
URIBuilder.of("https://api.etherscan.io").path("/api")
                .param("module", "block")
                .param("action", "getblockreward")
                .build()
```

## License

This project licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details