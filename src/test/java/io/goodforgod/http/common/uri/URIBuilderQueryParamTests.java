package io.goodforgod.http.common.uri;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Description.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 16.02.2022
 */
class URIBuilderQueryParamTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("/foo", Map.of("foo", "bar"), "/foo?foo=bar"),
                Arguments.of("/foo?existing=true", Map.of("foo", "bar"), "/foo?existing=true&foo=bar"),
                Arguments.of("/foo?foo=bar", Map.of("foo", "baz"), "/foo?foo=bar&foo=baz"),
                Arguments.of("/foo", Map.of("foo", "hello world"), "/foo?foo=hello+world"),
                Arguments.of("/foo", Map.of("foo", "bar"), "/foo?foo=bar"),
                Arguments.of("/foo", Map.of("foo", List.of("bar", "baz")), "/foo?foo=bar&foo=baz"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testQueryParamMethodForUri(String uri, Map<String, Object> params, String expected) {
        var builder = URIBuilder.of(uri);
        params.forEach(builder::param);
        assertEquals(expected, builder.toString());
    }
}
