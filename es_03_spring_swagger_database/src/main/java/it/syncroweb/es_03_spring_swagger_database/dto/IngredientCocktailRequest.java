package it.syncroweb.es_03_spring_swagger_database.dto;

import jakarta.validation.constraints.NotNull;

public class IngredientCocktailRequest {

    @NotNull
    private String name;
    private String measure;

    public IngredientCocktailRequest(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public IngredientCocktailRequest(){

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
