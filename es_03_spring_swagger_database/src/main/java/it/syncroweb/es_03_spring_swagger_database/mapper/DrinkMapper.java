package it.syncroweb.es_03_spring_swagger_database.mapper;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.LanguageResponse;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DrinkMapper {

    DrinkMapper INSTANCE = Mappers.getMapper(DrinkMapper.class);

    @Mapping(source = "name", target = "language")
    LanguageResponse toLanguageResponse(Language language);

    @Mapping(target = "alcoholic", expression = "java(drink.getAlcoholic().getType())")
    @Mapping(target = "glass", expression = "java(drink.getGlass().getName())")
    @Mapping(target = "category", expression = "java(drink.getCategory().getName())")
    @Mapping(target = "instructions")
    DrinkResponse toDrinkResponse(Drink drink);
    @Mapping(target = "language", expression = "java(instruction.getLanguage().getName())")
    InstructionResponse toInstructionResponse(Instruction instruction);
    List<InstructionResponse> toInstructionResponseList(List<Instruction> instructions);
}
