'use client';
import style from './index.module.scss';
import ReactPaginate from 'react-paginate';
import { useRouter, useSearchParams } from 'next/navigation';

/**
 * Interface representing the props for the Pagination component.
 * 
 * @interface
 * @property {number} pageCount - The total page count to show
 */
interface PaginationProps {
    pageCount: number;
}

/**
 * The Pagination component
 * 
 * This component renders a pagination that receives and reflects its values in the query param.
 * 
 * @param {RecipesGridProps} props - The props for the Pagination component.
 * @returns {JSX.Element} The Pagination component.
 */
export default function Pagination({ pageCount }: PaginationProps) {
    const router = useRouter();
    const searchParams = useSearchParams();

    const changePage = ({ selected }: { selected: number }) => {
        const currentPath = window.location.pathname;
        const currentQuery = new URLSearchParams(window.location.search);
        currentQuery.set('page', (selected + 1).toString());
        router.replace(`${currentPath}?${currentQuery.toString()}`, { scroll: false });
    }
    
    const page = Number.parseInt(searchParams.get('page') ?? '0') + 1;

    return (
        <ReactPaginate
            forcePage={page}
            breakLabel="..."
            nextLabel="⮞"
            onPageChange={changePage}
            pageCount={Math.ceil(pageCount)}
            previousLabel="⮜"
            renderOnZeroPageCount={null}
            className={style.pagination}
            pageLinkClassName={style.page}
            nextLinkClassName={style.page}
            previousLinkClassName={style.page}
        />
    );
}

