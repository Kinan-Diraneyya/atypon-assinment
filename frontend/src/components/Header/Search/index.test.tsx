// Search.test.js

import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Search from './index';
import '@testing-library/jest-dom';
import { useRouter, useSearchParams } from 'next/navigation';

jest.mock('next/navigation', () => ({
  useRouter: jest.fn(),
  useSearchParams: jest.fn(),
}));

describe('Search Component', () => {
  const mockReplace = jest.fn();
  const mockSearchParams = {
    get: jest.fn(),
  };

  beforeEach(() => {
    (useRouter as jest.Mock).mockReturnValue({ replace: mockReplace });
    (useSearchParams as jest.Mock).mockReturnValue(mockSearchParams);
    mockSearchParams.get.mockReturnValue('');
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  test('renders correctly with initial state', () => {
    render(<Search />);
    const inputElement = screen.getByPlaceholderText('Search recipes');
    expect(inputElement).toBeInTheDocument();
    expect(inputElement).toHaveValue('');
  });

  test('updates input value on change', () => {
    render(<Search />);
    const inputElement = screen.getByPlaceholderText('Search recipes');
    fireEvent.change(inputElement, { target: { value: 'new query' } });
    expect(inputElement).toHaveValue('new query');
  });

  test('updates query string on form submit', () => {
    render(<Search />);
    const inputElement = screen.getByPlaceholderText('Search recipes');
    fireEvent.change(inputElement, { target: { value: 'new query' } });

    const formElement = screen.getByRole('search');
    fireEvent.submit(formElement);

    expect(mockReplace).toHaveBeenCalledWith('?q=new query');
  });
});