'use client';
import Logo from './Logo';
import style from './index.module.scss';
import Search, { SearchProps } from './Search';

/**
 * HeaderProps interface
 * 
 * HeaderProps extends SearchProps as it.
 * 
 * @interface
 */
export type HeaderProps = SearchProps;

/**
 * Header Component
 * 
 * This component renders site header, including its logo and search bar
 * 
 * @param {HeaderProps} props - The properties for the Header component.
 * @returns {JSX.Element} The Header component.
 */
export default function Header(props: HeaderProps) {
    return (
        <header className={style.header}>
            <div className={style.activeArea}>
                <Logo />
                <Search {...props} />
            </div>
        </header>
    );
}