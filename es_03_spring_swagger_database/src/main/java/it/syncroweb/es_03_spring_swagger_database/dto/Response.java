package it.syncroweb.es_03_spring_swagger_database.dto;

public class Response {
    private String message;

    public Response() {
        super();
    }

    public Response(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
