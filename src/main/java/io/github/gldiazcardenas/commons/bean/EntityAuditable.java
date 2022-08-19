package io.github.gldiazcardenas.commons.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Object that represents a persistable auditable entity.
 *
 * @param <ID> The type of the id for the entity.
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class EntityAuditable<ID extends Serializable> extends Entity<ID> {

    /**
     * The attributes of the entity.
     */
    public interface Attributes extends Entity.Attributes {
        String CREATE_DATE = "createDate";

        String UPDATE_DATE = "updateDate";
    }

    public abstract LocalDateTime getCreateDate();

    public abstract void setCreateDate(LocalDateTime createDate);

    public abstract LocalDateTime getUpdateDate();

    public abstract void setUpdateDate(LocalDateTime createDate);

}
