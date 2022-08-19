package io.github.gldiazcardenas.commons.bean.mapping.service.impl;

import io.github.gldiazcardenas.commons.bean.mapping.Merger;
import io.github.gldiazcardenas.commons.bean.mapping.service.MergerException;
import io.github.gldiazcardenas.commons.bean.mapping.service.MergerService;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation that receives merges externally.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class MergerServiceImpl implements MergerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MergerServiceImpl.class);

    private final Map<MappingKey, Merger<?, ?>> mergers = new HashMap<>();

    public MergerServiceImpl(Collection<Merger<?, ?>> mergers) {
        Optional.ofNullable(mergers).ifPresent(cvs -> cvs.forEach(this::registerMerger));
    }

    @Override
    public <S, T> boolean canBeMerged(Class<S> sourceClass, Class<T> targetClass) {
        return mergers.containsKey(MappingKey.from(sourceClass, targetClass));
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <S, T> void merge(S source, T target) throws MergerException {
        if (source == null || target == null) {
            return;
        }

        MappingKey key = MappingKey.from(source.getClass(), target.getClass());
        Merger<S, T> merger = (Merger<S, T>) mergers.get(key);

        if (merger == null) {
            throw new MergerException(source, target, String.format("No merger found %s", key.toString()));
        }

        try {
            merger.merge(source, target);
        }
        catch (Exception e) {
            throw new MergerException(source, target, e);
        }
    }

    private void registerMerger(Merger<?, ?> merger) {
        MappingKey key = MappingKey.from(
                ReflectionUtils.getGenericInterfaceType(merger.getClass(), 0),
                ReflectionUtils.getGenericInterfaceType(merger.getClass(), 1)
        );
        mergers.put(key, merger);
        LOGGER.debug("Merger found => {}", key.toString());
    }

}
