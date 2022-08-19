package io.github.gldiazcardenas.commons.bean.validation;

/**
 * Object that allows to validate one passed object inside a process.
 *
 * @param <T> The type of object this validator works with.
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface Validator<T> {

    Validation validate(T object);

    Class<?>[] getScopes();

    default boolean isImmediate() {
        return true;
    }

}
