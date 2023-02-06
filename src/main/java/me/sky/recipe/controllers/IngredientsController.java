package me.sky.recipe.controllers;

import me.sky.recipe.model.Ingredients;
import me.sky.recipe.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientsController {
    private IngredientsService ingredientsService;

    public IngredientsController (IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping(value = "/ingredient")
    public ResponseEntity<?> create(@RequestBody Ingredients ingredient) {
        ingredientsService.addNewIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredients> read(@PathVariable(name = "id") int id) {
        final Ingredients ingredient = ingredientsService.getIngredients(id);
        return ingredient != null
                ? new ResponseEntity<>(ingredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
