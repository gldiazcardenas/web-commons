package io.github.gldiazcardenas.commons.http.client;

import io.github.gldiazcardenas.commons.http.ErrorResponse;
import io.github.gldiazcardenas.commons.http.ValidationResponse;
import io.github.gldiazcardenas.commons.http.client.exception.ErrorResponseException;
import io.github.gldiazcardenas.commons.http.client.exception.ValidationResponseException;
import io.github.gldiazcardenas.commons.util.IOUtils;
import io.github.gldiazcardenas.commons.util.JsonUtils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;

/**
 * Filter that parses the error and throws exceptions in case it is needed.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public class ClientErrorResponseFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext request, ClientResponseContext response) {
        Response.StatusType statusType = response.getStatusInfo();
        Response.Status.Family family = statusType.getFamily();

        if (family == Response.Status.Family.CLIENT_ERROR || family == Response.Status.Family.SERVER_ERROR) {
            if (response.hasEntity()) {
                String body = IOUtils.getStringSilent(response.getEntityStream());

                if (statusType.getStatusCode() == Response.Status.BAD_REQUEST.getStatusCode()) {
                    ValidationResponse validationResponse = JsonUtils.readString(body, ValidationResponse.class);
                    if (validationResponse != null) {
                        throw new ValidationResponseException(validationResponse);
                    }
                }

                ErrorResponse errorResponse = JsonUtils.readString(body, ErrorResponse.class);
                if (errorResponse != null) {
                    throw new ErrorResponseException(errorResponse);
                }
            }

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatusCode(statusType.getStatusCode());
            errorResponse.setMessage(statusType.getReasonPhrase());
            errorResponse.setPath(request.getUri().toString());

            throw new ErrorResponseException(errorResponse);
        }
    }

}
