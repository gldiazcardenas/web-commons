package io.github.gldiazcardenas.commons.bean.validation.service;

import io.github.gldiazcardenas.commons.bean.validation.Validation;

/**
 * Exception thrown when a validation error occurs.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ValidatorException extends RuntimeException {

    private final Validation validation;

    public ValidatorException(Validation validation) {
        super(validation.toString());
        this.validation = validation;
    }

    public Validation getValidation() {
        return validation;
    }

}
