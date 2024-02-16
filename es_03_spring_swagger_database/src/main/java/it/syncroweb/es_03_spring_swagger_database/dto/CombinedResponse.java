package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CombinedResponse {
    private List<CategoryResponse> category;
    private List<GlassResponse> glass;
    private List<LanguageResponse> languages;
    private List<IngredientResponse> ingredient;

}
