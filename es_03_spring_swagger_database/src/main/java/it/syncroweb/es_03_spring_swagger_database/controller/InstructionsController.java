package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.service.IngredientService;
import it.syncroweb.es_03_spring_swagger_database.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instruction")
public class InstructionsController {

    @Autowired
    public InstructionService instructionService;

    @GetMapping("/all")
    public ResponseEntity<List<Instruction>> getAllInstruction(){
        return instructionService.getAllInstruction();
    }

    @PostMapping("")
    public ResponseEntity<Instruction> createInstruction(@RequestBody Instruction instruction){
        return instructionService.addInstruction(instruction);
    }
}
