# HTTP Common

![GraalVM Ready](https://img.shields.io/badge/GraalVM-Ready-orange?style=plastic)
![Java CI](https://github.com/GoodforGod/http-common/workflows/Java%20CI/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=alert_status)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_http-common&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_http-common)

Small library with HTTP common components like HttpStatus, MediaType, URIBuilder, etc.

Have no dependencies.

## Dependency :rocket:

Java 17+ support.

[**Gradle**](https://mvnrepository.com/artifact/io.goodforgod/http-common)
```groovy
implementation "io.goodforgod:http-common:1.0.0-SNAPSHOT"
```

[**Maven**](https://mvnrepository.com/artifact/io.goodforgod/http-common)
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>http-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
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

## HttpMethod

Describes *HttpMethod* common methods of HTTP protocol.

```java
final HttpMethod method = HttpMethod.of("get");
assertEquarls(HttpMethod.GET, method);
```

## MediaType

Library provides *MediaType* class for correctly parsing and interpreting MediaType / ContentTypes.

There are plenty of widely used and preconfigured MediaTypes.

```java
MediaType jsonMediaTypeUtf8 = MediaType.APPLICATION_JSON_UTF_8_TYPE;

MediaType mediaType = MediaType.of("application/json");

Optional<MediaType> jsonMediaType = MediaType.ofExtension("json");
```

## URIBuilder

Library provide URIBuilder for easier and safer way of building URI.

```java
URIBuilder.of("https://api.etherscan.io").path("/api")
                .param("module", "block")
                .param("action", "getblockreward")
                .build()
```

## HttpHeaders

Describes HTTP headers and can be instantiated via different builders available.

```java
final Map<String, List<String>> headerMap = new HashMap<>();
headerMap.put(HttpHeaders.CONNECTION, List.of("keep-alive"));
headerMap.put(HttpHeaders.CONTENT_LENGTH, List.of("72"));
headerMap.put(HttpHeaders.CONTENT_TYPE, List.of("application/json"));

final HttpHeaders headers = HttpHeaders.ofMultiMap(headerMap);

final Optional<Long> contentLength = headers.contentLength();
final Optional<MediaType> mediaType = headers.contentType();
```

## FormattedException

Exception that allow to format messages like SLF4J logger and other similar.

```java
throw new FormattedException("Failed for {} with code {}", "Bob", 200);
```

Resulted exception message:
```text
Failed for Bob with code 200
```

## License

This project licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details