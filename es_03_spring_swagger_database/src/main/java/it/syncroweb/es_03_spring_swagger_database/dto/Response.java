package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private String message;

    public Response() {
        super();
    }

    public Response(String message) {
        super();
        this.message = message;
    }

}
