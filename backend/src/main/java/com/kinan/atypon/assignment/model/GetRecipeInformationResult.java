package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The response for GetRecipeInformation.
 * <p>
 * This class is used as the response for the applications getRecipeInformation's endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GetRecipeInformationResult {
	
	/*
	 * The recipe's title
	 */
	private String title;
	
	/*
	 * The recipe's image URL
	 */
	private String image;
	
	/*
	 * The time it takes to prepare the recipe
	 */
	private Double readyInMinutes;
	
	/*
	 * The recipe's number of servings
	 */
	private Integer servings;
	
	/*
	 * The recipe's health score
	 */
	private Double healthScore;
	
	/*
	 * The recipe's summary
	 */
	private String summary;
	
	/*
	 * The recipe's dish types (tags)
	 */
	private List<String> dishTypes;
	
	
	/*
	 * The recipe's ingredients
	 */
	private List<Ingredient> ingredients;
	
	/*
	 * The recipe's nutrients
	 */
	private List<Nutrient> nutrients;
}
