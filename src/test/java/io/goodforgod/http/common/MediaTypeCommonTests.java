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
        final MediaType mediaType1 = MediaType.of("application/json");
        final MediaType mediaType2 = MediaType.of("application/JSON");

        assertEquals(mediaType1, mediaType2);

        assertEquals(MediaType.APPLICATION_JSON_TYPE, mediaType1);
        assertEquals(MediaType.APPLICATION_JSON_TYPE.type(), mediaType1.type());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.extension(), mediaType1.extension());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.length(), mediaType1.length());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.toString(), mediaType1.toString());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.name(), mediaType1.name());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.parameters(), mediaType1.parameters());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.quality(), mediaType1.quality());
        assertEquals(MediaType.APPLICATION_JSON_TYPE.qualityAsNumber(), mediaType1.qualityAsNumber());
    }

    @Test
    void equalsIgnoresParams() {
        MediaType mediaType1 = MediaType.of("application/x-yaml");
        MediaType mediaType2 = MediaType.of("application/x-yaml;charset=utf-8");

        assertEquals(mediaType1, mediaType2);
    }

    @Test
    void creatingMediaTypeWithParams() {
        final MediaType mediaType = MediaType.of("text/plain;charset=utf-8");

        assertEquals("UTF-8", mediaType.parameters().get("charset"));
        assertTrue(mediaType.charset().isPresent());
        assertEquals(StandardCharsets.UTF_8, mediaType.charset().get());
    }
}
