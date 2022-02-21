package io.goodforgod.http.common;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 17.02.2022
 */
class MediaTypeOfTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("application/hal+xml;q=1.1", null, "application/hal+xml", "xml", Map.of("q", "1.1"), 1.1, "hal+xml",
                        "application"),
                Arguments.of("application/hal+xml;q=1.1", "foo", "application/hal+xml", "foo", Map.of("q", "1.1"), 1.1, "hal+xml",
                        "application"),
                Arguments.of("application/hal+json", null, "application/hal+json", "json", Map.of(), 1.0, "hal+json",
                        "application"),
                Arguments.of("application/hal+xml;foo=bar", null, "application/hal+xml", "xml", Map.of("foo", "bar"), 1.0,
                        "hal+xml", "application"),
                Arguments.of("application/json", null, "application/json", "json", Map.of(), 1.0, "json", "application"),
                Arguments.of("text/html;charset=utf-8", null, "text/html", "html", Map.of("charset", "UTF-8"), 1.0, "html",
                        "text"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testQueryParamMethodForUri(String mediaType,
                                    String extension,
                                    String expectedName,
                                    String expectedExtension,
                                    Map<String, String> expectedParams,
                                    double expectedQuality,
                                    String expectedSubtype,
                                    String expectedType) {
        final MediaType type = MediaType.of(mediaType, extension);

        assertEquals(expectedName, type.name());
        assertEquals(expectedExtension, type.extension());
        assertEquals(expectedParams, type.parameters());
        assertEquals(expectedQuality, type.qualityAsNumber().doubleValue());
        assertEquals(expectedType, type.type());
        assertEquals(expectedSubtype, type.subtype());
    }
}
