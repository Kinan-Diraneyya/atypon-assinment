import axios from "axios";

/**
 * Interface representing the response structure for fetching recipes.
 * 
 * @interface
 * @property {number} page - The current page number.
 * @property {number} pageSize - The number of records per page.
 * @property {number} totalCount - The total number of records.
 * @property {Recipe[]} records - The list of recipe records.
 */
export interface SearchRecipesResponse {
    page: number;
    pageSize: number;
    totalResults: number;
    records: Recipe[];
}

/**
 * Fetches recipes based on the given search query.
 * 
 * @async
 * @function fetchRecipes
 * @param {string} query - The search query string.
 * @returns {Promise<SearchRecipesResponse>} The response data containing the list of recipes.
 */
export async function fetchRecipes(query: string, page = 0, pageSize = 10) {
    const response = await axios.get<SearchRecipesResponse>(`${process.env.BASE_API_URL}/search`, { params: { query, page, pageSize } })
    return response.data;
}


/**
 * Interface representing the structure of the response for fetching recipe information.
 * 
 * @interface
 * @property {string} title - The title of the recipe.
 * @property {string} image - The image file name, wich must be concatenated with a base URL to fetch the image.
 * @property {number} readyInMinutes - The time required to prepare the recipe.
 * @property {number} servings - The number of servings the recipe yields.
 * @property {number} healthScore - The health score of the recipe.
 * @property {string[]} dishTypes - The types of dishes the recipe falls under.
 * @property {string} summary - A summary of the recipe.
 * @property {Ingredient[]} ingredients - The list of ingredients for the recipe.
 * @property {Nutrient[]} nutrients - The list of nutrients for the recipe.
 */
export interface GetRecipeInformationResponse {
    title: string;
    image: string;
    readyInMinutes: number;
    servings: number;
    healthScore: number;
    dishTypes: string[];
    summary: string;
    ingredients: Ingredient[];
    nutrients: Nutrient[];
}

/**
 * Fetches detailed information about a recipe based on the provided recipe ID.
 * 
 * @async
 * @function fetchRecipeInformation
 * @param {string} id - The unique identifier for the recipe.
 * @returns {Promise<GetRecipeInformationResponse>} The response data containing detailed information about the recipe.
 */
export async function fetchRecipeInformation(id: string) {
    const response = await axios.get<GetRecipeInformationResponse>(`${process.env.BASE_API_URL}/${id}/information`);
    return response.data;
}