package io.goodforgod.http.common.exception;

import io.goodforgod.http.common.HttpStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Exception that contains {@link HttpStatus} and can have formatted message.
 * Formatter example: ("Temperature set to {}. Old temperature was {}.", 1, 2) will result in
 * "Temperature set to 1. Old temperature was 2."
 *
 * @author Anton Kurako (GoodforGod)
 * @since 20.02.2022
 */
public class HttpStatusException extends FormattedException {

    private final HttpStatus status;

    public HttpStatusException(@NotNull HttpStatus status) {
        super(status.reason());
        this.status = status;
    }

    public HttpStatusException(@NotNull HttpStatus status, String formatter, Object... vars) {
        super(formatter, vars);
        this.status = status;
    }

    public HttpStatusException(@NotNull HttpStatus status, String formatter, Throwable cause, Object... vars) {
        super(formatter, cause, vars);
        this.status = status;
    }

    @NotNull
    public HttpStatus status() {
        return status;
    }
}
