package it.syncroweb.es_03_spring_swagger_database.dto;

public class LanguageResponseDTO {

    private Integer id;
    private String language;

    public LanguageResponseDTO(Integer id, String language) {
        this.id = id;
        this.language = language;
    }
    public LanguageResponseDTO(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
