import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Nutrients from './index';

describe('Nutrients Component', () => {
  const nutrients = [
    { name: 'Protein', amount: 10, unit: 'g' },
    { name: 'Carbohydrates', amount: 20, unit: 'g' },
  ];

  test('renders the Nutrients component with nutrients', () => {
    render(<Nutrients nutrients={nutrients} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toBeInTheDocument();
    expect(screen.getByRole('list')).toBeInTheDocument();
    expect(screen.getAllByRole('listitem')).toHaveLength(nutrients.length);
  });

  test('renders the Nutrients component with no nutrients', () => {
    render(<Nutrients nutrients={[]} />);
    expect(screen.getByText('No nutrients to show')).toBeInTheDocument();
  });
});
