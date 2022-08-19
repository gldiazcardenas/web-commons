package io.github.gldiazcardenas.commons.http.server;

import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Filter used to clean up the MDC logging params after processing every request.
 *
 * @author Gabriel Diaz, 19/12/2020.
 */
public final class Slf4jMDCClearFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        }
        finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Do nothing
    }

}
