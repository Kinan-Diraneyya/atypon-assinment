package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The response for SearchRecipes.
 * <p>
 * This class is used as the response for the application's searchRecipes endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SearchRecipesResult {
	
	/*
	 * The page number
	 */
	private Integer page;
	
	/*
	 * The page size
	 */
	private Integer pageSize;
	
	/*
	 * The count of all results found
	 */
	private Integer totalResults;
	
	/*
	 * The list of results found
	 */
	private List<Recipe> records;
}
