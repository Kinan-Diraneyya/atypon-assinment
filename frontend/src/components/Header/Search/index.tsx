'use client';
import { useRouter, useSearchParams  } from 'next/navigation';;
import { useEffect, useState } from 'react';
import style from './index.module.scss';

/**
 * Search Component
 * 
 * This component renders a search form with an input field. It allows users to search for recipes by typing a query and submitting the form.
 * 
 * @returns {JSX.Element} The Search component.
 */
export default function Search() {
    const router = useRouter();
    const searchParams = useSearchParams();
    const [searchQuery, setSearchQuery] = useState('');

    useEffect(() => {
        const queryParam = searchParams.get('q') || '';
        setSearchQuery(queryParam);
    }, [searchParams]);

    const handleInputChange = (value: string) => {
        setSearchQuery(value);
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        router.replace(`?q=${searchQuery}`);
    };

    return (
        <form 
            className={style.search} 
            onSubmit={handleSubmit}
            role="search"
            aria-label="Search recipes form"
        >
            <input 
                className={style.input} 
                placeholder='Search recipes' 
                onChange={e => handleInputChange(e.target.value)}
                value={searchQuery}
                type="search"
                aria-label="Search recipes" 
            />
        </form>
    );
}
