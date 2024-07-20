package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response for Spoonacular's SearchRecipes.
 * <p>
 * This class is used as the response for Spoonacular's SearchRecipes endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpoonacularSearchRecipesResponse {
	
	/*
	 * The response's result offset (how many results to skip, as used in pagination)
	 */
	private int offset;
	
	/*
	 * The response's result number (AKA page size, how many results to return, as used in pagination)
	 */
	private int number;
	
	/*
	 * The total number of results found (as used in pagination)
	 */
	private int totalResults;
	
	/*
	 * The results found
	 */
	private List<Recipe> results;
}
