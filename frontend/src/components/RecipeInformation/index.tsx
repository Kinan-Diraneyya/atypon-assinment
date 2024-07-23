import style from './index.module.scss';
import RecipeSummary from '../RecipeSummary';
import axios from 'axios';
import Ingredients from '../Ingredients';
import Nutrients from '../Nutrients';

/**
 * Interface representing the properties for the RecipeInformation component.
 * 
 * @interface
 * @property {string} id - The unique identifier for the recipe.
 */
interface RecipeInformationProps {
    id: string;
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
interface GetRecipeInformationResponse {
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
 * Component to display detailed information about a recipe.
 * 
 * This component fetches recipe information based on the provided recipe ID and displays the summary, ingredients, and nutrients of the recipe.
 * 
 * @component
 * @async
 * @param {RecipeInformationProps} props - The properties for the RecipeInformation component.
 * @returns {Promise<JSX.Element>} The rendered RecipeInformation component.
 */
export default async function RecipeInformation({id}: RecipeInformationProps) {
    const responseData = await fetchRecipeInformation(id);

    return (
        <div className={style.recipeInformation}>
            <RecipeSummary {...responseData} />
            <Ingredients ingredients={responseData.ingredients.map(ingredient => ({...ingredient, imageName: ingredient.image}))} />
            <Nutrients nutrients={responseData.nutrients} />
        </div>
    );
}

/**
 * Fetches detailed information about a recipe based on the provided recipe ID.
 * 
 * @async
 * @function fetchRecipeInformation
 * @param {string} id - The unique identifier for the recipe.
 * @returns {Promise<GetRecipeInformationResponse>} The response data containing detailed information about the recipe.
 */
async function fetchRecipeInformation(id: string) {
    const response = await axios.get<GetRecipeInformationResponse>(`${process.env.NEXT_PUBLIC_BASE_API_URL}/${id}/information`);
    return response.data;
}