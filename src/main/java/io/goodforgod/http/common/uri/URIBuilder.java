package io.goodforgod.http.common.uri;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Helper for building URI (Inspired by Micronaut UriBuilder)
 *
 * @author Graeme Rocher
 * @author Anton Kurako (GoodforGod)
 * @since 15.02.2022
 */
public interface URIBuilder {

    /**
     * Sets the URI fragment.
     *
     * @param fragment The fragment
     * @return This builder
     */
    @NotNull
    URIBuilder fragment(@Nullable String fragment);

    /**
     * Sets the URI scheme.
     *
     * @param scheme The scheme
     * @return This builder
     */
    @NotNull
    URIBuilder scheme(@Nullable String scheme);

    /**
     * Sets the URI user info.
     *
     * @param userInfo The use info
     * @return This builder
     */
    @NotNull
    URIBuilder userInfo(@Nullable String userInfo);

    /**
     * Sets the URI host.
     *
     * @param host The host to use
     * @return This builder
     */
    @NotNull
    URIBuilder host(@Nullable String host);

    /**
     * Sets the URI port.
     *
     * @param port The port to use
     * @return This builder
     */
    @NotNull
    URIBuilder port(int port);

    /**
     * Appends the given path to the existing path correctly handling '/'. If path is null it is simply
     * ignored.
     *
     * @param path The path
     * @return This builder
     */
    @NotNull
    URIBuilder path(@Nullable String path);

    /**
     * Replaces the existing path if any. If path is null it is simply ignored.
     *
     * @param path The path
     * @return This builder
     */
    @NotNull
    URIBuilder replacePath(@Nullable String path);

    /**
     * Adds a query parameter for the give name and values. The values will be URI encoded. If either
     * name or values are null the value will be ignored.
     *
     * @param name   The name
     * @param values The values
     * @return This builder
     */
    @NotNull
    URIBuilder param(@NotNull String name, @NotNull String... values);

    /**
     * Adds a query parameter for the give name and values. The values will be URI encoded. If either
     * name or values are null the value will be ignored.
     *
     * @param name   The name
     * @param values The values with be converted to String via {@link String#valueOf(Object)}
     * @return This builder
     */
    @NotNull
    default URIBuilder param(String name, @NotNull Object... values) {
        final String[] valuesAsStrings = Arrays.stream(values)
                .flatMap(v -> (v instanceof Collection)
                        ? ((Collection<?>) v).stream()
                        : Stream.of(v))
                .map(String::valueOf)
                .toArray(String[]::new);

        return param(name, valuesAsStrings);
    }

    /**
     * Adds a query parameter for the give name and values. The values will be URI encoded. If either
     * name or values are null the value will be ignored.
     *
     * @param name   The name
     * @param values The values
     * @return This builder
     */
    @NotNull
    URIBuilder replaceParam(@NotNull String name, @NotNull String... values);

    /**
     * Adds a query parameter for the give name and values. The values will be URI encoded. If either
     * name or values are null the value will be ignored.
     *
     * @param name   The name
     * @param values The values with be converted to String via {@link String#valueOf(Object)}
     * @return This builder
     */
    @NotNull
    default URIBuilder replaceParam(@NotNull String name, @NotNull Object... values) {
        final String[] valuesAsStrings = Arrays.stream(values)
                .flatMap(v -> (v instanceof Collection)
                        ? ((Collection<?>) v).stream()
                        : Stream.of(v))
                .map(String::valueOf)
                .toArray(String[]::new);

        return replaceParam(name, valuesAsStrings);
    }

    /**
     * The constructed URI.
     *
     * @return Build the URI
     * @throws IllegalArgumentException if the URI to be constructed is invalid
     */
    @NotNull
    URI build();

    /**
     * Create a {@link URIBuilder} with the given base URI as a starting point.
     *
     * @param uri The URI
     * @return The builder
     */
    static @NotNull URIBuilder of(@NotNull URI uri) {
        Objects.requireNonNull(uri);
        return new DefaultURIBuilder(uri);
    }

    /**
     * Create a {@link URIBuilder} with the given base URI as a starting point.
     *
     * @param uri The URI
     * @return The builder
     */
    static @NotNull URIBuilder of(@NotNull CharSequence uri) {
        Objects.requireNonNull(uri);
        return new DefaultURIBuilder(uri);
    }
}
