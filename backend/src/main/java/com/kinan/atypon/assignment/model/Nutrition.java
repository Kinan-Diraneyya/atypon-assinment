package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a Spoonacular nutritional details.
 * <p>
 * This class provides a data model for Spoonacular nutrition.
 * The class is used inside {@link SpoonacularGetRecipeInformationResponse}.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Nutrition {
	
	/*
	 * The recipe's total nutrients
	 */
	private List<Nutrient> nutrients;
	
	/*
	 * The recipe's ingredients
	 */
	private List<Ingredient> ingredients;
}
