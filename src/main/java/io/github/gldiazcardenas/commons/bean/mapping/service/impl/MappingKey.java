package io.github.gldiazcardenas.commons.bean.mapping.service.impl;

import java.util.Objects;

/**
 * Key that allows to map two different classes.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
final class MappingKey {

    private Class<?> sourceClass;
    private Class<?> targetClass;

    private MappingKey() {
        super();
    }

    public static MappingKey from(Class<?> sourceClass, Class<?> targetClass) {
        MappingKey key = new MappingKey();
        key.sourceClass = sourceClass;
        key.targetClass = targetClass;
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MappingKey key = (MappingKey) o;

        return Objects.equals(sourceClass, key.sourceClass) && Objects.equals(targetClass, key.targetClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceClass, targetClass);
    }

    @Override
    public String toString() {
        return "from " + sourceClass.getCanonicalName() + " to " + targetClass.getCanonicalName();
    }

}
