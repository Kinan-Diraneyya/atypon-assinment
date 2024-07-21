package com.kinan.atypon.assignment.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.SpoonacularGetRecipeNutritionsResponse;
import com.kinan.atypon.assignment.model.SpoonacularSearchRecipesResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Client for interacting with the Spoonacular API.
 * <p>
 * This client provides methods to interact with the Spoonacular API to search for recipes and retrieve
 * other relevant information.d
 * </p>
 */
@Slf4j
@Component
public class SpoonacularClient {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    /**
     * Parameterized constructor.
     * <p>
     * Initializes the instance with the specified restTemplate, apiKey, and baseUrl.
     * </p>
     *
     * @param restTemplate A {@link RestTemplate} implementation to use for requests
     * @param apiKey Spoonacular's API key
     * @param baseUrl Spoonacular's base URL to use for all requests 
     */
    public SpoonacularClient(RestTemplate restTemplate,
                             @Value("${spoonacular.api-key}") String apiKey,
                             @Value("${spoonacular.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    /**
     * Return's a recipe's nutritions based on its ID
     * <p>
     * This method sends a GET request to the Spoonacular API's recipe nutritions endpoint
     * to retrieve its nutritional values
     * </p>
     *
     * @param recipeId the recipe's ID
     * @return a {@link SpoonacularGetRecipeNutritionsResponse} containing the nutritions of the recipe
     * @throws IllegalArgumentException if the query is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    public SpoonacularGetRecipeNutritionsResponse getRecipeNutritions(String recipeId) {
    	log.info("Fetching recipe nutritions from Spoonacular using the following reciptID: {}", recipeId);
    	if (recipeId == null || recipeId.trim().isEmpty()) {
            log.error("Invalid recipe ID parameter: recipeId cannot be null or empty");
            throw new IllegalArgumentException("recipeId cannot be null or empty");
        }
    	
        String url = String.format("recipes/%s/nutritionWidget.json?apiKey=%s", recipeId, apiKey);
        SpoonacularGetRecipeNutritionsResponse response = execute(url, SpoonacularGetRecipeNutritionsResponse.class);
        return response;
    }

    /**
     * Searches for recipes based on a query string.
     * <p>
     * This method sends a GET request to the Spoonacular API's recipe search endpoint
     * to retrieve recipes that match the specified query string.
     * </p>
     *
     * @param query the search query string to find matching recipes
     * @param page the page to get results from
     * @param pageSize the number of results to get
     * @return a {@link SpoonacularSearchRecipesResponse} containing the search results from the Spoonacular API
     * @throws IllegalArgumentException if the query is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    public SpoonacularSearchRecipesResponse searchRecipes(String query, int page, int pageSize) {
    	log.info("Searching Spoonacular for recipes using the following query: {}", query);
    	if (query == null || query.trim().isEmpty()) {
            log.error("Invalid query parameter: query cannot be null or empty");
            throw new IllegalArgumentException("query cannot be null or empty");
        }
    	
        String url = String.format("recipes/complexSearch?query=%s&apiKey=%s&offset=%s&number=%s", query, apiKey, page * pageSize, pageSize);
        SpoonacularSearchRecipesResponse response = execute(url, SpoonacularSearchRecipesResponse.class);
        return response;
    }
    
    /**
     * Return's a response object from the specified URL, throwing various types of exceptions on failure
     * <p>
     * This method sends a GET request to the Spoonacular API's, on the specified URL, returning a response
     * of the specified type on success, and throwing an appropriate exception on failure.
     * </p>
     *
     * @param subUrl the sub URL on the Spoonacular API
     * @param responseType The type of the response object to return
     * @return a {@link SpoonacularGetRecipeNutritionsResponse} containing the nutritions of the recipe
     * @throws IllegalArgumentException if the query is null or empty
     * @throws ClientException if a client error occurs (4xx)
     * @throws ServerException if a server error occurs (5xx)
     * @throws TimeoutException if a timeout error occurs
     * @throws RuntimeException if an unexpected error occurs
     */
    private <T> T execute(String subUrl, Class<T> responseType) {
    	T response;
    	String fullUrl = String.format("%s/%s", baseUrl, subUrl);
    	
    	log.info("Making API request to: {}", fullUrl);
        try {
            response = restTemplate.getForObject(fullUrl, responseType);
        } catch (HttpClientErrorException e) {
        	String responseBody = getResponseBodyFromException(e);
            log.error("Client error occurred: {}", responseBody);
            throw new ClientException("Client error occurred: " + responseBody);
        } catch (HttpServerErrorException e) {
        	String responseBody = getResponseBodyFromException(e);
            log.error("Server error occurred: {}", responseBody);
            throw new ServerException("Server error occurred: " + responseBody);
        } catch (ResourceAccessException e) {
            log.error("Request timeout: {}", e.getMessage());
            throw new TimeoutException("Request timeout: " + e.getMessage());
        } catch (Exception e) {
            log.error("An unexpected error occurred: {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
        
        log.info("The request succeeded, returning the following response: {}", response);
        return response;
    }

    /**
     * Return's a response body string from the specified {@link HttpStatusCodeException}
     * <p>
     * This method extracts and returns the response body from an HttpStatusCodeException as
     * a string, which protects against a runtime error that happens when the body within the 
     * exception is null.
     * </p>
     *
     * @param httpStatusCodeException the exception to extract the response body from
     */
    private String getResponseBodyFromException(HttpStatusCodeException httpStatusCodeException) {
    	log.trace("Extracting the response body from the exception");
    	String body;
    	try {
    		body = httpStatusCodeException.getResponseBodyAsString();
    		log.trace("The found the response body: {}", body);
    	} catch (Exception e) {
    		body = "No response body";
    		log.trace("The exception has no response body");
    	}
    	return body;
    }
}
