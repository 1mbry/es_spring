package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.service.AlcoholicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alcoholic")
public class AlcoholicController {

    @Autowired
    public AlcoholicService alcoholicService;

    @GetMapping("/all")
    public ResponseEntity<List<Alcoholic>> getAllAlcoholic(){
        return alcoholicService.getAllAlcoholic();
    }
    @PostMapping("")
    public ResponseEntity<Alcoholic> createAlcoholic(@RequestBody Alcoholic alcoholic){
        return alcoholicService.addAlcoholic(alcoholic);
    }

}
