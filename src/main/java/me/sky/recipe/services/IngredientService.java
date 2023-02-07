package me.sky.recipe.services;

import me.sky.recipe.model.Ingredient;

public interface IngredientService {
    public void addNewIngredient(Ingredient ingredients);

    public Ingredient getIngredient(int num);
}
