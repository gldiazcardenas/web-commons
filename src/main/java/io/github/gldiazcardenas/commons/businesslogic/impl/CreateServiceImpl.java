package io.github.gldiazcardenas.commons.businesslogic.impl;

import io.github.gldiazcardenas.commons.bean.Domain;
import io.github.gldiazcardenas.commons.bean.Entity;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterService;
import io.github.gldiazcardenas.commons.bean.validation.scopes.Create;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorService;
import io.github.gldiazcardenas.commons.data.Repository;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;

import java.io.Serializable;

public class CreateServiceImpl<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        extends AbstractCreateService<D, E, ID> {

    private final Repository<E, ID> repository;
    private final ConverterService converterService;
    private final ValidatorService validatorService;
    private final Class<E> entityClass;

    public CreateServiceImpl(Repository<E, ID> repository,
                             ConverterService converterService,
                             ValidatorService validatorService) {

        this.repository = repository;
        this.converterService = converterService;
        this.validatorService = validatorService;
        this.entityClass = ReflectionUtils.getGenericClassType(CreateServiceImpl.class, 2);
    }

    @Override
    protected Repository<E, ID> getRepository() {
        return repository;
    }

    protected ConverterService getConverterService() {
        return converterService;
    }

    protected ValidatorService getValidatorService() {
        return validatorService;
    }

    @Override
    protected void validateAndThrow(D domainBean) {
        validatorService.validateAndThrow(domainBean, Create.class);
    }

    @Override
    protected E convertToEntity(D domainBean) {
        return converterService.convertTo(domainBean, entityClass);
    }

}
