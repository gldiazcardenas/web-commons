package io.github.gldiazcardenas.commons.bean.mapping;

import io.github.gldiazcardenas.commons.bean.Domain;
import io.github.gldiazcardenas.commons.bean.Entity;

/**
 * Converter between a {@link Domain} and {@link Entity} objects, which allows conversion bidirectional.
 *
 * @param <D> The type of domain bean.
 * @param <E> The type of entity.
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface DomainEntityConverter<D extends Domain<?>, E extends Entity<?>> extends BidirectionalConverter<D, E> {

    /**
     * Converts from domain bean to entity object, this creates a new instance of entity and returns it.
     * @param domain The domain bean to be converted.
     * @return The entity created based on the passed domain bean.
     */
    E convert(D domain);

    /**
     * Converts from entity to domain object, this creates a new instance of domain and returns it.
     * @param entity The entity bean to be converted.
     * @return The domain bean created based on the passed entity.
     */
    D reverse(E entity);

}
