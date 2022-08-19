package io.github.gldiazcardenas.commons.data;

import java.util.Optional;

/**
 * Definition of a data repository that can persist objects permanently.
 *
 * @param <E> Type of persistent object.
 * @param <ID> Type of identifier of the object.
 *
 * @author Gabriel Diaz, 22/12/2020.
 */
public interface Repository<E, ID> {

    ID insert(E object);

    void update(ID id, E object);

    void deleteById(ID id);

    Optional<E> findById(ID id);

    Iterable<E> findAll();

}
