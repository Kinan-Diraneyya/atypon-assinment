package com.kinan.atypon.assignment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kinan.atypon.assignment.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.GetRecipeNutritionsResult;
import com.kinan.atypon.assignment.model.GetRecipeTotalCaloriesResult;
import com.kinan.atypon.assignment.model.SearchRecipesResult;

/**
 * REST controller for handling recipe-related requests.
 * <p>
 * This controller handles incoming HTTP requests, delegates them to the
 * RecipeService, and returns appropriate HTTP responses.
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

	private final RecipeService recipeService;

    /**
     * Parameterized constructor.
     * <p>
     * Initializes the instance with the specified recipeService.
     * </p>
     *
     * @param recipeService A {@link RecipeService} implementation to use for fetching
     */
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

    /**
     * Searches for recipes based on a query string.
     * <p>
     * This method handles GET requests to the /search endpoint and returns a list of recipes that match the query.
     * If the query is empty or null, it responds with a 400 Bad Request status.
     * </p>
     *
     * @param query the search query string
     * @param page the page to get results from
     * @param pageSize the number of results to get
     * @return a ResponseEntity containing the search results
     */
	@GetMapping("/search")
	public ResponseEntity<SearchRecipesResult> searchRecipes(
			@RequestParam String query, 
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int pageSize) {
		log.info("Received search request with query: {}", query);
	    if (query == null || query.trim().isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Query cannot be empty");
	    }
	    
		SearchRecipesResult response = recipeService.searchRecipes(query, page, pageSize);
		log.info("Returning reponse: {}", response);
		return ResponseEntity.ok(response);
	}

	/**
     * Retrieves the nutritional information for a specific recipe.
     * <p>
     * This method handles GET requests to the /{recipeID}/nutritions endpoint and returns the nutritional information for the specified recipe.
     * If the recipe ID is empty or null, it responds with a 400 Bad Request status.
     * </p>
     *
     * @param recipeID the ID of the recipe
     * @return a ResponseEntity containing the nutritional information
     */
	@GetMapping("/{recipeID}/nutritions")
	public ResponseEntity<GetRecipeNutritionsResult> getRecipeNutritions(@PathVariable String recipeID) {
		log.info("Received get recipe nutritions request with recipe ID: {}", recipeID);
		GetRecipeNutritionsResult response = recipeService.getRecipeNutritions(recipeID);
		log.info("Returning reponse: {}", response);
		return ResponseEntity.ok(response);
	}

	/**
     * Retrieves the total calories for a customized recipe.
     * <p>
     * This method handles GET requests to the /{recipeID}/customized-calories endpoint and returns the total calories for the specified recipe,
     * excluding specified ingredients. If the recipe ID is empty or null, it responds with a 400 Bad Request status.
     * </p>
     *
     * @param recipeID the ID of the recipe
     * @param exclude a list of ingredients to exclude from the calorie calculation
     * @return a ResponseEntity containing the total calories
     */
	@GetMapping("/{recipeID}/customized-calories")
	public ResponseEntity<GetRecipeTotalCaloriesResult> getCustomizedRecipeCalories(@PathVariable String recipeID, @RequestParam List<String> exclude) {
		log.info("Received get customized recipe calories request with recipe ID: {}, and ingredients to exclude: {}", recipeID, exclude);	    
		GetRecipeTotalCaloriesResult response = recipeService.getRecipeCustomizedTotalCalories(recipeID, exclude);
		log.info("Returning reponse: {}", response);
		return ResponseEntity.ok(response);
	}
	
	/**
     * Handles ResponseStatusException and returns a 400 Bad Request status with the exception message.
     *
     * @param ex the ResponseStatusException
     * @return a ResponseEntity with the error message
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleMissingParams(ResponseStatusException ex) {
        String name = ex.getReason();
        log.info("Missing request parameter: {}", name);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(name + " parameter is missing");
    }

	/**
     * Handles MissingServletRequestParameterException and returns a 400 Bad Request status with the exception message.
     *
     * @param ex the MissingServletRequestParameterException
     * @return a ResponseEntity with the error message
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        log.info("Missing request parameter: {}", name);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(name + " parameter is missing");
    }
    
	/**
     * Handles ClientException and returns a 400 Bad Request status with the exception message.
     *
     * @param ex the ClientException
     * @return a ResponseEntity with the error message
     */
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<String> handleClientException(ClientException ex) {
		log.info("Encountered a client exception");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	/**
     * Handles ServerException and returns a 500 Internal Server Error status with the exception message.
     *
     * @param ex the ServerException
     * @return a ResponseEntity with the error message
     */
	@ExceptionHandler(ServerException.class)
	public ResponseEntity<String> handleServerException(ServerException ex) {
		log.info("Encountered a server exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	/**
     * Handles TimeoutException and returns a 408 Request Timeout status with the exception message.
     *
     * @param ex the TimeoutException
     * @return a ResponseEntity with the error message
     */
	@ExceptionHandler(TimeoutException.class)
	public ResponseEntity<String> handleTimeoutException(TimeoutException ex) {
		log.info("Encountered a timeout exception");
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(ex.getMessage());
	}

	/**
     * Handles generic exceptions and returns a 500 Internal Server Error status with the exception message.
     *
     * @param ex the Exception
     * @return a ResponseEntity with the error message
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		log.info("Encountered a general exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}