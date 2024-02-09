package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.service.AlcoholicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alcoholic")
public class AlcoholicController {

    @Autowired
    public AlcoholicService alcoholicService;

    @PostMapping("/all")
    public ResponseEntity<List<Alcoholic>> createAllAlcoholic(@RequestBody List<Alcoholic> alcoholics){
        return alcoholicService.addAllAlcoholic(alcoholics);
    }

}
