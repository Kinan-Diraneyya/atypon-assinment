package com.kinan.atypon.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The response for getRecipeTotalCalories.
 * <p>
 * This class is used as the response for the application's getRecipeTotalCalories endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GetRecipeTotalCaloriesResult {
	
	/*
	 * The total calories of the recipe, customized by removed ingredients
	 */
	private Double totalCalories;
}
