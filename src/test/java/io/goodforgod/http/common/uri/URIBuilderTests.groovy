package io.goodforgod.http.common.uri


import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Anton Kurako (GoodforGod)
 * @since 22.08.2021
 */
class URIBuilderTests extends Specification {

    void "test query param order"() {
        Map<String, String> params = new LinkedHashMap<>()
        params.put("t_param", "t_value")
        params.put("s_param", "s_value")
        params.put("a_param", "a_value")

        URIBuilder uriBuilder = URIBuilder.of("/api").path("v1").path("secretendpoint");
        for (String paramKey : params.keySet()) {
            System.out.println(paramKey)
            uriBuilder = uriBuilder.queryParam(paramKey, params.get(paramKey));
        }

        expect:
        uriBuilder.build().toString() == "/api/v1/secretendpoint?t_param=t_value&s_param=s_value&a_param=a_value"
    }

    void "test uri builder toString()"() {
        given:
        def builder = URIBuilder.of("")
        when:
        builder.path("/foo")
        then:
        builder.toString() == '/foo'
        when:
        builder.path("/bar/")
                .path('/baz')
        then:
        builder.toString() == '/foo/bar/baz'
        when:
        builder.host("myhost")
        then:
        builder.toString() == '//myhost/foo/bar/baz'
        when:
        builder.port(9090)
        then:
        builder.toString() == '//myhost:9090/foo/bar/baz'
        when:
        builder.scheme("https")
        then:
        builder.toString() == 'https://myhost:9090/foo/bar/baz'
        when:
        builder.queryParam("offset", 10)
        then:
        builder.toString() == 'https://myhost:9090/foo/bar/baz?offset=10'
    }

    @Unroll
    void "test queryParam method for uri #uri"() {
        given:
        def builder = URIBuilder.of(uri)
        for (p in params) {
            builder.queryParam(p.key, p.value)
        }

        expect:
        builder.toString() == expected
        where:
        uri                  | params                  | expected
        '/foo'               | ['foo': 'bar']          | '/foo?foo=bar'
        '/foo?existing=true' | ['foo': 'bar']          | '/foo?existing=true&foo=bar'
        '/foo?foo=bar'       | ['foo': 'baz']          | '/foo?foo=bar&foo=baz'
        '/foo'               | ['foo': 'hello world']  | '/foo?foo=hello+world'
        '/foo'               | ['foo': ['bar', 'baz']] | '/foo?foo=bar&foo=baz'
        '/foo'               | ['foo': ['bar', 'baz']] | '/foo?foo=bar&foo=baz'
    }

    void "test parameter names with special characters"() {
        given:
        URIBuilder builder = URIBuilder.of("myurl")
                .queryParam("\$top", "10")
                .queryParam("\$filter", "xyz")
        String uri = builder.build().toString()

        expect:
        uri == 'myurl?%24top=10&%24filter=xyz'
    }
}
