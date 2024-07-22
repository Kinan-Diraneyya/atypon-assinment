import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Logo from './index';

describe('Logo Component', () => {
  test('renders without crashing', () => {
    render(<Logo />);
    const logoElement = screen.getByText('Spoonacular Recipe Client');
    expect(logoElement).toBeInTheDocument();
  });

  test('matches snapshot', () => {
    const { asFragment } = render(<Logo />);
    expect(asFragment()).toMatchSnapshot();
  });
});