package io.github.gldiazcardenas.commons.util;

import org.slf4j.Logger;

/**
 * Utility method to do logging.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class LogUtils {

    private static final String SEPARATION_FROM_PREFIX = "";

    private LogUtils() {
        super();
    }

    private static String getPrefix(Area area) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = area.toString();
        boolean insideLogUtils = false;
        try {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].getClassName().equals(LogUtils.class.getCanonicalName())) {
                    insideLogUtils = true;
                }
                if (insideLogUtils && !elements[i].getClassName().equals(LogUtils.class.getCanonicalName())) {
                    prefix += ", className=" + elements[i].getClassName() + ", methodName=" + elements[i].getMethodName();
                    return prefix + SEPARATION_FROM_PREFIX;
                }
            }
        }
        catch (Exception ex) {
            //just in case
            return prefix + SEPARATION_FROM_PREFIX;
        }
        return prefix + SEPARATION_FROM_PREFIX;
    }

    public static String getCaller(Class<?> currentClass) {
        String result = "";
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        boolean insideCurrentClass = false;
        try {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].getClassName().equals(currentClass.getCanonicalName())) {
                    insideCurrentClass = true;
                }
                if (insideCurrentClass && !elements[i].getClassName().equals(currentClass.getCanonicalName())) {
                    result += "className=" + elements[i].getClassName() + ", methodName=" + elements[i].getMethodName();
                    return result;
                }
            }
        }
        catch (Exception ex) {
            //just in case
            return result;
        }
        return result;
    }

    public static void logPerformanceDebugMsg(Logger logger, long timeStart) {
        logPerformance(logger, timeStart, null, true);
    }

    public static void logPerformanceDebugMsg(Logger logger, long timeStart, String additionalInfo) {
        logPerformance(logger, timeStart, additionalInfo, true);
    }

    public static void logPerformance(Logger logger, long timeStart) {
        logPerformance(logger, timeStart, null, false);
    }

    public static void logPerformance(Logger logger, long timeStart, String additionalInfo) {
        logPerformance(logger, timeStart, additionalInfo, false);
    }

    public static void logPerformance(Logger logger, long timeStart, String additionalInfo, boolean debug) {
        long millis = System.currentTimeMillis() - timeStart;
        long seconds = millis / 1000;

        String prefix = additionalInfo == null ? getPrefix(Area.PERFORMANCE) : getPrefix(Area.PERFORMANCE) + ", " + additionalInfo;

        if (debug) {
            logger.debug(prefix + ", executionTimeMills=" + millis + ", " + "executionTimeSeconds=" + seconds);
        }
        else {
            logger.info(prefix + ", executionTimeMills=" + millis + ", " + "executionTimeSeconds=" + seconds);
        }
    }

    private enum Area {

        PERFORMANCE("Performance Stats");

        private final String text;

        Area(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static Timer startTimer() {
        return new Timer();
    }

    public static final class Timer {

        private final long startMillis;
        private long executionTimeMillis;

        private Timer() {
            this.startMillis = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return String.valueOf(stop());
        }

        private long stop() {
            if (executionTimeMillis == 0) {
                executionTimeMillis = System.currentTimeMillis() - startMillis;
            }
            return executionTimeMillis;
        }

        public long getExecutionTimeMillis() {
            return stop();
        }

        public long getExecutionTimeSec() {
            return getExecutionTimeMillis() / 1_000L;
        }
    }

    public static MemUsage startMemoryUsage() {
        return new MemUsage();
    }

    public static final class MemUsage {

        private final long startUsage;

        private MemUsage() {
            startUsage = usage();
        }

        @Override
        public String toString() {
            return String.valueOf(usage() - startUsage);
        }

        public static long usage() {
            return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        }

    }

}


