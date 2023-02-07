package me.sky.recipe.services.impl;

import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int idRecipe = 0;
    private final Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();

    @Override
    public void addNewRecipe(Recipe recipe) {
        recipeMap.put(idRecipe++, recipe);
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public Recipe getRecipe(int num) {
        return recipeMap.getOrDefault(num, null);
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Boolean deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }
}
