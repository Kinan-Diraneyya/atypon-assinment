package com.kinan.atypon.assignment.service;

import com.kinan.atypon.assignment.client.SpoonacularClient;
import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.SpoonacularSearchRecipesResponse;
import com.kinan.atypon.assignment.model.GetRecipeInformationResult;
import com.kinan.atypon.assignment.model.GetRecipeTotalCaloriesResult;
import com.kinan.atypon.assignment.model.Ingredient;
import com.kinan.atypon.assignment.model.Nutrient;
import com.kinan.atypon.assignment.model.Nutrition;
import com.kinan.atypon.assignment.model.Recipe;
import com.kinan.atypon.assignment.model.SearchRecipesResult;
import com.kinan.atypon.assignment.model.SpoonacularGetRecipeInformationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Arrays;
import java.util.List;

public class RecipeServiceTest {

    @Mock
    private SpoonacularClient spoonacularClient;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that the getRecipeInformation method with a valid recipe ID returns a successful result.
     * <p>
     * This test verifies that the getRecipeInformation method returns the same nutretions it receives
     * from Spoonacular, with the calories calculated as the sum of the calories of the returned ingredients.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_SuccessfulResult() {
    	Nutrient[] mockTotalNutrientsArray = {
                new Nutrient("Calories", "kcal", 10d),
                new Nutrient("b", "g", 2d),
                new Nutrient("c", "g", 3d)
        };
    	List<Nutrient> mockTotalNutrients = Arrays.asList(mockTotalNutrientsArray);
    	
    	Ingredient[] mockIngredientsArray = {		
    			new Ingredient(1, "a", "image", "kcal", "original", 1d, Arrays.asList(new Nutrient[] { new Nutrient("Calories","kcal", 2d) })), 
    			new Ingredient(2, "b", "image", "kcal", "original", 1d, Arrays.asList(new Nutrient[] { new Nutrient("Calories","kcal", 8d) })), 
    			new Ingredient(3, "c", "image", "kcal", "original", 1d, null)
        };
    	List<Ingredient> mockIngredients = Arrays.asList(mockIngredientsArray);
    	
    	Nutrition nutrition = new Nutrition(mockTotalNutrients, mockIngredients);
    	
    	SpoonacularGetRecipeInformationResponse mockResponse = new SpoonacularGetRecipeInformationResponse("title", "image", 1d, 2, 3d,  "summary", nutrition, null, mockIngredients);
        when(spoonacularClient.getRecipeInformation(anyString())).thenReturn(mockResponse);
        
        GetRecipeInformationResult response = recipeService.getRecipeInformation("existingRecipeID");
        assertThat(response.getNutrients(), containsInAnyOrder(mockTotalNutrientsArray));
    }
    
    /**
     * Tests that the getRecipeInformation method with an empty recipe ID throws an IllegalArgumentException.
     * <p>
     * This test verifies that the getRecipeInformation method throws an IllegalArgumentException when
     * given an empty recipe ID.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_EmptyRecipeID() {
    	when(spoonacularClient.getRecipeInformation("")).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.getRecipeInformation("");
        });
    }

    /**
     * Tests that the getRecipeInformation method with a null recipe ID throws an IllegalArgumentException.
     * <p>
     * This test verifies that the searchRecipes method throws an IllegalArgumentException when
     * given a null recipe ID.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_NullRecipeID() {
    	when(spoonacularClient.getRecipeInformation(null)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.getRecipeInformation(null);
        });
    }

    /**
     * Tests that the getRecipeInformation method delegates the ClientException if it encounters one.
     * <p>
     * This test verifies that the getRecipeInformation method throws a ClientException when its underlying
     * RecipeService encounters a client error.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_ClientError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () -> {
            recipeService.getRecipeInformation("invalidID");
        });
    }

    /**
     * Tests that the getRecipeInformation method delegates the ServerException if it encounters one.
     * <p>
     * This test verifies that the getRecipeInformation method throws a ServerException when its underlying
     * RecipeService encounters a server error.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_ServerError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(ServerException.class);
        assertThrows(ServerException.class, () -> {
            recipeService.getRecipeInformation("recipeID");
        });
    }

    /**
     * Tests the that the getRecipeInformation method delegates the TimeoutException if it encounters one.
     * <p>
     * This test verifies that the getRecipeInformation method throws a TimeoutException when its underlying
     * RecipeService encounters a timeout error.
     * </p>
     */
    @Test
    public void testGetRecipeInformation_TimeoutError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(TimeoutException.class);
        assertThrows(TimeoutException.class, () -> {
            recipeService.getRecipeInformation("recipeID");
        });
    }

    /**
     * Tests that the searchRecipes method with a valid query returns a successful result.
     * <p>
     * This test verifies that the searchRecipes method returns a correct {@link SearchRecipesResult},
     * mapping offset to page, number to pageSize, and results to records.
     * </p>
     */
    @Test
    public void testSearchRecipes_SuccessfulResponse() {
    	Recipe[] mockTotalRecipesArray = {
                new Recipe("1", "a", "", "jpg"),
                new Recipe("2", "b", "", "png")
        };
    	List<Recipe> mockTotalRecipes = Arrays.asList(mockTotalRecipesArray);
    	
    	SpoonacularSearchRecipesResponse mockResponse = new SpoonacularSearchRecipesResponse(4, 2, 10, mockTotalRecipes);
    	SearchRecipesResult expectedResponse = new SearchRecipesResult(2, 2, 10, mockTotalRecipes);
    	
        when(spoonacularClient.searchRecipes(anyString(), anyInt(), anyInt())).thenReturn(mockResponse);
        SearchRecipesResult response = recipeService.searchRecipes("existingRecipeID", 2, 2);
        assertEquals(response, expectedResponse);
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
    	when(spoonacularClient.searchRecipes("", 0, 10)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.searchRecipes("", 0, 10);
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
    	when(spoonacularClient.searchRecipes(null, 0, 10)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.searchRecipes(null, 0, 10);
        });
    }

    /**
     * Tests that the searchRecipes method delegates the ClientException if it encounters one.
     * <p>
     * This test verifies that the searchRecipes method throws a ClientException when its underlying
     * RecipeService encounters a client error.
     * </p>
     */
    @Test
    public void testSearchRecipes_ClientError() {
        when(spoonacularClient.searchRecipes(anyString(), anyInt(), anyInt())).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () -> {
            recipeService.searchRecipes("query", 0, 10);
        });
    }

    /**
     * Tests that the searchRecipes method delegates the ServerException if it encounters one.
     * <p>
     * This test verifies that the searchRecipes method throws a ServerException when its underlying
     * RecipeService encounters a server error.
     * </p>
     */
    @Test
    public void testSearchRecipes_ServerError() {
        when(spoonacularClient.searchRecipes(anyString(), anyInt(), anyInt())).thenThrow(ServerException.class);
        assertThrows(ServerException.class, () -> {
            recipeService.searchRecipes("query", 0, 10);
        });
    }

    /**
     * Tests the that the searchRecipes method delegates the TimeoutException if it encounters one.
     * <p>
     * This test verifies that the searchRecipes method throws a TimeoutException when its underlying
     * RecipeService encounters a timeout error.
     * </p>
     */
    @Test
    public void testSearchRecipes_TimeoutError() {
        when(spoonacularClient.searchRecipes(anyString(), anyInt(), anyInt())).thenThrow(TimeoutException.class);
        assertThrows(TimeoutException.class, () -> {
            recipeService.searchRecipes("query", 0, 10);
        });
    }

    /**
     * Tests that the getRecipeCustomizedTotalCalories method with a valid query returns a successful result.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method returns a correct {@link GetRecipeTotalCaloriesResult}.
     * </p>
     */
    @Test
    public void testGetRecipeCustomizedTotalCalories_SuccessfulResult() {
    	Nutrient[] mockTotalNutrientsArray = {
                new Nutrient("Calories", "kcal", 10d),
                new Nutrient("b", "g", 2d),
                new Nutrient("c", "g", 3d)
        };
    	List<Nutrient> mockTotalNutrients = Arrays.asList(mockTotalNutrientsArray);
    	
    	Ingredient[] mockIngredientsArray = {
    			
    			new Ingredient(1, "a", "image", "kcal", "original", 1d, Arrays.asList(new Nutrient[] { new Nutrient("Calories","kcal", 2d) })), 
    			new Ingredient(2, "b", "image", "kcal", "original", 1d, Arrays.asList(new Nutrient[] { new Nutrient("Calories","kcal", 8d) })), 
    			new Ingredient(3, "c", "image", "kcal", "original", 1d, null)
        };
    	List<Ingredient> mockIngredients = Arrays.asList(mockIngredientsArray);
    	
    	List<String> excludedIngredientsNames = Arrays.asList(new String[] {"b", "c"});
    	Nutrition nutrition = new Nutrition(mockTotalNutrients, mockIngredients);
    	
    	SpoonacularGetRecipeInformationResponse mockResponse = new SpoonacularGetRecipeInformationResponse("title", "image", 1d, 2, 3d,  "summary", nutrition, null, mockIngredients);
    	GetRecipeTotalCaloriesResult expectedResponse = new GetRecipeTotalCaloriesResult(2d);
    	
        when(spoonacularClient.getRecipeInformation(anyString())).thenReturn(mockResponse);
        GetRecipeTotalCaloriesResult response = recipeService.getRecipeCustomizedTotalCalories("recipeID", excludedIngredientsNames);
        assertEquals(response, expectedResponse);
    }
    
    /**
     * Tests that the getRecipeCustomizedTotalCalories method with an empty query throws an IllegalArgumentException.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method throws an IllegalArgumentException when
     * given an empty query.
     * </p>
     */
    @Test
    public void testGetRecipeCustomizedTotalCalories_EmptyQuery() {
    	when(spoonacularClient.getRecipeInformation("")).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.getRecipeCustomizedTotalCalories("", null);
        });
    }

    /**
     * Tests that the getRecipeCustomizedTotalCalories method with a null query throws an IllegalArgumentException.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method throws an IllegalArgumentException when
     * given a null query.
     * </p>
     */
    @Test
    public void testGetRecipeCustomizedTotalCalories_NullQuery() {
    	when(spoonacularClient.getRecipeInformation(null)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
        	recipeService.getRecipeCustomizedTotalCalories(null, null);
        });
    }

    /**
     * Tests that the getRecipeCustomizedTotalCalories method delegates the ClientException if it encounters one.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method throws a ClientException when its underlying
     * RecipeService encounters a client error.
     * </p>
     */
    @Test
    public void ttestGetRecipeCustomizedTotalCalories_ClientError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () -> {
        	recipeService.getRecipeCustomizedTotalCalories("", null);
        });
    }

    /**
     * Tests that the getRecipeCustomizedTotalCalories method delegates the ServerException if it encounters one.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method throws a ServerException when its underlying
     * RecipeService encounters a server error.
     * </p>
     */
    @Test
    public void testGetRecipeCustomizedTotalCalories_ServerError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(ServerException.class);
        assertThrows(ServerException.class, () -> {
        	recipeService.getRecipeCustomizedTotalCalories("", null);
        });
    }

    /**
     * Tests the that the getRecipeCustomizedTotalCalories method delegates the TimeoutException if it encounters one.
     * <p>
     * This test verifies that the getRecipeCustomizedTotalCalories method throws a TimeoutException when its underlying
     * RecipeService encounters a timeout error.
     * </p>
     */
    @Test
    public void testGetRecipeCustomizedTotalCalories_TimeoutError() {
        when(spoonacularClient.getRecipeInformation(anyString())).thenThrow(TimeoutException.class);
        assertThrows(TimeoutException.class, () -> {
        	recipeService.getRecipeCustomizedTotalCalories("", null);
        });
    }
}
