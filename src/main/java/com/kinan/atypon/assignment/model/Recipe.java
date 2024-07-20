package com.kinan.atypon.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a Spoonacular recipe.
 * <p>
 * This class provides a data model for Spoonacular's recipes.
 * The class is used inside {@link SpoonacularSearchRecipesResponse}.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Recipe {
	
	/*
	 * The recipe's ID
	 */
	private String id;
	
	/*
	 * The recipe's title
	 */
	private String title;
	
	/*
	 * The recipe's image URL
	 */
	private String image;
	
	/*
	 * The recipe's image type
	 */
	private String imageType;
}
