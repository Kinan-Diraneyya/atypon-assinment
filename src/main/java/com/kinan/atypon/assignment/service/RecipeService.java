package com.kinan.atypon.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kinan.atypon.assignment.client.SpoonacularClient;
import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.GetRecipeNutritionsResult;
import com.kinan.atypon.assignment.model.GetRecipeTotalCaloriesResult;
import com.kinan.atypon.assignment.model.Ingredient;
import com.kinan.atypon.assignment.model.Nutrient;
import com.kinan.atypon.assignment.model.SearchRecipesResult;
import com.kinan.atypon.assignment.model.SpoonacularGetRecipeNutritionsResponse;
import com.kinan.atypon.assignment.model.SpoonacularSearchRecipesResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for handling recipe-related operations.
 * <p>
 * This service delegates the actual API calls to the SpoonacularClient.
 * </p>
 */
@Slf4j
@Service
public class RecipeService {

    private final SpoonacularClient spoonacularClient;

    /**
     * Parameterized constructor.
     * <p>
     * Initializes the instance with the specified spoonacularClient.
     * </p>
     *
     * @param spoonacularClient A {@link SpoonacularClient} implementation to use for requests
     */
    public RecipeService(SpoonacularClient spoonacularClient) {
        this.spoonacularClient = spoonacularClient;
    }

    /**
     * Searches for recipes based on a query string.
     * <p>
     * This method uses a SpoonacularClient to fetch recipes based on the specified query.
     * </p>
     *
     * @param query the search query string to find matching recipes
     * @param page the page to get results from
     * @param pageSize the number of results to get
     * @return a {@link SearchRecipesResult} containing the search results from the Spoonacular API
     * @throws IllegalArgumentException if the query is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    public SearchRecipesResult searchRecipes(String query, int page, int pageSize) {
    	log.info("Searching recipes using the following query: {}, page: {}, and pageSize: {}", query, page, pageSize);
    	
        SpoonacularSearchRecipesResponse response = spoonacularClient.searchRecipes(query, page, pageSize);
        SearchRecipesResult searchRecipesResult = SearchRecipesResult.builder()
        		.page(response.getOffset() / response.getNumber())
        		.pageSize(response.getNumber())
        		.totalResults(response.getTotalResults())
        		.records(response.getResults())
        		.build();
        
        log.info("Mapped the response to a SearchRecipesResult: {}", searchRecipesResult);
        return searchRecipesResult;
    }
    
    /**
     * Return's a recipe's nutritions based on its ID
     * <p>
     * This method uses a SpoonacularClient to fetch the specified recipe's nutritional values.
     * The method returns all totals found in SpoonacularClient as is, except for calories, whuch
     * it sums up from the calories of each individual ingredient. There is not advantage to doing this
     * extra calculation, but it was specifically specified in the requirements.
     * </p>
     *
     * @param recipeId the recipe's ID
     * @return a {@link SpoonacularGetRecipeNutritionsResponse} containing the nutritions of the recipe
     * @throws IllegalArgumentException if the recipe ID is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    public GetRecipeNutritionsResult getRecipeNutritions(String recipeID) {
    	log.info("Fetching recipe nutritions using the following reciptID: {}", recipeID);
    	
    	SpoonacularGetRecipeNutritionsResponse response = spoonacularClient.getRecipeNutritions(recipeID);
    	List<Nutrient> nutrients = response.getNutrients()
    			.stream()
    			.filter(n -> !"calories".equalsIgnoreCase(n.getName()))
    			.collect(Collectors.toList());
    	
    	// The below hassle is to adhere to the requirement stating the following:
    	// "The total number of calories must be calculated by adding the calories of each 
    	// ingredient that forms the recipe". 
    	// However, the total calories of one recipe can be accessed directly through response.nutrients
    	// by finding the nutrient with the name 'calories'.
    	double caloriesTotal = 0;
    	for (Ingredient ingredient : response.getIngredients()) {
    		caloriesTotal += extractCalories(ingredient);
    	}
    	log.trace("The sum of the calories of all ingredients: {}", caloriesTotal);
    	Nutrient totalCaloriesNutrient = new Nutrient("Calories", "kcal", caloriesTotal);
    	nutrients.add(totalCaloriesNutrient);
    	
    	GetRecipeNutritionsResult getRecipeNutritionsResult =  new GetRecipeNutritionsResult(nutrients);
    	log.info("Extracted nutritions from the result: {}", getRecipeNutritionsResult);
    	return getRecipeNutritionsResult;
    }
    
    
    /**
     * Return's a customized recipe's total calories based on its ID and which ingredients to exclude
     * <p>
     * This method uses a SpoonacularClient to fetch the specified recipe's nutritional values. It then
     * returns the recipe's total calories minus the calories of exclude ingredients.
     * The method ignores excluded ingredients that it does not find in the original recipe.
     * </p>
     *
     * @param recipeId the recipe's ID
     * @return a {@link SpoonacularGetRecipeNutritionsResponse} containing the nutritions of the recipe
     * @throws IllegalArgumentException if the recipe ID is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    public GetRecipeTotalCaloriesResult getRecipeCustomizedTotalCalories(String recipeID, List<String> excludedIngredientNames) {
    	log.info("Calculating recipe calories using the following reciptID: {}", recipeID);
    	log.info("Excluding the following ingredients: {}", excludedIngredientNames);
    	SpoonacularGetRecipeNutritionsResponse response = spoonacularClient.getRecipeNutritions(recipeID);
    	
    	double caloriesTotal = 0;
    	for (Ingredient ingredient : response.getIngredients()) {
    		boolean isIngredientExcluded = excludedIngredientNames
    				.stream()
    				.anyMatch(i -> i != null && i.equalsIgnoreCase(ingredient.getName()));
    		if (!isIngredientExcluded) {
    			caloriesTotal += extractCalories(ingredient);
    		}
    	}
    	log.info("Calculated total calories: {}", caloriesTotal);
    	
    	GetRecipeTotalCaloriesResult getRecipeTotalCaloriesResult =  new GetRecipeTotalCaloriesResult(caloriesTotal);
    	return getRecipeTotalCaloriesResult;
    }
    
    /**
     * Return's the calories within an ingredient
     * <p>
     * This method iterates over all nutritions within an ingredient, until it finds its calories, and 
     * returns them.
     * </p>
     *
     * @param ingredient the ingredient to extract the calories from
     * @return the calories within the specified ingredient, or 0 if anything goes along the way
     */
    private double extractCalories(Ingredient ingredient) {
    	log.trace("Extracting calories from: {}", ingredient);
    	if (ingredient == null || ingredient.getNutrients() == null) {
    		log.trace("The ingredient is invalid");
    		return 0d;
    	}

    	Optional<Nutrient> caloriesNutrient = ingredient.getNutrients()
    			.stream()
    			.parallel()
    			.filter(i -> "calories".equalsIgnoreCase(i.getName()))
    			.findAny();
    	log.trace("Calories nutrient object: {}", caloriesNutrient);
    	if (caloriesNutrient.isPresent() && caloriesNutrient.get().getAmount() != null) {
    		double calories = caloriesNutrient.get().getAmount();
    		log.trace("Calories: {}", calories);
    		return calories;
    	}
    	
    	log.trace("Calories were not found in the ingredient; substituting 0");
    	return 0d;
    }
}
