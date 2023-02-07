package me.sky.recipe.controllers;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientsService) {
        this.ingredientService = ingredientsService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Ingredient>> read() {
        final List<Ingredient> ingredient = ingredientService.getAllIngredient();
        return ingredient != null && !ingredient.isEmpty()
                ? new ResponseEntity<>(ingredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addNewIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        final Ingredient newIngredient = ingredientService.editIngredient(id, ingredient);
        return newIngredient != null
                ? new ResponseEntity<>(newIngredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable(name = "id") int id) {
        final Ingredient ingredient = ingredientService.getIngredient(id);
        return ingredient != null
                ? new ResponseEntity<>(ingredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(name = "id") int id) {
        return ingredientService.deleteIngredient(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
