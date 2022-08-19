package io.github.gldiazcardenas.commons.businesslogic;

import io.github.gldiazcardenas.commons.bean.Domain;

import java.io.Serializable;

public interface MergeService<D extends Domain<ID>, ID extends Serializable> {

    /**
     * Merges both, the current state and new passed state of an object identified by the passed ID.  The result of this
     * operation will be the union between current state and non-null values passed in the object.
     *
     * @param id The ID of the object to update.
     * @param domainBean The new state of the object.
     * @throws ObjectNotFoundException When the object is not found in the persistence layer.
     */
    void merge(ID id, D domainBean) throws ObjectNotFoundException;

}
