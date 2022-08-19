package io.github.gldiazcardenas.commons.businesslogic.impl;

import io.github.gldiazcardenas.commons.bean.Domain;
import io.github.gldiazcardenas.commons.bean.Entity;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterException;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorException;
import io.github.gldiazcardenas.commons.businesslogic.DeleteService;
import io.github.gldiazcardenas.commons.businesslogic.ObjectNotFoundException;
import io.github.gldiazcardenas.commons.data.Repository;

import java.io.Serializable;

public abstract class AbstractDeleteService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements DeleteService<D, ID> {

    @Override
    public void delete(ID id) throws ValidatorException, ObjectNotFoundException {
        E entity = getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        D domain = convertToDomain(entity);
        validateAndThrow(domain);
        getRepository().deleteById(id);
    }

    /**
     * @return The repository object providing persistent storage.
     */
    protected abstract Repository<E, ID> getRepository();

    /**
     * @param domainBean Performs some business rule validations before creating the object.
     * @throws ValidatorException In case any business rule is broken.
     */
    protected abstract void validateAndThrow(D domainBean) throws ValidatorException;

    /**
     * Converts the entity to domain bean.
     * @param entity The entity to be converted.
     * @return The domain result of the conversion.
     * @throws ConverterException In case the conversion cannot happen.
     */
    protected abstract D convertToDomain(E entity) throws ConverterException;

}
