import Image from 'next/image';
import style from './index.module.scss';

/**
 * Interface representing the properties for the RecipeSummary component.
 * 
 * @interface
 * @property {string} title - The title of the recipe.
 * @property {string} image - The URL of the recipe image.
 * @property {number} readyInMinutes - The time required to prepare the recipe.
 * @property {number} servings - The number of servings the recipe yields.
 * @property {number} healthScore - The health score of the recipe.
 * @property {string[]} dishTypes - The types of dishes the recipe falls under.
 * @property {string} summary - A summary of the recipe.
 */
export interface RecipeSummaryProps {
    title: string;
    image: string;
    readyInMinutes: number;
    servings: number;
    healthScore: number;
    dishTypes: string[];
    summary: string;
}

/**
 * The RecipeSummary component
 * 
 * This component displays the summary of a recipe.
 * 
 * @component
 * @param {RecipeSummaryProps} props - The properties for the RecipeSummary component.
 * @returns {JSX.Element} The rendered RecipeSummary component.
 */
export default function RecipeSummary({ title, image, readyInMinutes, servings, dishTypes, summary }: RecipeSummaryProps) {
    return (
        <div className={style.recipe} role="region" aria-labelledby="recipe-title" aria-describedby="recipe-summary">
            <Image
                priority={true}
                width={556}
                height={370}
                className={style.image} src={image}
                alt={title}
                role="img"
            />
            <div className={style.details}>
                <h2 className={style.title} id="recipe-title">{title}</h2>
                <p className={style.summary} id="recipe-summary">{summary}</p>
                <div className={style.specs}>
                    <div>{`⏲ ${readyInMinutes} Minutes`}</div>
                    <div>{`🍲 ${servings} Servings`}</div>
                </div>
                <div className={style.types}>
                    <h3 className={style.title}>Diash Types</h3>
                    <ul className={style.list} role="list">
                        {dishTypes.map(type => <li className={style.type} key={type} role="listitem">{type}</li>)}
                    </ul>
                </div>
            </div>
        </div>
    );
}