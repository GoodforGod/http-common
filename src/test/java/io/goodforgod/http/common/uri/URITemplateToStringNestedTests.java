package io.goodforgod.http.common.uri;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 16.02.2022
 */
class URITemplateToStringNestedTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("/city", "/", "/city"),
                Arguments.of("/city", "", "/city"),
                Arguments.of("/city", "country/{name}", "/city/country/{name}"),
                Arguments.of("/city/", "country/{name}", "/city/country/{name}"),
                Arguments.of("/city/", "/country/{name}", "/city/country/{name}"),
                Arguments.of("/city", "/country/{name}", "/city/country/{name}"),
                Arguments.of("/foo", "/find?{x,empty}", "/foo/find?{x,empty}"),
                Arguments.of("/foo", "/find{?x,empty}", "/foo/find{?x,empty}"),
                Arguments.of("/foo", "{/list*,path:4}", "/foo{/list*,path:4}"),
                Arguments.of("/person", "/{fred}", "/person/{fred}"),
                Arguments.of("/books", "{/id}", "/books{/id}"),
                Arguments.of("/books/", "{/id}", "/books{/id}"),
                Arguments.of("", "/authors{/authorId}", "/authors{/authorId}"),
                Arguments.of("/", "/regex/{color:^blue,orange$}", "/regex/{color:^blue,orange$}"),
                Arguments.of("/poetry", "/{?max}", "/poetry{?max}"),
                Arguments.of("/poetry", "{?max}", "/poetry{?max}"),
                Arguments.of("/", "/hello/{name}", "/hello/{name}"),
                Arguments.of("", "/hello/{name}", "/hello/{name}"),
                Arguments.of("/test/", "/hello/{name}", "/test/hello/{name}"),
                Arguments.of("{var}", "{var2}", "{var}/{var2}"),
                Arguments.of("/book{/id}", "/author{/authorId}", "/book{/id}/author{/authorId}"),
                Arguments.of("{var}/", "{var2}", "{var}/{var2}"),
                Arguments.of("{var}", "/{var2}", "{var}/{var2}"),
                Arguments.of("{var}{?q}", "/{var2}", "{var}/{var2}{?q}"),
                Arguments.of("{var}{#hash}", "{var2}", "{var}/{var2}{#hash}"),
                Arguments.of("/foo", "/find{?year*}", "/foo/find{?year*}"),
                Arguments.of("/foo", "/find{?keys1*}{?keys2*}", "/foo/find{?keys1*}{?keys2*}"),
                Arguments.of("/foo", "/find{?keys1*}{&keys2*}", "/foo/find{?keys1*}{&keys2*}"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void nestTemplateToStringWithPathNested(String template, String nested, String expected) {
        final URITemplate uriTemplate = new URITemplate(template);
        assertEquals(expected, uriTemplate.nest(nested).toString());
    }
}
