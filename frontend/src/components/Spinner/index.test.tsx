import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Spinner from './index';

describe('Spinner Component', () => {
  test('renders the Spinner component', () => {
    render(<Spinner />);
    const logoElement = screen.getByRole('status');
    expect(logoElement).toBeInTheDocument();
  });
});