import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import RecipeCard from './index';

describe('RecipeCard Component', () => {
  const props = {
    imgSrc: 'https://example.com/image.jpg',
    title: 'Delicious Recipe',
    id: '123',
  };

  test('renders the RecipeCard component', () => {
    render(<RecipeCard {...props} />);
    const cardElement = screen.getByRole('link');
    expect(cardElement).toBeInTheDocument();
  });

  test('renders the image with correct src and alt attributes', () => {
    render(<RecipeCard {...props} />);
    const imageElement = screen.getByRole('img');
    expect(imageElement).toHaveAttribute('src', props.imgSrc);
    expect(imageElement).toHaveAttribute('alt', props.title);
  });

  test('renders the title correctly', () => {
    render(<RecipeCard {...props} />);
    const titleElement = screen.getByText(props.title);
    expect(titleElement).toBeInTheDocument();
  });

  test('renders the ID correctly', () => {
    render(<RecipeCard {...props} />);
    const idElement = screen.getByText(`#${props.id}`);
    expect(idElement).toBeInTheDocument();
  });

  test('renders the Show more text', () => {
    render(<RecipeCard {...props} />);
    const showMoreElement = screen.getByText('Show more');
    expect(showMoreElement).toBeInTheDocument();
  });
});
