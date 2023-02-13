package me.sky.recipe.services.impl;

import me.sky.recipe.model.Ingredient;
import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && !StringUtils.isEmpty(recipe.getName())
                && recipe.getCookingTimeMin() > 0
                && recipe.getIngredientsList() != null
                && recipe.getCookingInstructionsList() != null
                && !recipe.getIngredientsList().isEmpty()
                && !recipe.getCookingInstructionsList().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && !StringUtils.isEmpty(ingredient.getName())
                && ingredient.getWeight() >0
                && ingredient.getUnitOfMeasurement() != null;
    }
}
