package io.github.gldiazcardenas.commons.businesslogic;

import io.github.gldiazcardenas.commons.bean.Domain;

import java.io.Serializable;
import java.util.List;

public interface ReadService<D extends Domain<ID>, ID extends Serializable> {

    /**
     * Checks the object identified by the given ID in the persistence layer, and returns it in case it is found.
     *
     * @param id The ID of the object.
     * @return The object associated to the ID in the persistence layer.
     * @throws ObjectNotFoundException When the object is not found in the persistence layer.
     */
    D findById(ID id) throws ObjectNotFoundException;

    /**
     * Gets the object identified by the passed ID, returning a {@code null} value if the object is not found.
     * @param id The ID of the object.
     * @return The object associated to the ID, if not found then {@code null}.
     */
    D getById(ID id);

    /**
     * Gets all objects stored in the persistence layer.
     *
     * @return The list of objects.
     */
    List<D> getAll();

}
