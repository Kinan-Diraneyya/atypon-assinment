import style from './index.module.scss';

/**
 * Ad Component
 * 
 * This component renders a dummy ad to simulate a real website
 * 
 * @returns {JSX.Element} The Ad component.
 */
export default function Ad() {
    return (
        <div className={style.ad} role="region" aria-labelledby="ad-title">
            <div className={style.content}>
                <h2 className={style.title} id="ad-title">
                    <span className={style.special}>Special</span>
                    <span className={style.recipes}>Recipes</span>
                </h2>
            </div>
        </div>
    );
}