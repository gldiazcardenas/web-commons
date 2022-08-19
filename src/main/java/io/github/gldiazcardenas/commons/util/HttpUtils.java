package io.github.gldiazcardenas.commons.util;

import io.github.gldiazcardenas.commons.http.ErrorResponse;
import io.github.gldiazcardenas.commons.http.ValidationResponse;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

/**
 * Utility class for web applications that allows to handle multiple different purposes.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class HttpUtils {

    private HttpUtils() {
        super();
    }

    public static boolean isValidationResponse(WebApplicationException ex) {
        return toValidationResponse(ex) != null;
    }

    public static boolean isErrorResponse(WebApplicationException ex) {
        return toErrorResponse(ex) != null;
    }

    public static ValidationResponse toValidationResponse(WebApplicationException ex) {
        String body = ex.getResponse().readEntity(String.class);
        return JsonUtils.readString(body, ValidationResponse.class);
    }

    public static ErrorResponse toErrorResponse(WebApplicationException ex) {
        String body = ex.getResponse().readEntity(String.class);
        return JsonUtils.readString(body, ErrorResponse.class);
    }

    public static NotFoundException notFound(Object message) {
        return new NotFoundException(String.valueOf(message));
    }

    public static Supplier<NotFoundException> supplyNotFound(Object message) {
        return () -> notFound(message);
    }

    public static BadRequestException badRequest(Object message) {
        return new BadRequestException(String.valueOf(message));
    }

    public static Supplier<BadRequestException> supplyBadRequest(Object message) {
        return () -> badRequest(message);
    }

    public static NotAuthorizedException notAuthorized(Object message) {
        return new NotAuthorizedException(String.valueOf(message));
    }

    public static Supplier<NotAuthorizedException> supplyNotAuthorized(Object message) {
        return () -> notAuthorized(message);
    }

    public static ClientErrorException conflict(Object message) {
        return new ClientErrorException(String.valueOf(message), Response.status(Response.Status.CONFLICT).build());
    }

    public static Supplier<ClientErrorException> supplyConflict(Object message) {
        return () -> conflict(message);
    }

}
