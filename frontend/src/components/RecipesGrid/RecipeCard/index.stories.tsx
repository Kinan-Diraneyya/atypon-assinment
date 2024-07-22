import React from 'react';
import RecipeCard from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof RecipeCard> = {
    component: RecipeCard,
  };
   
  export default meta;
  type Story = StoryObj<typeof RecipeCard>;
   
  export const Primary: Story = {
    args: {
      id: '1',
      title: 'Pasta',
      imgSrc: 'https://img.spoonacular.com/recipes/634629-312x231.jpg'
    },
  };