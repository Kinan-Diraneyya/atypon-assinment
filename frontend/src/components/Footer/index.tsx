import style from './index.module.scss';

/**
 * Site footer.
 * 
 * Renders a ribbon with a few details about the site.
 * 
 * @returns {JSX.Element} The Footer component.
 */
export default function Footer() {
    return (
        <footer className={style.footer}>
            <small className={style.text}>Created on 21/07/2024 for Atypon online assignment</small>
        </footer>
    );
}