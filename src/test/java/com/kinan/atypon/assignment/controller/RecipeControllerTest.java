package com.kinan.atypon.assignment.controller;

import com.kinan.atypon.assignment.exception.ClientException;
import com.kinan.atypon.assignment.exception.ServerException;
import com.kinan.atypon.assignment.exception.TimeoutException;
import com.kinan.atypon.assignment.model.GetRecipeNutritionsResult;
import com.kinan.atypon.assignment.model.GetRecipeTotalCaloriesResult;
import com.kinan.atypon.assignment.model.Nutrient;
import com.kinan.atypon.assignment.model.Recipe;
import com.kinan.atypon.assignment.model.SearchRecipesResult;
import com.kinan.atypon.assignment.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSearchRecipes_Success() throws Exception {
        String query = "pasta";
    	Recipe[] mockRecipesArray = {
                new Recipe("1", "a", "x", "jpg"),
                new Recipe("2", "b", "y", "png")
        };
    	List<Recipe> mockRecipes = Arrays.asList(mockRecipesArray);
        SearchRecipesResult mockResult = new SearchRecipesResult(1, 2, 100, mockRecipes);
        when(recipeService.searchRecipes(query)).thenReturn(mockResult);

        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.pageSize").value(2))
                .andExpect(jsonPath("$.totalResults").value(100))
                .andExpect(jsonPath("$.records", hasSize(2)))
                .andExpect(jsonPath("$.records[*].id", containsInAnyOrder("1", "2")))
                .andExpect(jsonPath("$.records[*].title", containsInAnyOrder("a", "b")))
                .andExpect(jsonPath("$.records[*].image", containsInAnyOrder("x", "y")))
                .andExpect(jsonPath("$.records[*].imageType", containsInAnyOrder("jpg", "png")));
    }

    @Test
    public void testSearchRecipes_BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRecipeNutritions_Success() throws Exception {
        String recipeID = "1";
        
    	Nutrient[] mockTotalNutrientsArray = {
                new Nutrient("Calories", "kcal", 10d),
                new Nutrient("b", "g", 2d),
        };
    	List<Nutrient> mockTotalNutrients = Arrays.asList(mockTotalNutrientsArray);
        GetRecipeNutritionsResult mockResult = new GetRecipeNutritionsResult(mockTotalNutrients);

        when(recipeService.getRecipeNutritions(recipeID)).thenReturn(mockResult);

        mockMvc.perform(get("/api/v1/recipes/{recipeID}/nutritions", recipeID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nutrients", hasSize(2)))
                .andExpect(jsonPath("$.nutrients[*].name", containsInAnyOrder("Calories", "b")))
                .andExpect(jsonPath("$.nutrients[*].unit", containsInAnyOrder("kcal", "g")))
                .andExpect(jsonPath("$.nutrients[*].amount", containsInAnyOrder(10d, 2d)));
    }

    @Test
    public void testGetRecipeNutritions_BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/recipes//nutritions"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomizedRecipeCalories_Success() throws Exception {
        String recipeID = "1";
        List<String> exclude = Arrays.asList("ingredient1", "ingredient2");
        GetRecipeTotalCaloriesResult mockResult = new GetRecipeTotalCaloriesResult(10d);

        when(recipeService.getRecipeCustomizedTotalCalories(recipeID, exclude)).thenReturn(mockResult);

        mockMvc.perform(get("/api/v1/recipes/{recipeID}/customized-calories", recipeID)
                .param("exclude", "ingredient1,ingredient2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCalories").value(10d));
    }

    @Test
    public void testGetCustomizedRecipeCalories_BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/recipes//customized-calories")
                .param("exclude", "ingredient1,ingredient2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testHandleClientException() throws Exception {
        String query = "pasta";
        when(recipeService.searchRecipes(query)).thenThrow(new ClientException("Client error"));

        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", query))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testHandleServerException() throws Exception {
        String query = "pasta";
        when(recipeService.searchRecipes(query)).thenThrow(new ServerException("Server error"));

        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", query))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testHandleTimeoutException() throws Exception {
        String query = "pasta";
        when(recipeService.searchRecipes(query)).thenThrow(new TimeoutException("Timeout error"));

        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", query))
                .andExpect(status().isRequestTimeout());
    }

    @Test
    public void testHandleGeneralException() throws Exception {
        String query = "pasta";
        when(recipeService.searchRecipes(query)).thenThrow(new RuntimeException("General error"));

        mockMvc.perform(get("/api/v1/recipes/search")
                .param("query", query))
                .andExpect(status().isInternalServerError());
    }
}
