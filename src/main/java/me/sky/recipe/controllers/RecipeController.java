package me.sky.recipe.controllers;

import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Recipe recipe) {
        recipeService.addNewRecipe(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Recipe>> read() {
        final List<Recipe> recipe = recipeService.getAllRecipe();
        return recipe != null && !recipe.isEmpty()
                ? new ResponseEntity<>(recipe, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> read(@PathVariable(name = "id") int id) {
        final Recipe recipe = recipeService.getRecipe(id);
        return recipe != null
                ? new ResponseEntity<>(recipe, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> create(@PathVariable int id, @RequestBody Recipe recipe) {
        final Recipe newRecipe = recipeService.editRecipe(id, recipe);
        return newRecipe != null
                ? new ResponseEntity<>(newRecipe, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(name = "id") int id) {
        return recipeService.deleteRecipe(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

