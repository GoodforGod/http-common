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
class MediaTypeOfFilenameTests extends Assertions {

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("doc.xml", "application/xml"),
                Arguments.of("son-5151-g1t1gg.mp3", "audio/mpeg"),
                Arguments.of("path/top/my/simple/payload.json", "application/json"),
                Arguments.of("./mydoc.docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void mediaTypeForFilename(String filename,
                              String expected) {
        final Optional<MediaType> type = MediaType.ofFilename(filename);

        assertTrue(type.isPresent());
        assertEquals(expected, type.get().name());
    }
}
