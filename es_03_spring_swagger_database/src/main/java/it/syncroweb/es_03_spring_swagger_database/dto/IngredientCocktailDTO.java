package it.syncroweb.es_03_spring_swagger_database.dto;

public class IngredientCocktailDTO {

    private String name;
    private String measure;

    public IngredientCocktailDTO() {
        this.name = name;
        this.measure = measure;
    }

    public IngredientCocktailDTO(String name, String measure){

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
