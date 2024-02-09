package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.CombinedResponseDTO;
import it.syncroweb.es_03_spring_swagger_database.service.CombinedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category-glass-ingredient-language")
public class CombinedController {

    @Autowired
    CombinedService combinedService;

    @GetMapping("")
    public ResponseEntity<CombinedResponseDTO> getAll(){
        return combinedService.getAll();
    }
}
