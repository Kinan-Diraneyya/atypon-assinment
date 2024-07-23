import style from './index.module.scss';

/**
 * Interface representing the properties for the Ingredient component.
 * 
 * @interface
 * @property {string} imageName - The name of the image file for the ingredient.
 * @property {string} name - The name of the ingredient.
 * @property {string} id - The unique identifier for the ingredient.
 * @property {string} unit - The unit of measurement for the ingredient amount.
 * @property {number} amount - The amount of the ingredient.
 */
export interface IngredientProps {
    imageName: string;
    name: string;
    id: string;
    unit: string;
    amount: number;
}

/**
 * The Ingredient component.
 * 
 * This component displays detailed information about an ingredient.
 * 
 * @component
 * @param {IngredientProps} props - The properties for the Ingredient component.
 * @returns {JSX.Element} The rendered Ingredient component.
 */
export default function Ingredient({ imageName, name, id, unit, amount }: IngredientProps) {
    return (
        <div 
            className={style.ingredient} 
            role="region" 
            aria-labelledby={`ingredient-title-${id}`} 
            aria-describedby={`ingredient-description-${id}`}
        >
            <img src={'https://img.spoonacular.com/ingredients_250x250/' + imageName} alt={name} className={style.image} />
            <div className={style.body}>
                <h3 className={style.title} id={`ingredient-title-${id}`}>{name}</h3>
                <div className={style.description} id={`ingredient-description-${id}`}>{`${amount} ${unit}`}</div>
                <div className={style.id}>#{id}</div>
            </div>
        </div>
    );
}