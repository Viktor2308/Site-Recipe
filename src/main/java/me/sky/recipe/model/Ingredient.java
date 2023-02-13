package me.sky.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Ingredient
 */
public class Ingredient {
    private String name;
    private int weight;
    private String unitOfMeasurement;

}
