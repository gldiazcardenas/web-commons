package io.github.gldiazcardenas.commons.http.client.exception;

import io.github.gldiazcardenas.commons.http.ErrorResponse;
import io.github.gldiazcardenas.commons.util.JsonUtils;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;

/**
 * Error response exception thrown in client when an error response is received.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public final class ErrorResponseException extends ResponseProcessingException {

    private final ErrorResponse errorResponse;

    public ErrorResponseException(ErrorResponse errorResponse) {
        super(Response.status(errorResponse.getStatusCode()).entity(JsonUtils.writeString(errorResponse)).build(), errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}
