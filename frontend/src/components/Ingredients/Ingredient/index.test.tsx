import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Ingredient, { IngredientProps } from './index';

describe('Ingredient Component', () => {
  const props: IngredientProps = {
    imageName: 'apple.jpg',
    name: 'Apple',
    id: '12345',
    unit: 'grams',
    amount: 150,
  };

  test('renders the Ingredient component', () => {
    render(<Ingredient {...props} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toBeInTheDocument();
  });

  test('renders the image with correct src and alt attributes', () => {
    render(<Ingredient {...props} />);
    const imageElement = screen.getByRole('img');
    expect(imageElement).toHaveAttribute('src', 'https://img.spoonacular.com/ingredients_250x250/apple.jpg');
    expect(imageElement).toHaveAttribute('alt', props.name);
  });

  test('renders the name correctly', () => {
    render(<Ingredient {...props} />);
    const nameElement = screen.getByText(props.name);
    expect(nameElement).toBeInTheDocument();
    expect(nameElement).toHaveAttribute('id', `ingredient-title-${props.id}`);
  });

  test('renders the amount and unit correctly', () => {
    render(<Ingredient {...props} />);
    const descriptionElement = screen.getByText(`${props.amount} ${props.unit}`);
    expect(descriptionElement).toBeInTheDocument();
    expect(descriptionElement).toHaveAttribute('id', `ingredient-description-${props.id}`);
  });

  test('renders the ID correctly', () => {
    render(<Ingredient {...props} />);
    const idElement = screen.getByText(`#${props.id}`);
    expect(idElement).toBeInTheDocument();
  });

  test('applies correct ARIA attributes', () => {
    render(<Ingredient {...props} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toHaveAttribute('aria-labelledby', `ingredient-title-${props.id}`);
    expect(regionElement).toHaveAttribute('aria-describedby', `ingredient-description-${props.id}`);
  });
});
