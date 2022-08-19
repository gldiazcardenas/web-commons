package io.github.gldiazcardenas.commons.businesslogic.impl;

import io.github.gldiazcardenas.commons.bean.Domain;
import io.github.gldiazcardenas.commons.bean.Entity;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterException;
import io.github.gldiazcardenas.commons.bean.mapping.service.ConverterService;
import io.github.gldiazcardenas.commons.data.Repository;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;

import java.io.Serializable;

public class ReadServiceImpl<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        extends AbstractReadService<D, E, ID> {

    private final Repository<E, ID> repository;
    private final ConverterService converterService;
    private final Class<D> domainClass;

    public ReadServiceImpl(Repository<E, ID> repository,
                           ConverterService converterService) {
        this.repository = repository;
        this.converterService = converterService;
        this.domainClass = ReflectionUtils.getGenericClassType(ReadServiceImpl.class, 1);
    }

    @Override
    protected Repository<E, ID> getRepository() {
        return repository;
    }

    @Override
    protected D convertToDomain(E entity) throws ConverterException {
        return converterService.convertTo(entity, domainClass);
    }

}
