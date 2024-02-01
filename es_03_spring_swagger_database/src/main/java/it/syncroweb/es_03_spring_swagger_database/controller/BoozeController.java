package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Booze;
import it.syncroweb.es_03_spring_swagger_database.service.BoozeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booze")
public class BoozeController {

    @Autowired
    public BoozeService boozeService;

    @GetMapping("/all")
    public ResponseEntity<List<Booze>> getAllBooze(){
        return boozeService.getAllBooze();
    }

    @PostMapping("")
    public ResponseEntity<Booze> createBooze(@RequestBody Booze booze){
        return boozeService.addBooze(booze);
    }
}
