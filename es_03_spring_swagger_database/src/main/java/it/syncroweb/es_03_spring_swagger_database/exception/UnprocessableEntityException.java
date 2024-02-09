package it.syncroweb.es_03_spring_swagger_database.exception;

public class UnprocessableEntityException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(String errorMessage) {
        super(errorMessage);
    }
}
