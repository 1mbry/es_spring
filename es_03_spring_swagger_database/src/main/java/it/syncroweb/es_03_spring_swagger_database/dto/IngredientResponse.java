package it.syncroweb.es_03_spring_swagger_database.dto;

public class IngredientResponse {

    private Integer id;
    private String name;

    public IngredientResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public IngredientResponse(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
