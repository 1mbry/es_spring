package it.syncroweb.es_03_spring_swagger_database.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkRequest {

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
    @NotEmpty
    private  List<InstructionRequest> instructions;
    @NotEmpty
    private  List<IngredientCocktailRequest> ingredients;
}
