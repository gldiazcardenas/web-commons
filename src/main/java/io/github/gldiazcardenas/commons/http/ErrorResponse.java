package io.github.gldiazcardenas.commons.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.gldiazcardenas.commons.util.ExceptionUtils;

import java.time.Instant;

/**
 * Error response object that should be returned by all HTTP web service in case of any error happens.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

    public static final int DEFAULT_STATUS = 500;

    private Instant timestamp;
    private String trace;

    public ErrorResponse() {
        super(DEFAULT_STATUS, null);
        this.timestamp = Instant.now();
    }

    public ErrorResponse(int status, String message) {
        super(status, message);
        this.timestamp = Instant.now();
    }

    public ErrorResponse(int status, Throwable throwable) {
        super(status, throwable.getLocalizedMessage());
        this.trace = ExceptionUtils.getStackTrace(throwable);
        this.timestamp = Instant.now();
    }

    public ErrorResponse(int status, String message, Throwable throwable) {
        super(status, message);
        this.trace = ExceptionUtils.getStackTrace(throwable);
        this.timestamp = Instant.now();
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
