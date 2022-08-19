package io.github.gldiazcardenas.commons.bean.mapping.service;

/**
 * Exception thrown when merging is not allowed or any error happened while merging.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public class MergerException extends RuntimeException {

    private final Object source;
    private final Object target;

    public MergerException(Object source, Object target, String message) {
        super(message);
        this.source = source;
        this.target = target;
    }

    public MergerException(Object source, Object target, Throwable cause) {
        super(cause);
        this.source = source;
        this.target = target;
    }

    public Object getSource() {
        return source;
    }

    public Object getTarget() {
        return target;
    }
}
