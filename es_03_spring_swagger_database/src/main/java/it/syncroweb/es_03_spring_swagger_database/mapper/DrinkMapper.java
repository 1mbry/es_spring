package it.syncroweb.es_03_spring_swagger_database.mapper;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DrinkMapper {
    DrinkMapper INSTANCE = Mappers.getMapper(DrinkMapper.class);

    /*@Mapping(source = "alcoholic", target = "alcoholic")
    @Mapping(source = "glass", target = "glass")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "ingredientCocktails", target = "ingredients")
    @Mapping(source = "instructions", target = "instructions")
    DrinkResponse toDrinkResponse(Drink drink);*/

}
