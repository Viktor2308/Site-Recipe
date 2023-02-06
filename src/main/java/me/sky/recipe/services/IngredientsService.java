package me.sky.recipe.services;

import me.sky.recipe.model.Ingredients;

public interface IngredientsService {
    public void addNewIngredient(Ingredients ingredients);

    public Ingredients getIngredients(int num);
}
