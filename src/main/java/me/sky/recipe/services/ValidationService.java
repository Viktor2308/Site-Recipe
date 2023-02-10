package me.sky.recipe.services;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.model.Recipe;

/**
 * Validation service
 */
public interface ValidationService {
    boolean validate(Recipe recipe);

    boolean validate(Ingredient ingredient);
}
