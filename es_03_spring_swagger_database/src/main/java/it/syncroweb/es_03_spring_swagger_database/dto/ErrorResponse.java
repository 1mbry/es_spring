package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
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

}
