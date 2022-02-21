package io.goodforgod.http.common;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 17.02.2022
 */
class MediaTypeOfExtensionTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("xml", "application/xml"),
                Arguments.of("mp3", "audio/mpeg"),
                Arguments.of("json", "application/json"),
                Arguments.of("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
                Arguments.of("pdf", "application/pdf"),
                Arguments.of("html", "text/html"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void mediaTypeForExtension(String extension,
                               String expected) {
        final Optional<MediaType> type = MediaType.ofExtension(extension);

        assertTrue(type.isPresent());
        assertEquals(expected, type.get().name());
    }
}
