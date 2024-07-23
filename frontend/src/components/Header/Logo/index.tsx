import style from './index.module.scss';

/**
 * Application logo.
 * 
 * Renders text that represents the application's logo
 * 
 * @returns {JSX.Element} The Logo component.
 */
export default function Logo() {
    return <a href='/' className={style.logo}>Spoonacular Recipe Client</a>;
}