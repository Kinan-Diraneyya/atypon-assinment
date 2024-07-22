import style from './index.module.scss';

/**
 * Separator Component
 * 
 * This component renders a horizontal line and nothing else.
 * 
 * @returns {JSX.Element} The Separator component.
 */
export default function Separator() {
    return <div className={style.separator} role="separator"></div>;
}