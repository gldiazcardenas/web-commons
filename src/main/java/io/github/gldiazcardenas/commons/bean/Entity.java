package io.github.gldiazcardenas.commons.bean;

import java.io.Serializable;

/**
 * Object that represents a persistable entity into any data storage.
 *
 * @param <ID> The type of the id for the entity.
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class Entity<ID extends Serializable> extends Identifiable<ID> implements Serializable {

    public interface Attributes extends Identifiable.Attributes {
    }

}
