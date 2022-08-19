package io.github.gldiazcardenas.commons.businesslogic;

import io.github.gldiazcardenas.commons.bean.Domain;

import java.io.Serializable;

public interface DeleteService<D extends Domain<ID>, ID extends Serializable> {

    /**
     * Deletes the object from the persistence layer identified by the given ID.
     * @param id The ID of the object to be deleted.
     * @throws ObjectNotFoundException When the object is not found in the persistence layer.
     */
    void delete(ID id) throws ObjectNotFoundException;

}
