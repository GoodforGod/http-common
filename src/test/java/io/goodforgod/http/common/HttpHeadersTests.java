package io.goodforgod.http.common;

import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 13.03.2022
 */
class HttpHeadersTests extends Assertions {

    @Test
    void emptyNotNull() {
        final HttpHeaders headers = HttpHeaders.empty();
        assertNotNull(headers);
        assertTrue(headers.getMap().isEmpty());
    }

    @Test
    void ofNotNull() {
        final HttpHeaders headers = HttpHeaders.of();
        assertNotNull(headers);
        assertTrue(headers.getMap().isEmpty());
    }

    @Test
    void emptyOfMap() {
        final HttpHeaders headers = HttpHeaders.ofMap(Collections.emptyMap());
        assertNotNull(headers);
        assertTrue(headers.getMap().isEmpty());
    }

    @Test
    void emptyOfMultiMap() {
        final HttpHeaders headers = HttpHeaders.ofMultiMap(Collections.emptyMap());
        assertNotNull(headers);
        assertTrue(headers.getMap().isEmpty());
    }

    @Test
    void ofMultiMap() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put("h1", List.of("v1"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        assertNotNull(headers);
        assertFalse(headers.getMap().isEmpty());
        assertEquals(h, headers.getMultiMap());

        final Map<String, String> map = headers.getMap();
        final String h1 = map.get("h1");
        assertEquals("v1", h1);
    }

    @Test
    void ofMap() {
        // given
        final Map<String, String> h = new HashMap<>();
        h.put("h1", "v1");

        // when
        final HttpHeaders headers = HttpHeaders.ofMap(h);

        // then
        assertNotNull(headers);
        assertFalse(headers.getMap().isEmpty());
        assertEquals(h, headers.getMap());

        final Map<String, List<String>> multiMap = headers.getMultiMap();
        final List<String> h1 = multiMap.get("h1");
        assertNotNull(h1);
        assertEquals(1, h1.size());
        assertEquals("v1", h1.get(0));
    }

    @Test
    void ofArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> HttpHeaders.of("h1", "v1", "v1"));
    }

    @Test
    void ofArray() {
        // given
        final Map<String, String> h = new HashMap<>();
        h.put("h1", "v1");

        // when
        final HttpHeaders headers = HttpHeaders.of("h1", "v1");

        // then
        assertNotNull(headers);
        assertFalse(headers.getMap().isEmpty());
        assertEquals(h, headers.getMap());

        final Map<String, List<String>> multiMap = headers.getMultiMap();
        final List<String> h1 = multiMap.get("h1");
        assertNotNull(h1);
        assertEquals(1, h1.size());
        assertEquals("v1", h1.get(0));

        final Map<String, String> map = headers.getMap();
        assertEquals("v1", map.get("h1"));
    }

    @Test
    void findAll() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put("h1", List.of("v1", "v2"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        final List<String> all = headers.findAll("h1");
        assertEquals(2, all.size());
        assertTrue(all.contains("v1"));
        assertTrue(all.contains("v2"));
    }

    @Test
    void findAllEmpty() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put("h1", List.of("v1", "v2"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        final List<String> all = headers.findAll("h2");
        assertTrue(all.isEmpty());
    }

    @Test
    void findFirst() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put("h1", List.of("v1", "v2"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        final Optional<String> first = headers.findFirst("h1");
        assertTrue(first.isPresent());
        assertEquals("v1", first.get());
    }

    @Test
    void findFirstEmpty() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put("h1", List.of("v1", "v2"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        final Optional<String> first = headers.findFirst("h2");
        assertFalse(first.isPresent());
    }

    @Test
    void specialHeadersPresent() {
        // given
        final Map<String, List<String>> h = new HashMap<>();
        h.put(HttpHeaders.CONNECTION, List.of("keep-alive"));
        h.put(HttpHeaders.ORIGIN, List.of("origin"));
        h.put(HttpHeaders.AUTHORIZATION, List.of("auth"));
        h.put(HttpHeaders.CONTENT_LENGTH, List.of("72"));
        h.put(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON));
        h.put(HttpHeaders.ACCEPT, List.of("text/html, application/xhtml+xml, application/xml;q=0.9, image/webp", "*/*"));

        // when
        final HttpHeaders headers = HttpHeaders.ofMultiMap(h);

        // then
        assertTrue(headers.isKeepAlive());
        final Optional<String> origin = headers.origin();
        assertTrue(origin.isPresent());
        assertEquals("origin", origin.get());

        final Optional<String> authorization = headers.authorization();
        assertTrue(authorization.isPresent());
        assertEquals("auth", authorization.get());

        final Optional<Long> contentLength = headers.contentLength();
        assertTrue(contentLength.isPresent());
        assertEquals(72, contentLength.get());

        final Optional<MediaType> mediaType = headers.contentType();
        assertTrue(mediaType.isPresent());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, mediaType.get());

        final List<MediaType> accept = headers.accept();
        assertEquals(5, accept.size());
    }
}
