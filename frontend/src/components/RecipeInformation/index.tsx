import style from './index.module.scss';
import RecipeSummary from '../RecipeSummary';
import Ingredients from '../Ingredients';
import Nutrients from '../Nutrients';
import { fetchRecipeInformation } from '@/utilities/api';

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
