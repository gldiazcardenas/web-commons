package io.github.gldiazcardenas.commons.http;

/**
 * Base response object.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class BaseResponse {

    private Integer statusCode;
    private String message;
    private String path;

    BaseResponse() {
        super();
    }

    BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
