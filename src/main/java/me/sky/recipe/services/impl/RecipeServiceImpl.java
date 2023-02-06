package me.sky.recipe.services.impl;

import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int idRecipe = 0;
    private static Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();

    @Override
    public void addNewRecipe(Recipe recipe) {
        if (recipe != null && !recipeMap.containsValue(recipe)) {
            recipeMap.put(idRecipe++, recipe);
        }
    }

    @Override
    public Recipe getRecipe(int num) {
        if (recipeMap.containsKey(num)) {
            return recipeMap.get(num);
        } else {
            return null;
        }
    }
}
