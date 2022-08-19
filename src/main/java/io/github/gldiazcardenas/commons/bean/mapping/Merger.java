package io.github.gldiazcardenas.commons.bean.mapping;

/**
 * Definition of a merger which allows to combine the state of two different objects onto only one without creating
 * a new object.  The result will be placed at the target object.
 *
 * @param <S> The source object to be merged into the target.
 * @param <T> The target object which will receive input from the source.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
@FunctionalInterface
public interface Merger<S, T> {

    void merge(S source, T target);

}
