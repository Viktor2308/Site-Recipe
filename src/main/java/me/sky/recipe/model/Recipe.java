package me.sky.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int cookingTimeMin;
    private List<Ingredients> ingredientsList;
    private List<String> cookingInstructionsList;


}