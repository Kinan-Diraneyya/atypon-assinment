package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response for Spoonacular's GetRecipeNutritions.
 * <p>
 * This class is used as the response for Spoonacular's GetRecipeNutritions endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpoonacularGetRecipeNutritionsResponse {
	
	/*
	 * The response's total nutrients
	 */
	public List<Nutrient> nutrients;
	
	/*
	 * The response's ingredients
	 */
	private List<Ingredient> ingredients;
}
