import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Header from '.';

jest.mock('./Logo', () => () => <div>Mocked Logo Component</div>);
jest.mock('./Search', () => () => <div>Mocked Search Component</div>);

describe('Header Component', () => {
  test('renders the Logo component', () => {
    render(<Header />);
    const logoElement = screen.getByText('Mocked Logo Component');
    expect(logoElement).toBeInTheDocument();
  });

  test('renders the Search component', () => {
    render(<Header />);
    const searchElement = screen.getByText('Mocked Search Component');
    expect(searchElement).toBeInTheDocument();
  });
});