package it.syncroweb.es_03_spring_swagger_database.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private Integer id;
    private String name;

}
