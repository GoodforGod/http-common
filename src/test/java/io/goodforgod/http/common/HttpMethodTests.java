package io.goodforgod.http.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 18.02.2022
 */
class HttpMethodTests extends Assertions {

    @Test
    void methodOfLowerCase() {
        final HttpMethod method = HttpMethod.of("get");
        assertEquals(HttpMethod.GET, method);
        assertEquals(HttpMethod.GET.length(), method.length());
        assertEquals(HttpMethod.GET.toString(), method.toString());
        assertEquals(HttpMethod.GET.allowRequestBody(), method.allowRequestBody());
    }

    @Test
    void methodOfUpperCase() {
        final HttpMethod method = HttpMethod.of("POST");
        assertEquals(HttpMethod.POST, method);
        assertEquals(HttpMethod.POST.length(), method.length());
        assertEquals(HttpMethod.POST.toString(), method.toString());
        assertEquals(HttpMethod.POST.allowRequestBody(), method.allowRequestBody());
    }

    @Test
    void methodOfCaseInsensitive() {
        final HttpMethod method = HttpMethod.of("PuT");
        assertEquals(HttpMethod.PUT, method);
        assertEquals(HttpMethod.PUT.length(), method.length());
        assertEquals(HttpMethod.PUT.toString(), method.toString());
        assertEquals(HttpMethod.PUT.allowRequestBody(), method.allowRequestBody());
    }

    @Test
    void methodOfUnknown() {
        final HttpMethod method = HttpMethod.of("PuTtto");
        assertEquals(HttpMethod.CUSTOM, method);
    }
}
