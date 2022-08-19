package io.github.gldiazcardenas.commons.businesslogic.impl;

import io.github.gldiazcardenas.commons.bean.Domain;
import io.github.gldiazcardenas.commons.bean.Entity;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterException;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterService;
import io.github.gldiazcardenas.commons.bean.mapping.service.MergerException;
import io.github.gldiazcardenas.commons.bean.mapping.service.MergerService;
import io.github.gldiazcardenas.commons.bean.validation.scopes.Update;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorException;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorService;
import io.github.gldiazcardenas.commons.data.Repository;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;

import java.io.Serializable;

public class MergeServiceImpl<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        extends AbstractMergeService<D, E, ID> {

    private final Repository<E, ID> repository;
    private final ConverterService converterService;
    private final ValidatorService validatorService;
    private final MergerService mergerService;
    private final Class<D> domainClass;
    private final Class<E> entityClass;

    public MergeServiceImpl(Repository<E, ID> repository,
                            ConverterService converterService,
                            ValidatorService validatorService,
                            MergerService mergerService) {
        this.repository = repository;
        this.converterService = converterService;
        this.validatorService = validatorService;
        this.mergerService = mergerService;
        this.domainClass = ReflectionUtils.getGenericClassType(MergeServiceImpl.class, 1);
        this.entityClass = ReflectionUtils.getGenericClassType(MergeServiceImpl.class, 2);
    }

    @Override
    protected Repository<E, ID> getRepository() {
        return repository;
    }

    @Override
    protected void validateAndThrow(D domainBean) throws ValidatorException {
        validatorService.validateAndThrow(domainBean, Update.class);
    }

    @Override
    protected D convertToDomain(E entity) throws ConverterException {
        return converterService.convertTo(entity, domainClass);
    }

    @Override
    protected E convertToEntity(D domainBean) throws ConverterException {
        return converterService.convertTo(domainBean, entityClass);
    }

    @Override
    protected void merge(D currentDomain, D newDomain) throws MergerException {
        mergerService.merge(currentDomain, newDomain);
    }

}
