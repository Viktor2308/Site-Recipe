package me.sky.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredients {
    private String name;
    private int weight;
    private String unitOfMeasurement;

}
