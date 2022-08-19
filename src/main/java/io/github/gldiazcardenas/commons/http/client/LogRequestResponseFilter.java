package io.github.gldiazcardenas.commons.http.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;

/**
 * Filter for JAX-RS that allows to put in info in the log about the HTTP calls done.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public class LogRequestResponseFilter implements ClientRequestFilter, ClientResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogRequestResponseFilter.class);

    @Override
    public void filter(ClientRequestContext request) {
        LOGGER.info(request.getMethod() + " request, uri=" + request.getUri());
    }

    @Override
    public void filter(ClientRequestContext request, ClientResponseContext response) {
        String message = response.getStatus() + " " + request.getMethod() + " response, uri=" + request.getUri();
        Response.StatusType statusType = response.getStatusInfo();
        Response.Status.Family family = statusType.getFamily();
        if (family == Response.Status.Family.SUCCESSFUL || family == Response.Status.Family.REDIRECTION) {
            LOGGER.info(message);
        }
        else if (family == Response.Status.Family.CLIENT_ERROR || family == Response.Status.Family.SERVER_ERROR) {
            LOGGER.error(message);
        }
        else {
            LOGGER.warn(message);
        }
    }

}
