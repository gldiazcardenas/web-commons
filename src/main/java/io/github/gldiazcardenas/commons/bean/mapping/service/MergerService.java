package io.github.gldiazcardenas.commons.bean.mapping.service;

/**
 * Service that allows merging objects from different types.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface MergerService {

    /**
     * Checks whether the two types can be merged if the service allows that action.
     *
     * @param sourceClass The source's object class.
     * @param targetClass The target's object class.
     * @param <S> The type of source.
     * @param <T> The type of target object.
     * @return {@code true} if merging is possible through this service.
     */
    <S, T> boolean canBeMerged(Class<S> sourceClass, Class<T> targetClass);

    /**
     * Does the merging action with the two passed objects.
     * @param source the source input.
     * @param target The target object.
     * @param <S> The type of source.
     * @param <T> The type of target object.
     * @throws MergerException In case merging is not allowed or something wrong happened while merging.
     */
    <S, T> void merge(S source, T target) throws MergerException;

}
