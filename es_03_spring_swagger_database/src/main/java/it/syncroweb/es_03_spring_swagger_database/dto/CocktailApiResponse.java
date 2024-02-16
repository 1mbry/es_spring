package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CocktailApiResponse {
    private List<CocktailResponse> drinks;

}
