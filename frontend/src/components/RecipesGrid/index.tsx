import { fetchRecipes, SearchRecipesResponse } from '@/utilities/api';
import style from './index.module.scss';
import RecipeCard, { RecipeCardProps } from './RecipeCard';
import Pagination from './Pagination';
import { Suspense } from 'react';
import Spinner from '../Spinner';

const PAGE_SIZE = 12;

/**
 * Interface representing the props for the RecipesGrid component.
 * 
 * @interface
 * @property {string | string[]} [query] - The search query string or array of query strings.
 */
interface RecipesGridProps {
    query?: string;
    page?: string
}

/**
 * The RecipesGrid component
 * 
 * This component renders a grid of recipe cards based on the search query, or string saying that none were found.
 * 
 * @param {RecipesGridProps} props - The props for the RecipesGrid component.
 * @returns {JSX.Element} The RecipesGrid component.
 */
export default async function RecipesGrid({ query, page = '1' }: RecipesGridProps) {
    let recipeCards: RecipeCardProps[] = [];
    let responseData: SearchRecipesResponse | undefined= undefined;
    if (query) {
        responseData = await fetchRecipes(query, Number.parseInt(page) - 1, PAGE_SIZE);
        recipeCards = responseData.records.map(recipe => ({ ...recipe, imgSrc: recipe.image }));
    }

    return (
        <div className={style.recipesGrid} role="region" aria-labelledby="recipes-heading">
            <h2 className={style.title} id="recipes-heading">Recipes</h2>
            {
                responseData && recipeCards.length > 0 ?
                    (
                        <>
                        <div className={style.grid} role="list">
                            {recipeCards.map(card => <RecipeCard key={card.id} {...card} />)}
                        </div>
                        <Suspense fallback={<Spinner />}>
                            <Pagination pageCount={responseData.totalResults / responseData.pageSize} />
                        </Suspense>
                    </>
                    ) :
                    <p>No recipes to show</p>
            }
        </div>
    );
}