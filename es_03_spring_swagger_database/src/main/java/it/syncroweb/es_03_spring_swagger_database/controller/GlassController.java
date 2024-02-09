package it.syncroweb.es_03_spring_swagger_database.controller;


import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.service.GlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/glass")
public class GlassController {

    @Autowired
    public GlassService glassService;

    @PostMapping("/all")
    public ResponseEntity<List<Glass>> createAllGlass(@RequestBody List<Glass> glasses){
        return glassService.addAllGlasses(glasses);
    }
}
