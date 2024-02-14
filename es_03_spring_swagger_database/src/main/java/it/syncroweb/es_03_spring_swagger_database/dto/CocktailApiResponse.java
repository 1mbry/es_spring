package it.syncroweb.es_03_spring_swagger_database.dto;

import java.util.List;

public class CocktailApiResponse {
    private List<CocktailResponse> drinks;

    public CocktailApiResponse(List<CocktailResponse> drinks) {
        this.drinks = drinks;
    }
    public CocktailApiResponse(){

    }

    public List<CocktailResponse> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<CocktailResponse> drinks) {
        this.drinks = drinks;
    }
}
