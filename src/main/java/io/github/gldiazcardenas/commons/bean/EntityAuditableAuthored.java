package io.github.gldiazcardenas.commons.bean;

import java.io.Serializable;

/**
 * Object that represents a persistable auditable with author entity.
 *
 * @param <ID> The type of the id for the entity.
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class EntityAuditableAuthored<ID extends Serializable> extends EntityAuditable<ID> {

    /**
     * The attributes of the entity.
     */
    public interface Attributes extends EntityAuditable.Attributes {
        String CREATED_BY = "createdBy";

        String UPDATED_BY = "updatedBy";
    }

    public abstract String getCreatedBy();

    public abstract void setCreatedBy(String createdBy);

    public abstract String getUpdatedBy();

    public abstract void setUpdatedBy(String updatedBy);

}
