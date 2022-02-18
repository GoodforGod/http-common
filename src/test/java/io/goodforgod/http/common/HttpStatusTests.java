package io.goodforgod.http.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 17.02.2022
 */
class HttpStatusTests extends Assertions {

    @Test
    void statusOfSuccess() {
        final HttpStatus status = HttpStatus.valueOf(500);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, status);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), status.name());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.reason(), status.reason());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), status.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.length(), status.length());
    }
}
