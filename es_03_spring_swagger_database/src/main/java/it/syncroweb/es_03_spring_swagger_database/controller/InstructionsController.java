package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/instruction")
public class InstructionsController {

    @Autowired
    public InstructionService instructionService;

    @PostMapping("/all")
    public ResponseEntity<List<Instruction>> createAllInstruction(@RequestBody List<Instruction> instructions){
        return instructionService.addAllInstruction(instructions);
    }
}
