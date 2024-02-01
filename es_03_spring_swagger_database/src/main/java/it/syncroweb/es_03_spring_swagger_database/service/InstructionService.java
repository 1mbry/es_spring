package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.model.Instruction;
import it.syncroweb.es_03_spring_swagger_database.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructionService {

    @Autowired
    public InstructionRepository instructionRepository;

    //Get all instruction
    public ResponseEntity<List<Instruction>> getAllInstruction(){
        try {
            List<Instruction> instructions = new ArrayList<>();
            instructions = instructionRepository.findAll();
            return new ResponseEntity<>(instructions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one instruction
    public ResponseEntity<Instruction> addInstruction(Instruction instruction){
        try{
            Instruction instruction1 = instructionRepository.save(instruction);
            return new ResponseEntity<>(instruction1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
