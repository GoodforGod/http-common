package io.goodforgod.http.common;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 17.02.2022
 */
class MediaTypeCommonTests extends Assertions {

    @Test
    void equalsCaseInsensitive() {
        MediaType mediaType1 = MediaType.of("application/json");
        MediaType mediaType2 = MediaType.of("application/JSON");

        assertEquals(mediaType1, mediaType2);
    }

    @Test
    void equalsIgnoresParams() {
        MediaType mediaType1 = MediaType.of("application/json");
        MediaType mediaType2 = MediaType.of("application/json;charset=utf-8");

        assertEquals(mediaType1, mediaType2);
    }

    @Test
    void creatingMediaTypeWithParams() {
        final MediaType mediaType = MediaType.of("application/json;charset=utf-8");

        assertEquals("UTF-8", mediaType.parameters().get("charset"));
        assertTrue(mediaType.charset().isPresent());
        assertEquals(StandardCharsets.UTF_8, mediaType.charset().get());
    }
}
