package me.sky.recipe.services;

import me.sky.recipe.model.Ingredient;

import java.util.List;

/**
 * service for work with ingredients
 */
public interface IngredientService {
    void addNewIngredient(Ingredient ingredients);

    List<Ingredient> getAllIngredient();

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);
}

