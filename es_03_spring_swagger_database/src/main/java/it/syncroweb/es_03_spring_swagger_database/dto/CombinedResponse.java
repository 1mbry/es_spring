package it.syncroweb.es_03_spring_swagger_database.dto;

import java.util.List;

public class CombinedResponse {
    private List<CategoryResponse> categoryResponses;
    private List<GlassResponse> glassResponses;
    private List<LanguageResponse> languageResponses;
    private List<IngredientResponse> ingredientResponses;


    public CombinedResponse(List<CategoryResponse> categoryResponses, List<GlassResponse> glassResponses, List<LanguageResponse> languageResponses, List<IngredientResponse> ingredientResponses) {
        this.categoryResponses = categoryResponses;
        this.glassResponses = glassResponses;
        this.languageResponses = languageResponses;
        this.ingredientResponses = ingredientResponses;
    }

    public CombinedResponse(){

    }

    public List<CategoryResponse> getCategory() {
        return categoryResponses;
    }

    public void setCategory(List<CategoryResponse> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }

    public List<GlassResponse> getGlass() {
        return glassResponses;
    }

    public void setGlass(List<GlassResponse> glassResponses) {
        this.glassResponses = glassResponses;
    }
    public List<LanguageResponse> getLanguages() {
        return languageResponses;
    }

    public void setLanguages(List<LanguageResponse> languageResponses) {
        this.languageResponses = languageResponses;
    }

    public List<IngredientResponse> getIngredient() {
        return ingredientResponses;
    }

    public void setIngredient(List<IngredientResponse> ingredientResponses) {
        this.ingredientResponses = ingredientResponses;
    }


}
