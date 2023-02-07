package me.sky.recipe.controllers;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientsService) {
        this.ingredientService = ingredientsService;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Ingredient ingredient) {
        ingredientService.addNewIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> read(@PathVariable(name = "id") int id) {
        final Ingredient ingredient = ingredientService.getIngredient(id);
        return ingredient != null
                ? new ResponseEntity<>(ingredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
