import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import RecipeSummary, { RecipeSummaryProps } from './index';

describe('RecipeSummary Component', () => {
  const props: RecipeSummaryProps = {
    title: 'Spaghetti Bolognese',
    image: 'https://example.com/spaghetti.jpg',
    readyInMinutes: 45,
    servings: 4,
    healthScore: 85,
    dishTypes: ['Main Course', 'Dinner'],
    summary: 'A classic Italian pasta dish with rich, meaty sauce.',
  };

  test('renders the RecipeSummary component', () => {
    render(<RecipeSummary {...props} />);
    const regionElement = screen.getByRole('region');
    expect(regionElement).toBeInTheDocument();
    expect(regionElement).toHaveAttribute('aria-labelledby', 'recipe-title');
    expect(regionElement).toHaveAttribute('aria-describedby', 'recipe-summary');
  });

  test('renders the image with correct src and alt attributes', () => {
    render(<RecipeSummary {...props} />);
    const imageElement = screen.getByRole('img');
    expect(imageElement).toHaveAttribute('alt', props.title);
  });

  test('renders the title correctly', () => {
    render(<RecipeSummary {...props} />);
    const titleElement = screen.getByText(props.title);
    expect(titleElement).toBeInTheDocument();
    expect(titleElement).toHaveAttribute('id', 'recipe-title');
  });

  test('renders the summary correctly', () => {
    render(<RecipeSummary {...props} />);
    const summaryElement = screen.getByText(props.summary);
    expect(summaryElement).toBeInTheDocument();
    expect(summaryElement).toHaveAttribute('id', 'recipe-summary');
  });

  test('renders the preparation time and servings correctly', () => {
    render(<RecipeSummary {...props} />);
    expect(screen.getByText(`⏲ ${props.readyInMinutes} Minutes`)).toBeInTheDocument();
    expect(screen.getByText(`🍲 ${props.servings} Servings`)).toBeInTheDocument();
  });

  test('renders the dish types correctly', () => {
    render(<RecipeSummary {...props} />);
    props.dishTypes.forEach(type => {
      expect(screen.getByText(type)).toBeInTheDocument();
    });
  });
});
