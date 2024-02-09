package it.syncroweb.es_03_spring_swagger_database.dto;


import java.util.List;

public class DrinkResponseDTO {

    private Integer id;
    private String name;
    private String alternate_name;
    private boolean alcoholic;
    private String glass;
    private String category;
    private String url_thumb;
    private String image_attribution;
    private String image_source;
    private String video;
    private String tags;
    private String iba;
    private String creative_commons;
    private List<IngredientCocktailDTO> ingredientCocktailDTOS;
    private List<InstructionResponseDTO> instructionResponseDTOS;

    public DrinkResponseDTO(Integer id, String name, String alternate_name, boolean alcoholic, String glass, String category, String url_thumb, String image_attribution, String image_source, String video, String tags, String iba, String creative_commons, List<IngredientCocktailDTO> ingredientCocktailDTOS, List<InstructionResponseDTO> instructionResponseDTOS) {
        this.id = id;
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
        this.ingredientCocktailDTOS = ingredientCocktailDTOS;
        this.instructionResponseDTOS = instructionResponseDTOS;
    }

    public DrinkResponseDTO(){

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

    public List<IngredientCocktailDTO> getIngredients() {
        return ingredientCocktailDTOS;
    }

    public void setIngredients(List<IngredientCocktailDTO> ingredientCocktailDTOS) {
        this.ingredientCocktailDTOS = ingredientCocktailDTOS;
    }

    public List<InstructionResponseDTO> getInstructions() {
        return instructionResponseDTOS;
    }

    public void setInstructions(List<InstructionResponseDTO> instructionResponseDTOS) {
        this.instructionResponseDTOS = instructionResponseDTOS;
    }
}
