package io.goodforgod.http.common.uri;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 16.02.2022
 */
class URITemplateExpandURITests extends Assertions {

    private static Stream<Arguments> source() {
        final Map<String, Object> map = new HashMap<>();
        map.put("x", 1024);
        map.put("y", 768);
        map.put("empty", null);

        return Stream.of(
                Arguments.of("", Map.of("", ""), ""),
                Arguments.of("/", Map.of("", ""), "/"),
                Arguments.of("{var}", Map.of("var", "value"), "value"),
                Arguments.of("{var:20}", Map.of("var", "value"), "value"),
                Arguments.of("{var:3}", Map.of("var", "value"), "val"),
                Arguments.of("{semi}", Map.of("semi", ";"), "%3B"),
                Arguments.of("{semi:2}", Map.of("semi", ";"), "%3B"),
                Arguments.of("find{?year*}", Map.of("", ""), "find"),
                Arguments.of("{hello}", Map.of("hello", "Hello World!"), "Hello%20World%21"),
                Arguments.of("{half}", Map.of("half", "50%"), "50%25"),
                Arguments.of("O{empty}X", Map.of("empty", ""), "OX"),
                Arguments.of("O{undef}X", Map.of("", ""), "OX"),
                Arguments.of("{x,y}", Map.of("x", 1024, "y", 768), "1024,768"),
                Arguments.of("?{x,empty}", Map.of("x", 1024, "empty", ""), "?1024,"),
                Arguments.of("?{x,undef}", Map.of("x", 1024, "empty", ""), "?1024"),
                Arguments.of("?{undef,y}", Map.of("y", 768), "?768"),
                Arguments.of("map?{x,y}", Map.of("x", 1024, "y", 768), "map?1024,768"),
                Arguments.of("{x,hello,y}", Map.of("x", 1024, "y", 768, "hello", "Hello World!"), "1024,Hello%20World%21,768"),
                Arguments.of("{var:30}", Map.of("var", "value"), "value"),
                Arguments.of("{+var}", Map.of("var", "value"), "value"),
                Arguments.of("{+hello}", Map.of("hello", "Hello World!"), "Hello%20World!"),
                Arguments.of("{+half}", Map.of("half", "50%"), "50%25"),
                Arguments.of("{base}index", Map.of("base", "http://example.com/home/"),
                        "http%3A%2F%2Fexample.com%2Fhome%2Findex"),
                Arguments.of("{+base}index", Map.of("base", "http://example.com/home/"), "http://example.com/home/index"),
                Arguments.of("{+base}{hello}", Map.of("base", "http://example.com/home/", "hello", "Hello World!"),
                        "http://example.com/home/Hello%20World%21"),
                Arguments.of("O{+empty}X", Map.of("empty", ""), "OX"),
                Arguments.of("O{+undef}X", Map.of("", ""), "OX"),
                Arguments.of("{+path}/here", Map.of("path", "/foo/bar"), "/foo/bar/here"),
                Arguments.of("here?ref={+path}", Map.of("path", "/foo/bar"), "here?ref=/foo/bar"),
                Arguments.of("up{+path}{var}/here", Map.of("path", "/foo/bar", "var", "value"), "up/foo/barvalue/here"),
                Arguments.of("{+x,hello,y}", Map.of("x", 1024, "y", 768, "hello", "Hello World!"), "1024,Hello%20World!,768"),
                Arguments.of("{+path,x}/here", Map.of("path", "/foo/bar", "x", 1024), "/foo/bar,1024/here"),
                Arguments.of("{+path:6}/here", Map.of("path", "/foo/bar"), "/foo/b/here"),
                Arguments.of("{#var}", Map.of("var", "value"), "#value"),
                Arguments.of("{#hello}", Map.of("hello", "Hello World!"), "#Hello%20World!"),
                Arguments.of("{#half}", Map.of("half", "50%"), "#50%25"),
                Arguments.of("foo{#empty}", Map.of("empty", ""), "foo#"),
                Arguments.of("foo{#undef}", Map.of("", ""), "foo"),
                Arguments.of("X{#var}", Map.of("var", "value"), "X#value"),
                Arguments.of("X{#hello}", Map.of("hello", "Hello World!", "var", "value"), "X#Hello%20World!"),
                Arguments.of("{#x,hello,y}", Map.of("x", 1024, "y", 768, "hello", "Hello World!"), "#1024,Hello%20World!,768"),
                Arguments.of("{#path,x}/here", Map.of("path", "/foo/bar", "x", 1024), "#/foo/bar,1024/here"),
                Arguments.of("{#path:6}/here", Map.of("path", "/foo/bar"), "#/foo/b/here"),
                Arguments.of("X{.var}", Map.of("var", "value"), "X.value"),
                Arguments.of("X{.empty}", Map.of("empty", ""), "X."),
                Arguments.of("X{.undef}", Map.of("", ""), "X"),
                Arguments.of("X{.x,y}", Map.of("x", 1024, "y", 768), "X.1024.768"),
                Arguments.of("{.who}", Map.of("who", "fred"), ".fred"),
                Arguments.of("{.who,who}", Map.of("who", "fred"), ".fred.fred"),
                Arguments.of("{.half,who}", Map.of("half", "50%", "who", "fred"), ".50%25.fred"),
                Arguments.of("X{.var:3}", Map.of("var", "value"), "X.val"),
                Arguments.of("{/who}", Map.of("who", "fred"), "/fred"),
                Arguments.of("{/who,who}", Map.of("who", "fred"), "/fred/fred"),
                Arguments.of("{/var,empty,empty}", Map.of("var", "fred"), "/fred"),
                Arguments.of("{/half,who}", Map.of("half", "50%", "who", "fred"), "/50%25/fred"),
                Arguments.of("{/who,dub}", Map.of("who", "fred", "dub", "me/too"), "/fred/me%2Ftoo"),
                Arguments.of("{/var}", Map.of("var", "value"), "/value"),
                Arguments.of("{/var,undef}", Map.of("var", "value"), "/value"),
                Arguments.of("{/var,empty}", Map.of("var", "value", "empty", ""), "/value/"),
                Arguments.of("{/var,x}/here", Map.of("var", "value", "x", 1024), "/value/1024/here"),
                Arguments.of("{/var:1,var}", Map.of("var", "value"), "/v/value"),
                Arguments.of("/files/content{/path*}{/name}", Map.of("name", "value"), "/files/content/value"),
                Arguments.of("{;who}", Map.of("who", "fred"), ";who=fred"),
                Arguments.of("{;half}", Map.of("half", "50%"), ";half=50%25"),
                Arguments.of("{;empty}", Map.of("empty", ""), ";empty"),
                Arguments.of("{;v,empty,who}", Map.of("v", 6, "empty", "", "who", "fred"), ";v=6;empty;who=fred"),
                Arguments.of("{;v,undef,who}", Map.of("v", 6, "who", "fred"), ";v=6;who=fred"),
                Arguments.of("{;x,y}", Map.of("x", 1024, "y", 768), ";x=1024;y=768"),
                Arguments.of("{;x,y,empty}", Map.of("x", 1024, "y", 768, "empty", ""), ";x=1024;y=768;empty"),
                Arguments.of("{;x,y,undef}", Map.of("x", 1024, "y", 768, "empty", ""), ";x=1024;y=768"),
                Arguments.of("{;hello:5}", Map.of("hello", "Hello World!"), ";hello=Hello"),
                Arguments.of("{?who}", Map.of("who", "fred"), "?who=fred"),
                Arguments.of("{?half}", Map.of("half", "50%"), "?half=50%25"),
                Arguments.of("{?x,y}", Map.of("x", 1024, "y", 768, "empty", ""), "?x=1024&y=768"),
                Arguments.of("{?x,y,empty}", Map.of("x", 1024, "y", 768, "empty", ""), "?x=1024&y=768&empty="),
                Arguments.of("{?x,y,undef}", Map.of("x", 1024, "y", 768, "empty", ""), "?x=1024&y=768"),
                Arguments.of("{?var:3}", Map.of("var", "value"), "?var=val"),
                Arguments.of("{?hello}", Map.of("hello", "Hello World!"), "?hello=Hello+World%21"),
                Arguments.of("?fixed=yes{&x}", Map.of("x", 1024), "?fixed=yes&x=1024"),
                Arguments.of("{&x,y,empty}", Map.of("x", 1024, "y", 768, "empty", ""), "&x=1024&y=768&empty="),
                Arguments.of("{&x,y,empty}", map, "&x=1024&y=768"),
                Arguments.of("{&var:3}", Map.of("var", "value"), "&var=val"));
    }

    @ParameterizedTest
    @MethodSource("source")
    void expandURITemplateWithArgumentsForPath(String template, Map<String, Object> params, String expected) {
        final URITemplate uriTemplate = new URITemplate(template);
        assertEquals(expected, uriTemplate.expand(params));
    }
}
