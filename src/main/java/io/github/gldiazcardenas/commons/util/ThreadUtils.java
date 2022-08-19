package io.github.gldiazcardenas.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Utility method to manipulate threads.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ThreadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtils.class);

    private ThreadUtils() {
        super();
    }

    public static boolean sleep(long milliseconds) {
        if (milliseconds > 0) {
            return sleep(Duration.ofMillis(milliseconds));
        }
        return false;
    }

    public static boolean sleep(Duration duration) {
        if (duration != null) {
            try {
                Thread.sleep(duration.toMillis());
                return true;
            }
            catch (InterruptedException e) {
                LOGGER.debug(e.getLocalizedMessage(), e);
            }
        }
        return false;
    }

}
