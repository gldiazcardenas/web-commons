package io.github.gldiazcardenas.commons.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Utility method to do some Java reflection operations.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ReflectionUtils {

    private ReflectionUtils() {
        super();
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericClassType(Class<?> clazz, int index) {
        return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
    }

    public static <T> Class<T> getGenericInterfaceType(Class<?> clazz, int index) {
        return getGenericInterfaceType(clazz, 0, index);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericInterfaceType(Class<?> clazz, int intIndex, int index) {
        return (Class<T>) ((ParameterizedType) (clazz.getGenericInterfaces()[intIndex])).getActualTypeArguments()[index];
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw ExceptionUtils.toRuntime(e);
        }
    }

}
