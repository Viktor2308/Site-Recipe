package me.sky.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Ingredient
 */
public class Ingredient {
    private String name;
    private int weight;
    private String unitOfMeasurement;

}
