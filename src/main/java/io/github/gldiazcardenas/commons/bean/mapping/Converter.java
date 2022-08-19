package io.github.gldiazcardenas.commons.bean.mapping;

/**
 * Converter definition that allows to transform an object representation to another different representation.
 *
 * @param <FROM> The type of the source.
 * @param <TO> The type of the target.
 * @author Gabriel Diaz, 17/12/2020.
 */
@FunctionalInterface
public interface Converter<FROM, TO> {

    /**
     * Converts the passed source object to the target representation defined in this converter.
     *
     * @param source The source object to be converted.
     * @return The object result of the conversion.
     */
    TO convert(FROM source);

}
