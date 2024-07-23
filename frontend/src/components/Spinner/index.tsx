import style from './index.module.scss';

/**
 * A Loading spinner.
 * 
 * @returns {JSX.Element} The Loading component.
 */
export default function Spinner() {
    return <div className={style.spinner} role="status" aria-live="polite" aria-label="Loading"></div>;
}