import { fetchRecipes } from '@/utilities/api';
import style from './index.module.scss';
import RecipeCard, { RecipeCardProps } from './RecipeCard';

/**
 * Interface representing the props for the RecipesGrid component.
 * 
 * @interface
 * @property {string | string[]} [query] - The search query string or array of query strings.
 */
interface RecipesGridProps {
    query?: string | string[];
}

/**
 * The RecipesGrid component
 * 
 * This component renders a grid of recipe cards based on the search query, or string saying that none were found.
 * 
 * @param {RecipesGridProps} props - The props for the RecipesGrid component.
 * @returns {JSX.Element} The RecipesGrid component.
 */
export default async function RecipesGrid({ query }: RecipesGridProps) {
    let recipeCards: RecipeCardProps[] = [];
    if (query && (!Array.isArray(query) || query.length > 0)) {
        query = Array.isArray(query) ? query[0] : query;
        const responseData = await fetchRecipes(query);
        recipeCards = responseData.records.map(recipe => ({ ...recipe, imgSrc: recipe.image }));
    }

    return (
        <div className={style.recipesGrid} role="region" aria-labelledby="recipes-heading">
            <h2 className={style.title} id="recipes-heading">Recipes</h2>
            {
                recipeCards.length > 0 ?
                    <div className={style.grid} role="list">
                        {recipeCards.map(card => <RecipeCard key={card.id} {...card} />)}
                    </div> :
                    <p>No recipes to show</p>
            }
        </div>
    );
}