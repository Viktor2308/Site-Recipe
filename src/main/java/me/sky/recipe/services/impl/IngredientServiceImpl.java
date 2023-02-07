package me.sky.recipe.services.impl;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int idIngredients = 0;
    private final Map<Integer, Ingredient> ingredientsMap = new LinkedHashMap<>();

    @Override
    public void addNewIngredient(Ingredient ingredient) {
        ingredientsMap.put(idIngredients++, ingredient);
    }

    @Override
    public Ingredient getIngredient(int num) {
        if (ingredientsMap.containsKey(num)) {
            return ingredientsMap.get(num);
        } else {
            return null;
        }
    }
}
