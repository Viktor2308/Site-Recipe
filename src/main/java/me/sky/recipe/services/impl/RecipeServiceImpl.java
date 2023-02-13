package me.sky.recipe.services.impl;

import me.sky.recipe.exeption.ValidationException;
import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.RecipeService;
import me.sky.recipe.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int idRecipe = 0;
    private final Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    private final ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void addNewRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.put(idRecipe++, recipe);
    }



    @Override
    public List<Recipe> getAllRecipe() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }
}
