package me.sky.recipe.services;

import me.sky.recipe.model.Ingredient;

import java.util.List;

public interface IngredientService {
    void addNewIngredient(Ingredient ingredients);

    List<Ingredient> getAllIngredient();

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    Boolean deleteIngredient(int id);
}

