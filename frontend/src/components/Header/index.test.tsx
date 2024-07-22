import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import Header from './index';
import { HeaderProps } from './index';

jest.mock('./Logo', () => () => <div>Logo Component</div>);
jest.mock('./Search', () => (props: HeaderProps) => (
    <form
        role="search"
        onSubmit={props.search}
    >
        <input
            placeholder="Search recipes"
            onChange={e => props.onQueryChange(e.target.value)}
            value={props.query}
        />
    </form>
));

describe('Header Component', () => {
    const mockOnQueryChange = jest.fn();
    const mockSearch = jest.fn();
    const props: HeaderProps = {
        onQueryChange: mockOnQueryChange,
        query: 'test query',
        search: mockSearch,
    };

    test('renders Logo and Search components', () => {
        render(<Header {...props} />);
        expect(screen.getByText('Logo Component')).toBeInTheDocument();

        const inputElement = screen.getByPlaceholderText('Search recipes');
        expect(inputElement).toBeInTheDocument();

        fireEvent.change(inputElement, { target: { value: 'new query' } });
        expect(mockOnQueryChange).toHaveBeenCalledWith('new query');
    });

    test('calls onQueryChange when input value changes', () => {
        render(<Header {...props} />);
        const inputElement = screen.getByPlaceholderText('Search recipes');
        fireEvent.change(inputElement, { target: { value: 'new query' } });
        expect(mockOnQueryChange).toHaveBeenCalledWith('new query');
    });

    test('calls search function on form submit', () => {
        render(<Header {...props} />);
        fireEvent.submit(screen.getByRole('search'));
        expect(mockSearch).toHaveBeenCalled();
    });
});
