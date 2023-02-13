package me.sky.recipe.services.impl;

import me.sky.recipe.exeption.ValidationException;
import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import me.sky.recipe.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int idIngredients = 0;
    private final Map<Integer, Ingredient> ingredientsMap = new LinkedHashMap<>();
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void addNewIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientsMap.put(idIngredients++, ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(ingredientsMap.values());
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredientsMap.get(id);
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
    public boolean deleteIngredient(int id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            return true;
        }
        return false;
    }
}
