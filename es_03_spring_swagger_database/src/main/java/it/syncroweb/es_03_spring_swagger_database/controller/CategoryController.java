package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Category;
import it.syncroweb.es_03_spring_swagger_database.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @PostMapping("/all")
    public ResponseEntity<List<Category>> createAllCategory(@RequestBody List<Category> categories){
        return categoryService.addAllCategory(categories);
    }
}
