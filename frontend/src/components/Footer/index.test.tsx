import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Footer from './index';

describe('Footer Component', () => {
  test('renders without crashing', () => {
    render(<Footer />);
    const logoElement = screen.getByText('Created on 21/07/2024 for Atypon online assignment');
    expect(logoElement).toBeInTheDocument();
  });

  test('matches snapshot', () => {
    const { asFragment } = render(<Footer />);
    expect(asFragment()).toMatchSnapshot();
  });
});