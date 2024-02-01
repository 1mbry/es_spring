package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.service.GlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/glass")
public class GlassController {

    @Autowired
    public GlassService glassService;

    @GetMapping("/all")
    public ResponseEntity<List<Glass>> getAllGlass(){
        return glassService.getAllGlass();
    }

    @PostMapping("")
    public ResponseEntity<Glass> createGlass(@RequestBody Glass glass){
        return glassService.addGlass(glass);
    }
}
