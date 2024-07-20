package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Spoonacular ingredient.
 * <p>
 * This class provides a data model for Spoonacular ingredients.
 * The class is used inside {@link SpoonacularGetRecipeNutritionsResponse}.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
	

    /**
     * The ID of the ingredient.
     */
	private int id;
	
	/**
     * The name of the ingredient.
     */
	private String name;
	
	/**
     * The ingredient's detailed list of nutrients.
     */
	private List<Nutrient> nutrients;
}
