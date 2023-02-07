package me.sky.recipe.services.impl;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(ingredientsMap.values());
    }

    @Override
    public Ingredient getIngredient(int num) {
        return ingredientsMap.getOrDefault(num, null);
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Boolean deleteIngredient(int id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            return true;
        }
        return false;
    }
}
