package io.github.gldiazcardenas.commons.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.gldiazcardenas.commons.bean.validation.Validation;

/**
 * Generic validation response in case some validation error happens while processing an endpoint.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponse extends BaseResponse {

    public static final int DEFAULT_STATUS = 400;

    private Validation validation;

    public ValidationResponse() {
        super(DEFAULT_STATUS, null);
    }

    public ValidationResponse(String message) {
        super(DEFAULT_STATUS, message);
    }

    public ValidationResponse(Validation validation) {
        super(DEFAULT_STATUS, null);
        this.validation = validation;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

}
