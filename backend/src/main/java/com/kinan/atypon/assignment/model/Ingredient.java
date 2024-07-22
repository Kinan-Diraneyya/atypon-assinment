package com.kinan.atypon.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a Spoonacular ingredient.
 * <p>
 * This class provides a data model for Spoonacular ingredients.
 * The class is used inside {@link SpoonacularGetRecipeInformationResponse}.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Ingredient {
	

    /**
     * The ID of the ingredient.
     */
	private Integer id;
	
	/**
     * The name of the ingredient.
     */
	private String name;
	
	
	/**
     * The image of the ingredient.
     */
	private String image;
	
	/**
     * The unit of the ingredient.
     */
	private String unit;
	
	/**
     * The original description of the ingredient, as noted by the recipe's author.
     */
	private String original;
	
	/**
     * The amount from the ingredient to use.
     */
	private double amount;
	
	/**
     * The ingredient's detailed list of nutrients.
     */
	private List<Nutrient> nutrients;
}
