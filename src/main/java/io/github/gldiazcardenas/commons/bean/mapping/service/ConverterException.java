package io.github.gldiazcardenas.commons.bean.mapping.service;

/**
 * Conversion exception that is thrown by {@link ConverterService} if something goes wrong while converting.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public class ConverterException extends RuntimeException {

    private final Object source;
    private final Class<?> targetClass;

    public ConverterException(Object source, Class<?> targetClass, String message) {
        super(message);
        this.source = source;
        this.targetClass = targetClass;
    }

    public ConverterException(Object source, Class<?> targetClass, Throwable cause) {
        super(cause);
        this.source = source;
        this.targetClass = targetClass;
    }

    public final Object getSource() {
        return source;
    }

    public final Class<?> getTargetClass() {
        return targetClass;
    }

}
