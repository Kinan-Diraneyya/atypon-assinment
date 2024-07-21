package com.kinan.atypon.assignment.client;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.SpoonacularGetRecipeNutritionsResponse;
import com.kinan.atypon.assignment.model.SpoonacularSearchRecipesResponse;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * The test class for SpoonacularClient.
 * <p>
 * This class tests all public methods within SpoonacularClient for all possible outputs and 
 * exceptions
 * </p>
 */
@SpringBootTest
public class SpoonacularClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SpoonacularClient spoonacularClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        spoonacularClient = new SpoonacularClient(restTemplate, "dummyApiKey", "dummyBaseUrl");
    }

    /**
     * Tests that the getRecipeNutritions method with a valid recipe ID returns a successful response.
     * <p>
     * This test verifies that the getRecipeNutritions method returns a non-null response
     * when a valid recipe ID is provided. The RestTemplate is mocked to return a predefined response.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_SuccessfulResponse() {
    	SpoonacularGetRecipeNutritionsResponse mockResponse = new SpoonacularGetRecipeNutritionsResponse();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(mockResponse);
        SpoonacularGetRecipeNutritionsResponse response = spoonacularClient.getRecipeNutritions("pasta");
        assertEquals(mockResponse, response);
    }

    /**
     * Tests that the getRecipeNutritions method with an empty recipe ID throws an IllegalArgumentException.
     * <p>
     * This test verifies that the getRecipeNutritions method throws an IllegalArgumentException when
     * given an empty recipe ID.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_EmptyRecipeID() {
        assertThrows(IllegalArgumentException.class, () -> {
            spoonacularClient.getRecipeNutritions("");
        });
    }

    /**
     * Tests that the getRecipeNutritions method with a null recipe ID throws an IllegalArgumentException.
     * <p>
     * This test verifies that the searchRecipes method throws an IllegalArgumentException when
     * given a null recipe ID.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_NullRecipeID() {
        assertThrows(IllegalArgumentException.class, () -> {
            spoonacularClient.getRecipeNutritions(null);
        });
    }

    /**
     * Tests that the getRecipeNutritions method throws a ClientException when it encounters an HttpClientErrorException.
     * <p>
     * This test verifies that the getRecipeNutritions method throws a ClientException when its underlying
     * restTemplate encounters a client error and throws an HttpClientErrorException.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_ClientError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(HttpClientErrorException.class);
        assertThrows(ClientException.class, () -> {
            spoonacularClient.getRecipeNutritions("invalidRecipeID");
        });
    }

    /**
     * Tests that the getRecipeNutritions method throws a ServerException when it encounters an HttpServerErrorException.
     * <p>
     * This test verifies that the getRecipeNutritions method throws a ServerException when its underlying
     * restTemplate encounters a server error and throws an HttpServerErrorException.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_ServerError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(HttpServerErrorException.class);
        assertThrows(ServerException.class, () -> {
            spoonacularClient.getRecipeNutritions("validRecipeID");
        });
    }

    /**
     * Tests the that the getRecipeNutritions method throws a TimeoutException when its request times out.
     * <p>
     * This test verifies that the getRecipeNutritions method throws a TimeoutException when its underlying
     * restTemplate times out and throws a ResourceAccessException.
     * </p>
     */
    @Test
    public void testGetRecipeNutritions_TimeoutError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(ResourceAccessException.class);
        assertThrows(TimeoutException.class, () -> {
            spoonacularClient.getRecipeNutritions("validQuery");
        });
    }

    /**
     * Tests that the searchRecipes method with a valid query returns a successful response.
     * <p>
     * This test verifies that the searchRecipes method returns a non-null response
     * when a valid query is provided. The RestTemplate is mocked to return a predefined response.
     * </p>
     */
    @Test
    public void testSearchRecipes_SuccessfulResponse() {
        SpoonacularSearchRecipesResponse mockResponse = new SpoonacularSearchRecipesResponse();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(mockResponse);
        SpoonacularSearchRecipesResponse response = spoonacularClient.searchRecipes("pasta", 0, 10);
        assertEquals(mockResponse, response);
    }

    /**
     * Tests that the searchRecipes method with an empty query throws an IllegalArgumentException.
     * <p>
     * This test verifies that the searchRecipes method throws an IllegalArgumentException when
     * given an empty query.
     * </p>
     */
    @Test
    public void testSearchRecipes_EmptyQuery() {
        assertThrows(IllegalArgumentException.class, () -> {
            spoonacularClient.searchRecipes("", 0, 10);
        });
    }

    /**
     * Tests that the searchRecipes method with a null query throws an IllegalArgumentException.
     * <p>
     * This test verifies that the searchRecipes method throws an IllegalArgumentException when
     * given a null query.
     * </p>
     */
    @Test
    public void testSearchRecipes_NullQuery() {
        assertThrows(IllegalArgumentException.class, () -> {
            spoonacularClient.searchRecipes(null, 0, 10);
        });
    }

    /**
     * Tests the that the searchRecipes method throws a ClientException when it encounters an HttpClientErrorException.
     * <p>
     * This test verifies that the searchRecipes method throws an ClientException when its underlying
     * restTemplate encounters a client error and throws an HttpClientErrorException.
     * </p>
     */
    @Test
    public void testSearchRecipes_ClientError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(HttpClientErrorException.class);
        assertThrows(ClientException.class, () -> {
            spoonacularClient.searchRecipes("query", 0, 10);
        });
    }

    /**
     * Tests the that the searchRecipes method throws a ServerException when it encounters an HttpServerErrorException.
     * <p>
     * This test verifies that the searchRecipes method throws a ServerException when its underlying
     * restTemplate encounters a server error and throws an HttpServerErrorException.
     * </p>
     */
    @Test
    public void testSearchRecipes_ServerError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(HttpServerErrorException.class);
        assertThrows(ServerException.class, () -> {
            spoonacularClient.searchRecipes("query", 0, 10);
        });
    }

    /**
     * Tests the that the searchRecipes method throws a TimeoutException when its request times out.
     * <p>
     * This test verifies that the searchRecipes method throws a TimeoutException when its underlying
     * restTemplate times out and throws a ResourceAccessException.
     * </p>
     */
    @Test
    public void testSearchRecipes_TimeoutError() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(ResourceAccessException.class);
        assertThrows(TimeoutException.class, () -> {
            spoonacularClient.searchRecipes("query", 0, 10);
        });
    }
}
