import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Ingredients, { IngredientsProps } from './index';

jest.mock('./Ingredient', () => function Logo() { return <div>Mocked Ingredient Component</div>});

describe('Ingredients Component', () => {
  const propsWithIngredients: IngredientsProps = {
    ingredients: [
      { imageName: 'apple.jpg', name: 'Apple', id: '1', unit: 'grams', amount: 150 },
      { imageName: 'banana.jpg', name: 'Banana', id: '2', unit: 'grams', amount: 120 },
    ],
  };

  const propsWithoutIngredients: IngredientsProps = {
    ingredients: [],
  };

  test('renders the Ingredients component with ingredients', () => {
    render(<Ingredients {...propsWithIngredients} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toBeInTheDocument();
    expect(regionElement).toHaveAttribute('aria-labelledby', 'ingredients-heading');
    expect(screen.getByRole('heading', { name: /Ingredients/i })).toBeInTheDocument();
    expect(screen.getByRole('list')).toBeInTheDocument();
    expect(screen.getAllByRole('listitem')).toHaveLength(propsWithIngredients.ingredients.length);
  });

  test('renders the Ingredients component with no ingredients', () => {
    render(<Ingredients {...propsWithoutIngredients} />);
    expect(screen.getByText('No ingredients to show')).toBeInTheDocument();
  });

  test('applies correct ARIA attributes', () => {
    render(<Ingredients {...propsWithIngredients} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toHaveAttribute('aria-labelledby', 'ingredients-heading');
    expect(screen.getByRole('heading', { name: /Ingredients/i })).toHaveAttribute('id', 'ingredients-heading');
  });
});
