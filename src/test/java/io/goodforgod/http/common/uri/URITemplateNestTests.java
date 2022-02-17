package io.goodforgod.http.common.uri;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 16.02.2022
 */
class URITemplateNestTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("/city", "country/{name}", Map.of("name", "Fred"), "/city/country/Fred"),
                Arguments.of("/city/", "country/{name}", Map.of("name", "Fred"), "/city/country/Fred"),
                Arguments.of("/city/", "/country/{name}", Map.of("name", "Fred"), "/city/country/Fred"),
                Arguments.of("/city", "/country/{name}", Map.of("name", "Fred"), "/city/country/Fred"),
                Arguments.of("/poetry", "/{?max}", Map.of("max", "10"), "/poetry?max=10"),
                Arguments.of("/poetry", "{?max}", Map.of("max", "10"), "/poetry?max=10"),
                Arguments.of("/", "/hello/{name}", Map.of("name", "Fred"), "/hello/Fred"),
                Arguments.of("", "/hello/{name}", Map.of("name", "Fred"), "/hello/Fred"),
                Arguments.of("/test/", "/hello/{name}", Map.of("name", "Fred"), "/test/hello/Fred"),
                Arguments.of("{var}", "{var2}", Map.of("var", "foo", "var2", "bar"), "foo/bar"),
                Arguments.of("/book{/id}", "/author{/authorId}", Map.of("id", "foo", "authorId", "bar"), "/book/foo/author/bar"),
                Arguments.of("{var}/", "{var2}", Map.of("var", "foo", "var2", "bar"), "foo/bar"),
                Arguments.of("{var}", "/{var2}", Map.of("var", "foo", "var2", "bar"), "foo/bar"),
                Arguments.of("{var}{?q}", "/{var2}", Map.of("var", "foo", "var2", "bar", "q", "test"), "foo/bar?q=test"),
                Arguments.of("{var}{?q}", "{var2}", Map.of("var", "foo", "var2", "bar", "q", "test"), "foo/bar?q=test"),
                Arguments.of("{var}{#hash}", "{var2}", Map.of("var", "foo", "var2", "bar", "hash", "test"), "foo/bar#test"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void nestTemplateTemplateWithPathNestedAndArguments(String template,
                                                        String nested,
                                                        Map<String, Object> arguments,
                                                        String expected) {
        final URITemplate uriTemplate = new URITemplate(template);
        assertEquals(expected, uriTemplate.nest(nested).expand(arguments));
    }
}
