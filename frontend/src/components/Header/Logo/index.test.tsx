import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Logo from './index';

describe('Logo Component', () => {
  test('matches snapshot', () => {
    const { asFragment } = render(<Logo />);
    expect(asFragment()).toMatchSnapshot();
  });

  test('renders the Logo component and ensures it navigates to the root', () => {
    render(<Logo />);
    const logoElement = screen.getByText('Spoonacular Recipe Client');
    expect(logoElement).toBeInTheDocument();
    expect(logoElement).toHaveAttribute('href', '/');
  });
});