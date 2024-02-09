package it.syncroweb.es_03_spring_swagger_database.dto;

import jakarta.validation.constraints.NotNull;

public class IngredientCocktailRequestDTO {

    private @NotNull String name;
    private String measure;

    public IngredientCocktailRequestDTO(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public IngredientCocktailRequestDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
