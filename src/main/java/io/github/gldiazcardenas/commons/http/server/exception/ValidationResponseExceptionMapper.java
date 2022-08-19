package io.github.gldiazcardenas.commons.http.server.exception;

import io.github.gldiazcardenas.commons.http.ValidationResponse;
import io.github.gldiazcardenas.commons.http.client.exception.ValidationResponseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Exception mapper for {@link ValidationResponseException}.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public class ValidationResponseExceptionMapper implements ExceptionMapper<ValidationResponseException> {

    @Override
    public Response toResponse(ValidationResponseException e) {
        ValidationResponse response = e.getValidationResponse();
        return Response.status(response.getStatusCode()).entity(response).build();
    }

}
