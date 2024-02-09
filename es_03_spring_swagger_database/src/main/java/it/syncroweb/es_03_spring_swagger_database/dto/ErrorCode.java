package it.syncroweb.es_03_spring_swagger_database.dto;

public enum ErrorCode {
    UA("Unauthorized"),
    ISE, //INTERNAL SERVER ERROR,
    IB, //INVALID BODY
    IH, //INVALID HEADER
    FE, //FEIGN EXCEPTION
    RNF("The URL you have reached is not in service at this time");

    private String message;

    public String getMessage(){
        return this.message;
    }

    private ErrorCode() {}

    private ErrorCode(String message){
        this.message = message;
    }
}
