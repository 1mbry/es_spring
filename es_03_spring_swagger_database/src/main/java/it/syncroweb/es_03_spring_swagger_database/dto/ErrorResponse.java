package it.syncroweb.es_03_spring_swagger_database.dto;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse extends Response {
    private Map<String, String> errors;

    public ErrorResponse(String message) {
        super(message);
        errors = new HashMap<String, String>();
    }

    public ErrorResponse(ErrorCode errorCode) {
        super(errorCode.getMessage());
        errors = new HashMap<String, String>();
    }

    public ErrorResponse(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
