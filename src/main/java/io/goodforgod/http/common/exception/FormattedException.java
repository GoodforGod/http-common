package io.goodforgod.http.common.exception;

/**
 * Formatter example: ("Temperature set to {}. Old temperature was {}.", 1, 2) will result in
 * "Temperature set to 1. Old temperature was 2."
 *
 * @author Anton Kurako (GoodforGod)
 * @since 18.02.2022
 */
public class FormattedException extends RuntimeException {

    public FormattedException(String formatter, Object... vars) {
        super(format(formatter, vars));
    }

    public FormattedException(String formatter, Throwable cause, Object... vars) {
        super(format(formatter, vars), cause);
    }

    private static String format(String formatter, Object... vars) {
        if (vars == null) {
            return formatter;
        } else if (vars.length == 1) {
            return MessageFormatter.format(formatter, vars[0]);
        } else if (vars.length == 2) {
            return MessageFormatter.format(formatter, vars[0], vars[1]);
        }

        return MessageFormatter.format(formatter, vars);
    }
}
