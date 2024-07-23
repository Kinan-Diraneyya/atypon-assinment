import Logo from './Logo';
import style from './index.module.scss';
import Search from './Search';
import Spinner from '../Spinner';
import { Suspense } from 'react';

/**
 * Header Component
 * 
 * This component renders site header, including its logo and search bar
 * 
 * @returns {JSX.Element} The Header component.
 */
export default function Header() {
    return (
        <header className={style.header}>
            <div className={style.activeArea}>
                <Logo />
                <Suspense fallback={<Spinner />}>
                    <Search />
                </Suspense>
            </div>
        </header>
    );
}