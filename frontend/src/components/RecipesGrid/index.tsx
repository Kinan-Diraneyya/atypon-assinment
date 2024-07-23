import style from './index.module.scss';
import RecipeCard, { RecipeCardProps } from './RecipeCard';
import axios from 'axios';

/**
 * Interface representing the response structure for fetching recipes.
 * 
 * @interface
 * @property {number} page - The current page number.
 * @property {number} pageSize - The number of records per page.
 * @property {number} totalCount - The total number of records.
 * @property {Recipe[]} records - The list of recipe records.
 */
interface SearchRecipesResponse {
    page: number;
    pageSize: number;
    totalCount: number;
    records: Recipe[];
}

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

/**
 * Fetches recipes based on the given search query.
 * 
 * @async
 * @function fetchRecipes
 * @param {string} query - The search query string.
 * @returns {Promise<SearchRecipesResponse>} The response data containing the list of recipes.
 */
export async function fetchRecipes(query: string) {
    const response = await axios.get<SearchRecipesResponse>(`${process.env.NEXT_PUBLIC_BASE_API_URL}/search`, { params: { query } })
    return response.data;
}