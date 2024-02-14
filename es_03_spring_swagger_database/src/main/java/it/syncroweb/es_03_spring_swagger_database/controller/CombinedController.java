package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.CombinedResponse;
import it.syncroweb.es_03_spring_swagger_database.service.CombinedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public CombinedService combinedService;

    @GetMapping("")
    public ResponseEntity<CombinedResponse> getAll()  {
        CombinedResponse combinedResponse = combinedService.getAll();
        return new ResponseEntity<>(combinedResponse, HttpStatus.OK);
    }
}
