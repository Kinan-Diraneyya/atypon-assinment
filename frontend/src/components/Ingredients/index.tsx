import style from './index.module.scss';
import Ingredient, { IngredientProps } from './Ingredient';

/**
 * Interface representing the properties for the Ingredients component.
 * 
 * @interface
 * @property {IngredientProps[]} ingredients - The list of ingredients to display.
 */
export interface IngredientsProps {
    ingredients: IngredientProps[];
}

/**
 * Component to display a list of ingredients.
 * 
 * This component displays a list of Ingredient components. If there are no ingredients to display, it shows a message indicating that there are no ingredients to show.
 * 
 * @component
 * @param {IngredientsProps} props - The properties for the Ingredients component.
 * @returns {JSX.Element} The rendered Ingredients component.
 */
export default function Ingredients({ ingredients }: IngredientsProps) {
    return (
        <div className={style.ingredients} role="region" aria-labelledby="ingredients-heading">
            <h3 className={style.title} id="ingredients-heading">Ingredients</h3>
            {
                ingredients && ingredients.length > 0 ?
                (
                    <ul className={style.list} role="list">
                        {ingredients.map(ingredient => (
                            <li key={ingredient.id} role="listitem">
                                <Ingredient {...ingredient} />
                            </li>
                        ))}
                    </ul>
                ) :
                <p className={style.empty}>No ingredients to show</p>
            }
        </div>
    );
}