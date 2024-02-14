package it.syncroweb.es_03_spring_swagger_database.dto;

import jakarta.validation.constraints.NotNull;

public class InstructionRequest {

    @NotNull
    private String language;
    private String text;

    public InstructionRequest(String language, String text) {
        this.language = language;
        this.text = text;
    }

    public InstructionRequest(){

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
