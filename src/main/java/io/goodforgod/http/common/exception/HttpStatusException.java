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

    /**
     * Message of exception will be equal to {@link HttpStatus#reason()}
     * 
     * @param status {@link HttpStatus} to indicate incident
     */
    public HttpStatusException(@NotNull HttpStatus status) {
        super(status.reason());
        this.status = status;
    }

    /**
     * Allow format exception message as analogy to org.slf4j.Logger formatter
     * In case of formatter: "message {}" with variable "4" formatter result will be: "message 4"
     *
     * @param status    {@link HttpStatus} to indicate incident
     * @param formatter message or formatter to use
     * @param vars      to use in formatter
     */
    public HttpStatusException(@NotNull HttpStatus status, String formatter, Object... vars) {
        super(formatter, vars);
        this.status = status;
    }

    /**
     * Allow format exception message as analogy to org.slf4j.Logger formatter
     * In case of formatter: "message {}" with variable "4" formatter result will be: "message 4"
     *
     * @param status    {@link HttpStatus} to indicate incident
     * @param formatter message or formatter to use
     * @param vars      to use in formatter
     * @param cause     root exception
     */
    public HttpStatusException(@NotNull HttpStatus status, String formatter, Throwable cause, Object... vars) {
        super(formatter, cause, vars);
        this.status = status;
    }

    @NotNull
    public HttpStatus status() {
        return status;
    }
}
