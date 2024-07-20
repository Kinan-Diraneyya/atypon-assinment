package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The response for GetRecipeNutritions.
 * <p>
 * This class is used as the response for the applications getRecipeNutrition's endpoint.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GetRecipeNutritionsResult {
	
	/*
	 * The response's nutrients
	 */
	private List<Nutrient> nutrients;
}
