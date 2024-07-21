package com.kinan.atypon.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a Spoonacular nutrient.
 * <p>
 * This class provides a data model for Spoonacular nutrients.
 * The class is used inside {@link Ingredient} and {@link SpoonacularGetRecipeNutritionsResponse}.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Nutrient {

    /**
     * The name of the nutrient.
     */
    private String name;

    /**
     * The unit of measurement for the nutrient.
     */
    private String unit;

    /**
     * The amount of the nutrient.
     */
    private Double amount;
}