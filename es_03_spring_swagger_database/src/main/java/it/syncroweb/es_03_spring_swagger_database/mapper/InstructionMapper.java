package it.syncroweb.es_03_spring_swagger_database.mapper;

import it.syncroweb.es_03_spring_swagger_database.dto.InstructionResponse;
import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InstructionMapper {

    InstructionMapper INSTANCE = Mappers.getMapper(InstructionMapper.class);

    @Mapping(target = "language", expression = "java(instruction.getLanguage().getName())")
    InstructionResponse toInstructionResponse(Instruction instruction);

    List<InstructionResponse> toInstructionResponseList(List<Instruction> instructions);
}
