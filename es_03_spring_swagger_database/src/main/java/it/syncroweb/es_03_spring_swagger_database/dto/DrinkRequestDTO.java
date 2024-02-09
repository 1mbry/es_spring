package it.syncroweb.es_03_spring_swagger_database.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DrinkRequestDTO {

    @NotNull
    private String name;
    private String alternate_name;
    @NotNull
    private boolean alcoholic;
    @NotNull
    private String glass;
    @NotNull
    private String category;
    private String url_thumb;
    private String image_attribution;
    private String image_source;
    private String video;
    private String tags;
    private String iba;
    private String creative_commons;
    @NotNull
    private  List<InstructionRequestDTO> instructionRequestDTOS;
    @NotNull
    private  List<IngredientCocktailRequestDTO> ingredientCocktailRequestDTOS;

    public DrinkRequestDTO(String name, String alternate_name, boolean alcoholic, String glass, String category, String url_thumb, String image_attribution, String image_source, String video, String tags, String iba, String creative_commons, List<InstructionRequestDTO> instructionRequestDTOS, List<IngredientCocktailRequestDTO> ingredientCocktailRequestDTOS) {
        this.name = name;
        this.alternate_name = alternate_name;
        this.alcoholic = alcoholic;
        this.glass = glass;
        this.category = category;
        this.url_thumb = url_thumb;
        this.image_attribution = image_attribution;
        this.image_source = image_source;
        this.video = video;
        this.tags = tags;
        this.iba = iba;
        this.creative_commons = creative_commons;
        this.instructionRequestDTOS = instructionRequestDTOS;
        this.ingredientCocktailRequestDTOS = ingredientCocktailRequestDTOS;
    }

    public DrinkRequestDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternate_name() {
        return alternate_name;
    }

    public void setAlternate_name(String alternate_name) {
        this.alternate_name = alternate_name;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl_thumb() {
        return url_thumb;
    }

    public void setUrl_thumb(String url_thumb) {
        this.url_thumb = url_thumb;
    }

    public String getImage_attribution() {
        return image_attribution;
    }

    public void setImage_attribution(String image_attribution) {
        this.image_attribution = image_attribution;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIba() {
        return iba;
    }

    public void setIba(String iba) {
        this.iba = iba;
    }

    public String getCreative_commons() {
        return creative_commons;
    }

    public void setCreative_commons(String creative_commons) {
        this.creative_commons = creative_commons;
    }

    public List<InstructionRequestDTO> getInstruction() {
        return instructionRequestDTOS;
    }

    public void setInstruction(List<InstructionRequestDTO> instructionRequestDTOS) {
        this.instructionRequestDTOS = instructionRequestDTOS;
    }

    public List<IngredientCocktailRequestDTO> getIngredients() {
        return ingredientCocktailRequestDTOS;
    }

    public void setIngredients(List<IngredientCocktailRequestDTO> ingredientCocktailRequestDTOS) {
        this.ingredientCocktailRequestDTOS = ingredientCocktailRequestDTOS;
    }
}
