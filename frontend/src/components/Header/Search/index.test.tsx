// Search.test.js

import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Search from './index';
import '@testing-library/jest-dom';

describe('Search Component', () => {
  const mockOnQueryChange = jest.fn();
  const mockSearch = jest.fn();
  const query = 'test query';

  beforeEach(() => {
    render(
      <Search
        onQueryChange={mockOnQueryChange}
        query={query}
        search={mockSearch}
      />
    );
  });

  test('renders the search input with the correct placeholder and value', () => {
    const inputElement = screen.getByPlaceholderText('Search recipes');
    expect(inputElement).toBeInTheDocument();
    expect(inputElement).toHaveValue(query);
  });

  test('calls onQueryChange when the input value changes', () => {
    const inputElement = screen.getByPlaceholderText('Search recipes');
    fireEvent.change(inputElement, { target: { value: 'new query' } });
    expect(mockOnQueryChange).toHaveBeenCalledWith('new query');
  });

  test('calls search function when the form is submitted', () => {
    const formElement = screen.getByRole('search');
    fireEvent.submit(formElement);
    expect(mockSearch).toHaveBeenCalled();
  });
});
