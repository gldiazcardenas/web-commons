package io.github.gldiazcardenas.commons.businesslogic;

import io.github.gldiazcardenas.commons.bean.Domain;

import java.io.Serializable;

public interface UpdateService<D extends Domain<ID>, ID extends Serializable> {

    /**
     * Updates the object represented by the passed ID with the passed state.
     *
     * @param id The ID of the object to update.
     * @param domainBean The new state of the object.
     * @throws ObjectNotFoundException When the object is not found in the persistence layer.
     */
    void update(ID id, D domainBean) throws ObjectNotFoundException;

}
