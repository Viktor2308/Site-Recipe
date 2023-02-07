package me.sky.recipe.services;

import me.sky.recipe.model.Recipe;

public interface RecipeService {
    public void addNewRecipe(Recipe recipe);

    public Recipe getRecipe(int num);
}
