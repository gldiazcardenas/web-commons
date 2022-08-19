package io.github.gldiazcardenas.commons.bean.mapping.service;

import java.util.List;

/**
 * Conversion service that allows to convert objects from one format to another.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface ConverterService {

    /**
     * Converts the given source object to the format specified by the passed target type class.
     *
     * @param source The source object to be converted.
     * @param targetClass The type of object expected in the result.
     * @param <S> The type of source.
     * @param <T> The type of target.
     * @return The object result of conversion.
     * @throws ConverterException If there is not converted that allows the conversion, or if some error happens while converting.
     */
    <S, T> T convertTo(S source, Class<T> targetClass) throws ConverterException;

    /**
     * Converts a list of source objects to the format specified by the passed target type class.
     * @param sources The list of source objects to be converted.
     * @param targetClass The type of object expected in the result.
     * @param <S> The type of source.
     * @param <T> The type of target.
     * @return The object result of conversion.
     * @throws ConverterException If there is not converted that allows the conversion, or if some error happens while converting.
     */
    <S, T> List<T> convertTo(Iterable<S> sources, Class<T> targetClass) throws ConverterException;

}
