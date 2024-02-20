package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.InstructionResponse;
import it.syncroweb.es_03_spring_swagger_database.mapper.InstructionMapper;
import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drink")
public class InstructionController {

    @Autowired
    private InstructionRepository instructionRepository;

    @GetMapping("/one")
    public ResponseEntity<List<InstructionResponse>> getOne(){
        List<Instruction> instructions = instructionRepository.findAll();
        return new ResponseEntity<>(InstructionMapper.INSTANCE.toInstructionResponseList(instructions), HttpStatus.OK);
    }
    /*public DrinkResponse getDrinkById(Integer drinkId) {
        // Retrieve the drink entity from the repository by ID
        Drink drink = drinkRepository.getReferenceById(drinkId);

        // Map the drink entity to a DrinkResponse DTO using the mapper
        DrinkResponse drinkResponse = DrinkMapper.INSTANCE.toDrinkResponse(drink);

        return drinkResponse;
    }*/
}
