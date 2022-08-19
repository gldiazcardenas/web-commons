package io.github.gldiazcardenas.commons.businesslogic;

import io.github.gldiazcardenas.commons.bean.Domain;

import java.io.Serializable;

public interface ServiceFacade<D extends Domain<ID>, ID extends Serializable> extends
        CreateService<D, ID>,
        ReadService<D, ID>,
        UpdateService<D, ID>,
        DeleteService<D, ID>,
        MergeService<D, ID>  {
}
