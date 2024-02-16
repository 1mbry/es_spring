package it.syncroweb.es_03_spring_swagger_database.dto;


import lombok.*;

import java.util.List;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkResponse {

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
    private List<IngredientCocktailResponse> ingredients;
    private List<InstructionResponse> instructions;

}
