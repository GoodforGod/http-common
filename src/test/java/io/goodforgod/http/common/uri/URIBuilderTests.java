package io.goodforgod.http.common.uri;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Description.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 16.02.2022
 */
class URIBuilderTests extends Assertions {

    @Test
    void testUriBuilderToString() {
        var builder = URIBuilder.of("");
        builder.path("/foo");
        assertEquals("/foo", builder.toString());

        builder.path("/bar/").path("/baz");
        assertEquals("/foo/bar/baz", builder.toString());

        builder.host("myhost");
        assertEquals("//myhost/foo/bar/baz", builder.toString());

        builder.port(9090);
        assertEquals("//myhost:9090/foo/bar/baz", builder.toString());

        builder.scheme("https");
        assertEquals("https://myhost:9090/foo/bar/baz", builder.toString());

        builder.param("offset", 10);
        assertEquals("https://myhost:9090/foo/bar/baz?offset=10", builder.toString());
    }

    @Test
    void testParameterNamesWithSpecialCharacters() {
        URIBuilder builder = URIBuilder.of("myurl")
                .param("$top", "10")
                .param("$filter", "xyz");

        String uri = builder.build().toString();

        assertEquals("myurl?%24top=10&%24filter=xyz", uri);
    }

    @Test
    void testQueryParamOrder() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("t_param", "t_value");
        params.put("s_param", "s_value");
        params.put("a_param", "a_value");

        URIBuilder uriBuilder = URIBuilder.of("/api").path("v1").path("secretendpoint");
        for (String paramKey : params.keySet()) {
            uriBuilder = uriBuilder.param(paramKey, params.get(paramKey));
        }

        assertEquals("/api/v1/secretendpoint?t_param=t_value&s_param=s_value&a_param=a_value", uriBuilder.build().toString());
    }
}
