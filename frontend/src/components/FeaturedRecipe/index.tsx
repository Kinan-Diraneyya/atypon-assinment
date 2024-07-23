import { fetchRecipeInformation } from "@/utilities/api";
import RecipeSummary from "../RecipeSummary";
import style from './index.module.scss';

/**
 * The FeaturedRecipe component
 * 
 * This component shows a dummy featured recipe.
 * 
 * @returns {JSX.Element} The RecipesGrid component.
 */
export default async function FeaturedRecipe() {
    const responseData = await fetchRecipeInformation('716429');
    return (
        <div className={style.featured} role="region" aria-labelledby="featured-recipe-title">
            <h3 className={style.title} id="featured-recipe-title">{'Today\'s Featured Recipe'}</h3>
            <RecipeSummary {...responseData} />
        </div>
    );
}