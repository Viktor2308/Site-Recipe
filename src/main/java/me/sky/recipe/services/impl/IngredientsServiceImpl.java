package me.sky.recipe.services.impl;

import me.sky.recipe.model.Ingredients;
import me.sky.recipe.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    private static int idIngredients = 0;
    private static Map<Integer, Ingredients> ingredientsMap = new LinkedHashMap<>();

    @Override
    public void addNewIngredient(Ingredients ingredients) {
        if (ingredients != null && !ingredientsMap.containsValue(ingredients)) {
            ingredientsMap.put(idIngredients++, ingredients);
        }
    }

    @Override
    public Ingredients getIngredients(int num) {
        if (ingredientsMap.containsKey(num)) {
            return ingredientsMap.get(num);
        } else {
            return null;
        }
    }
}
