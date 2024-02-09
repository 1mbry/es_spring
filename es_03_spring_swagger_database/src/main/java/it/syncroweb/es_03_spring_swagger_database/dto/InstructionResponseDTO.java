package it.syncroweb.es_03_spring_swagger_database.dto;

public class InstructionResponseDTO {

    private String language;
    private String text;

    public InstructionResponseDTO(String language, String text) {
        this.language = language;
        this.text = text;
    }

    public InstructionResponseDTO(){

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
