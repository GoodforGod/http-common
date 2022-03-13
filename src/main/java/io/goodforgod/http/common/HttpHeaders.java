package io.goodforgod.http.common;

import java.util.*;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Constants for common HTTP headers.
 * <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html">W3</a>
 *
 * @author Anton Kurako (GoodforGod)
 * @since 13.03.2022
 */
public final class HttpHeaders {

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CH = "Accept-CH";
    public static final String ACCEPT_CH_LIFETIME = "Accept-CH-Lifetime";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String ACCEPT_PATCH = "Accept-Patch";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    public static final String AGE = "Age";
    public static final String ALLOW = "Allow";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_INFO = "Authorization-Info";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONNECTION = "Connection";
    public static final String CONTENT_BASE = "Content-Base";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_DPR = "Content-DPR";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String CROSS_ORIGIN_RESOURCE_POLICY = "Cross-Origin-Resource-Policy";
    public static final String DATE = "Date";
    public static final String DEVICE_MEMORY = "Device-Memory";
    public static final String DOWNLINK = "Downlink";
    public static final String DPR = "DPR";
    public static final String ECT = "ECT";
    public static final String ETAG = "ETag";
    public static final String EXPECT = "Expect";
    public static final String EXPIRES = "Expires";
    public static final String FEATURE_POLICY = "Feature-Policy";
    public static final String FORWARDED = "Forwarded";
    public static final String FROM = "From";
    public static final String HOST = "Host";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_RANGE = "If-Range";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LINK = "Link";
    public static final String LOCATION = "Location";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String ORIGIN = "Origin";
    public static final String PRAGMA = "Pragma";
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String REFERRER_POLICY = "Referrer-Policy";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String RTT = "RTT";
    public static final String SAVE_DATA = "Save-Data";
    public static final String SEC_WEBSOCKET_KEY1 = "Sec-WebSocket-Key1";
    public static final String SEC_WEBSOCKET_KEY2 = "Sec-WebSocket-Key2";
    public static final String SEC_WEBSOCKET_LOCATION = "Sec-WebSocket-Location";
    public static final String SEC_WEBSOCKET_ORIGIN = "Sec-WebSocket-Origin";
    public static final String SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    public static final String SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    public static final String SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    public static final String SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
    public static final String SERVER = "Server";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String SET_COOKIE2 = "Set-Cookie2";
    public static final String SOURCE_MAP = "SourceMap";
    public static final String TE = "TE";
    public static final String TRAILER = "Trailer";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String UPGRADE = "Upgrade";
    public static final String USER_AGENT = "User-Agent";
    public static final String VARY = "Vary";
    public static final String VIA = "Via";
    public static final String VIEWPORT_WIDTH = "Viewport-Width";
    public static final String WARNING = "Warning";
    public static final String WEBSOCKET_LOCATION = "WebSocket-Location";
    public static final String WEBSOCKET_ORIGIN = "WebSocket-Origin";
    public static final String WEBSOCKET_PROTOCOL = "WebSocket-Protocol";
    public static final String WIDTH = "Width";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    public static final String X_AUTH_TOKEN = "X-Auth-Token";

    private static final HttpHeaders EMPTY = new HttpHeaders(Collections.emptyMap());

    private final Map<String, List<String>> multiHeaderMap;

    private HttpHeaders(@NotNull Map<String, List<String>> multiHeaderMap) {
        this.multiHeaderMap = multiHeaderMap;
    }

    @NotNull
    public static HttpHeaders ofMultiMap(@Nullable Map<String, List<String>> multiHeaderMap) {
        if (multiHeaderMap == null || multiHeaderMap.isEmpty()) {
            return EMPTY;
        }

        final Map<String, List<String>> headers = Map.copyOf(multiHeaderMap.entrySet().stream()
                .filter(e -> e.getKey() != null && !e.getKey().isEmpty()
                        && e.getValue() != null && !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, e -> List.copyOf(e.getValue()))));

        return new HttpHeaders(headers);
    }

    @NotNull
    public static HttpHeaders ofMap(@Nullable Map<String, String> headerMap) {
        if (headerMap == null || headerMap.isEmpty()) {
            return EMPTY;
        }

        final Map<String, List<String>> headers = Map.copyOf(headerMap.entrySet().stream()
                .filter(e -> e.getKey() != null && !e.getKey().isEmpty()
                        && e.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> List.of(e.getValue()))));

        return new HttpHeaders(headers);
    }

    @NotNull
    public static HttpHeaders of(@Nullable String... headerAndValue) {
        if (headerAndValue == null || headerAndValue.length == 0) {
            return EMPTY;
        }

        final boolean isEven = headerAndValue.length % 2 == 0;
        if (!isEven) {
            throw new IllegalArgumentException("Header and values amount must be even, but was: " + headerAndValue.length);
        }

        final Map<String, List<String>> multiHeaderMap = new HashMap<>(headerAndValue.length + 5);
        for (int i = 0; i < headerAndValue.length; i += 2) {
            final String key = headerAndValue[i];
            final String value = headerAndValue[i + 1];
            if (key != null && value != null) {
                multiHeaderMap.put(key, List.of(value));
            }
        }

        final Map<String, List<String>> headers = Map.copyOf(multiHeaderMap);
        return new HttpHeaders(headers);
    }

    @NotNull
    public static HttpHeaders empty() {
        return EMPTY;
    }

    /**
     * Get the first value of the given header.
     *
     * @param headerName The header name
     * @return The first value or null if it is present
     */
    @NotNull
    public Optional<String> findFirst(@NotNull CharSequence headerName) {
        final List<String> values = multiHeaderMap.get(headerName.toString());
        if (values == null) {
            return Optional.empty();
        }

        return Optional.of(values.get(0));
    }

    /**
     * Find all values of the given header.
     *
     * @param headerName The header name
     * @return All values or empty list if not present
     */
    @NotNull
    public List<String> findAll(@NotNull CharSequence headerName) {
        return multiHeaderMap.getOrDefault(headerName.toString(), Collections.emptyList());
    }

    /**
     * @return all headers and values as multi map
     */
    @NotNull
    public Map<String, List<String>> getMultiMap() {
        return multiHeaderMap;
    }

    /**
     * @return all headers and only first corresponding value as map
     */
    @NotNull
    public Map<String, String> getMap() {
        return multiHeaderMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().iterator().next()));
    }

    /**
     * The request or response content type.
     *
     * @return The content type
     */
    @NotNull
    public Optional<Long> contentLength() {
        return findFirst(CONTENT_LENGTH)
                .map(Long::parseLong);
    }

    /**
     * The request or response content type.
     *
     * @return The content type
     */
    @NotNull
    public Optional<MediaType> contentType() {
        return findFirst(CONTENT_TYPE).map(MediaType::of);
    }

    /**
     * @return The {@link #ORIGIN} header
     */
    @NotNull
    public Optional<String> origin() {
        return findFirst(ORIGIN);
    }

    /**
     * @return The {@link #AUTHORIZATION} header
     */
    @NotNull
    public Optional<String> authorization() {
        return findFirst(AUTHORIZATION);
    }

    /**
     * @return Whether the {@link HttpHeaders#CONNECTION} header is set to Keep-Alive
     */
    public boolean isKeepAlive() {
        return findFirst(CONNECTION)
                .map(v -> v.equalsIgnoreCase("keep-alive"))
                .orElse(false);
    }

    /**
     * A list of accepted {@link MediaType} instances.
     *
     * @return A list of zero or many {@link MediaType} instances
     */
    @NotNull
    public List<MediaType> accept() {
        final List<String> values = findAll(ACCEPT);
        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        final List<MediaType> mediaTypes = new ArrayList<>(6);
        for (String value : values) {
            for (String token : value.split(",")) {
                if (!token.isEmpty()) {
                    try {
                        mediaTypes.add(MediaType.of(token));
                    } catch (IllegalArgumentException e) {
                        // ignore
                    }
                }
            }
        }

        return mediaTypes;
    }
}
