package io.github.gldiazcardenas.commons.http.client.exception;

import io.github.gldiazcardenas.commons.http.ValidationResponse;
import io.github.gldiazcardenas.commons.util.JsonUtils;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;

/**
 * Validation response exception thrown when a validation response is received.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public final class ValidationResponseException extends ResponseProcessingException {

    private final ValidationResponse validationResponse;

    public ValidationResponseException(ValidationResponse response) {
        super(Response.status(response.getStatusCode()).entity(JsonUtils.writeString(response)).build(), response.getMessage());
        this.validationResponse = response;
    }

    public ValidationResponse getValidationResponse() {
        return validationResponse;
    }

}
