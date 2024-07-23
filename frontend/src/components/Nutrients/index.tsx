import style from './index.module.scss';

/**
 * Interface representing the properties for the Nutrients component.
 * 
 * @interface
 * @property {Nutrient[]} nutrients - The list of nutrients to show
 */
interface NutrientsProps {
    nutrients: Nutrient[];
}

/**
 * Nutrients List.
 * 
 * Renders a list of nutrients, or paragraph saying non exist if the list is empty.
 * 
 * @returns {JSX.Element} The Nutrients component.
 */
export default function Nutrients({ nutrients }: NutrientsProps) {
    return (
        <div className={style.nutrients} role="region" aria-labelledby="nutrients-heading">
            <h3 className={style.title} id="nutrients-heading">Nutrients</h3>
            {
                nutrients && nutrients.length > 0 ?
                    (
                        <ul className={style.list} role="list">
                            {
                                nutrients.map(nutrient => {
                                    return (
                                        <li
                                            className={style.item}
                                            key={nutrient.name}
                                            role="listitem"
                                        >
                                            {`${nutrient.name}: ${nutrient.amount} ${nutrient.unit}`}
                                        </li>
                                    );
                                })
                            }
                        </ul>
                    ) :
                    <p className={style.empty}>No nutrients to show</p>
            }
        </div>
    );
}