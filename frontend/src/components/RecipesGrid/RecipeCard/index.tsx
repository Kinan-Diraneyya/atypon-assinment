import style from './index.module.scss';

/**
 * RecipeCardProps interface
 * 
 * This interface defines the properties expected by the RecipeCard component.
 * 
 * @interface
 * @property {string} imgSrc - The source URL of the image to be displayed in the card.
 * @property {string} title - The title of the recipe.
 * @property {string} id - The unique identifier for the recipe.
 */
export interface RecipeCardProps {
    imgSrc: string;
    title: string;
    id: string;
}

/**
 * RecipeCard Component
 * 
 * This component renders a recipe card, showing a picture and some details.
 * 
 * @param {RecipeCardProps} props - The properties for the RecipeCard component.
 * @returns {JSX.Element} The RecipeCard component.
 */
export default function RecipeCard({imgSrc, title, id}: RecipeCardProps) {
    return (
        <a className={style.card} aria-labelledby={`recipe-title-${id}`} role='link' href={'recipes/' + id}>
            <div className={style.more}>Show more</div>
            <img src={imgSrc} alt={title} className={style.image} role='img' />
            <div className={style.body}>
                <h3 className={style.title} id={`recipe-title-${id}`}>{title}</h3>
                <div className={style.id}>#{id}</div>
            </div>
        </a>
    );
}