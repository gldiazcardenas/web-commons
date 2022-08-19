package io.github.gldiazcardenas.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Utility method to manipulate exceptions.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        super();
    }

    public static String getStackTrace(Throwable throwable) {
        return Optional.ofNullable(throwable)
                .map(t -> {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    t.printStackTrace(pw);
                    return sw.toString();
                })
                .orElse(null);
    }

    public static RuntimeException toRuntime(Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(e);
    }

}
