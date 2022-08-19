package io.github.gldiazcardenas.commons.http.server.exception;

import io.github.gldiazcardenas.commons.bean.mapping.service.MergerException;
import io.github.gldiazcardenas.commons.http.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Mapper to convert {@link MergerException} to a proper response object.
 *
 * @author Gabriel Diaz, 22/12/2020.
 */
public class MergerExceptionMapper implements ExceptionMapper<MergerException> {

    @Override
    public Response toResponse(MergerException e) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }

}
