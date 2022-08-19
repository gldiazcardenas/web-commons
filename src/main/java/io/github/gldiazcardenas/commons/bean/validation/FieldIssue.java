package io.github.gldiazcardenas.commons.bean.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Validation that refers to errors/warnings on fields of objects validated.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldIssue extends ValidationIssue {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return getType() + ": " + getField() + " => " + getMessage();
    }

    public static FieldIssue error(String field, String message) {
        FieldIssue result = new FieldIssue();
        result.setType(Type.ERROR);
        result.setField(field);
        result.setMessage(message);
        return result;
    }

    public static FieldIssue warning(String field, String message) {
        FieldIssue result = new FieldIssue();
        result.setType(Type.WARNING);
        result.setField(field);
        result.setMessage(message);
        return result;
    }

}
