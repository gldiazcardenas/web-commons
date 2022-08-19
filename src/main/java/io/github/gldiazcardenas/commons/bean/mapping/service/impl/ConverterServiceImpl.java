package io.github.gldiazcardenas.commons.bean.mapping.service.impl;

import io.github.gldiazcardenas.commons.bean.mapping.Converter;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterException;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterService;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Conversion service implementation that incorporates all logic to be able to convert objects from one format to another.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ConverterServiceImpl implements ConverterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterServiceImpl.class);

    private final Map<MappingKey, Converter<?, ?>> converters = new HashMap<>();

    public ConverterServiceImpl(Collection<Converter<?, ?>> converters) {
        Optional.ofNullable(converters).ifPresent(cvs -> cvs.forEach(this::registerConverter));
    }

    @Override
    public <S, T> T convertTo(S source, Class<T> targetClass) {
        return convert(source, targetClass);
    }

    @Override
    public <S, T> List<T> convertTo(Iterable<S> source, Class<T> targetClass) {
        List<T> result = null;
        if (source != null) {
            result = Lists.newArrayList();
            for (S s : source) {
                result.add(convert(s, targetClass));
            }
        }
        return result;
    }

    private void registerConverter(Converter<?, ?> converter) {
        MappingKey key = MappingKey.from(
                ReflectionUtils.getGenericInterfaceType(converter.getClass(), 0),
                ReflectionUtils.getGenericInterfaceType(converter.getClass(), 1)
        );
        converters.put(key, converter);
        LOGGER.debug("Converter found => {}", key);
    }

    @SuppressWarnings({"unchecked"})
    private <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        MappingKey key = MappingKey.from(source.getClass(), targetClass);
        Converter<S, T> converter = (Converter<S, T>) converters.get(key);

        if (converter == null) {
            throw new ConverterException(source, targetClass, String.format("No converter found %s", key.toString()));
        }

        try {
            return converter.convert(source);
        }
        catch (Exception e) {
            throw new ConverterException(source, targetClass, e);
        }
    }

}
