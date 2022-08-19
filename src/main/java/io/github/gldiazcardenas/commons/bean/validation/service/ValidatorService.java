package io.github.gldiazcardenas.commons.bean.validation.service;

import io.github.gldiazcardenas.commons.bean.validation.Validation;

/**
 * Validation service that allows to check whether the object is valid for any process.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface ValidatorService {

    <T> void validateAndThrow(T object, Class<?> scope) throws ValidatorException;

    <T> Validation validateAndGet(T object, Class<?> scope);

}
