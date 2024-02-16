package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientResponse {

    private Integer id;
    private String name;

}
