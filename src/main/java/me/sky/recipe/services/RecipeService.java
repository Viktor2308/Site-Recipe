package me.sky.recipe.services;

import me.sky.recipe.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
/**
 * service for work with recipe
 */
public interface RecipeService {
    void addNewRecipe(Recipe recipe);

    List<Recipe> getAllRecipe();

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    Path createAllRecipeReport() throws IOException;
}
