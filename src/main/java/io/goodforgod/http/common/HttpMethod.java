package io.goodforgod.http.common;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An enum containing the valid HTTP methods.
 * <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">The World Wide Web
 * Consortium</a>
 * 
 * @author Graeme Rocher
 * @author Anton Kurako (GoodforGod)
 * @since 15.02.2022
 */
public enum HttpMethod implements CharSequence {

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.2.
     */
    OPTIONS,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.3.
     */
    GET,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.4.
     */
    HEAD,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.5.
     */
    POST,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.6.
     */
    PUT,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.7.
     */
    DELETE,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.8.
     */
    TRACE,

    /**
     * See https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.9.
     */
    CONNECT,

    /**
     * See https://tools.ietf.org/html/rfc5789.
     */
    PATCH,

    /**
     * A custom non-standard HTTP method.
     */
    CUSTOM;

    /**
     * @return true if given method allows a request body
     */
    public boolean allowRequestBody() {
        return POST.equals(this)
                || PUT.equals(this)
                || PATCH.equals(this)
                || OPTIONS.equals(this)
                || DELETE.equals(this)
                || CUSTOM.equals(this);
    }

    /**
     * @param httpMethodName Name of the http method (may be nonstandard)
     * @return the value of enum (CUSTOM by default).
     */
    @NotNull
    public static HttpMethod of(@NotNull String httpMethodName) {
        HttpMethod httpMethod = parseString(httpMethodName);
        if (httpMethod != null) {
            return httpMethod;
        }

        httpMethodName = httpMethodName.toUpperCase();
        httpMethod = parseString(httpMethodName);
        if (httpMethod != null) {
            return httpMethod;
        }
        return CUSTOM;
    }

    /**
     * @param httpMethodName to parse which is always upper or lower case
     * @return Http parsed method name instance or null
     */
    @Nullable
    private static HttpMethod parseString(@NotNull String httpMethodName) {
        return switch (httpMethodName) {
            case "GET", "get" -> GET;
            case "POST", "post" -> POST;
            case "PUT", "put" -> PUT;
            case "PATCH", "patch" -> PATCH;
            case "DELETE", "delete" -> DELETE;
            case "OPTIONS", "options" -> OPTIONS;
            case "HEAD", "head" -> HEAD;
            case "TRACE", "trace" -> TRACE;
            case "CONNECT", "connect" -> CONNECT;
            default -> null;
        };
    }

    @Override
    public int length() {
        return name().length();
    }

    @Override
    public char charAt(int index) {
        return name().charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return name().subSequence(start, end);
    }

    @NotNull
    @Override
    public String toString() {
        return super.toString();
    }
}
