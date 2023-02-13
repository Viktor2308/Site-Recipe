package me.sky.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Recipe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int cookingTimeMin;
    private List<Ingredient> ingredientsList;
    private Map<Integer, String> cookingInstructionsList;


}