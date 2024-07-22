import style from './index.module.scss';

/**
 * SearchProps interface
 * 
 * This interface defines the properties expected by the Search component.
 * 
 * @interface
 * @property {function(string): void} onQueryChange - Callback function to handle changes in the search query.
 * @property {string} query - The current search query string.
 * @property {function(): void} search - Callback function to execute the search.
 */
export interface SearchProps {
    onQueryChange: (value: string) => void;
    query: string;
    search: () => void;
}

/**
 * Search Component
 * 
 * This component renders a search form with an input field. It allows users to search for recipes by typing a query and submitting the form.
 * 
 * @param {SearchProps} props - The properties for the Search component.
 * @returns {JSX.Element} The Search component.
 */
export default function Search({onQueryChange, query, search}: SearchProps) {
    return (
        <form 
            className={style.search} 
            onSubmit={e => {e.preventDefault(); search()}}
            role="search"
            aria-label="Search recipes form"
        >
            <input 
                className={style.input} 
                placeholder='Search recipes' 
                onChange={e => onQueryChange(e.target.value)} value={query}
                type="search"
                aria-label="Search recipes" 
            />
        </form>
    );
}
