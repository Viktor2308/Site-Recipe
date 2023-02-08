package me.sky.recipe.services;

import me.sky.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    void addNewRecipe(Recipe recipe);

    List<Recipe> getAllRecipe();

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

}
