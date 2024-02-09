package it.syncroweb.es_03_spring_swagger_database.dto;

import java.util.List;

public class CombinedResponseDTO {
    private List<CategoryResponseDTO> categoryResponseDTOS;
    private List<GlassResponseDTO> glassResponseDTOS;
    private List<LanguageResponseDTO> languageResponseDTOS;
    private List<IngredientResponseDTO> ingredientResponseDTOS;


    public CombinedResponseDTO(List<CategoryResponseDTO> categoryResponseDTOS, List<GlassResponseDTO> glassResponseDTOS, List<LanguageResponseDTO> languageResponseDTOS, List<IngredientResponseDTO> ingredientResponseDTOS) {
        this.categoryResponseDTOS = categoryResponseDTOS;
        this.glassResponseDTOS = glassResponseDTOS;
        this.languageResponseDTOS = languageResponseDTOS;
        this.ingredientResponseDTOS = ingredientResponseDTOS;
    }

    public CombinedResponseDTO(){

    }

    public List<CategoryResponseDTO> getCategory() {
        return categoryResponseDTOS;
    }

    public void setCategory(List<CategoryResponseDTO> categoryResponseDTOS) {
        this.categoryResponseDTOS = categoryResponseDTOS;
    }

    public List<GlassResponseDTO> getGlass() {
        return glassResponseDTOS;
    }

    public void setGlass(List<GlassResponseDTO> glassResponseDTOS) {
        this.glassResponseDTOS = glassResponseDTOS;
    }
    public List<LanguageResponseDTO> getLanguages() {
        return languageResponseDTOS;
    }

    public void setLanguages(List<LanguageResponseDTO> languageResponseDTOS) {
        this.languageResponseDTOS = languageResponseDTOS;
    }

    public List<IngredientResponseDTO> getIngredient() {
        return ingredientResponseDTOS;
    }

    public void setIngredient(List<IngredientResponseDTO> ingredientResponseDTOS) {
        this.ingredientResponseDTOS = ingredientResponseDTOS;
    }


}
