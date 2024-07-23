import React from 'react';
import Ingredients from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Ingredients> = {
  component: Ingredients,
};

export default meta;
type Story = StoryObj<typeof Ingredients>;

export const Empty: Story = {
  args: {},
};

export const Full: Story = {
  args: {
    ingredients: [
      {
        id: '1',
        name: 'butter',
        imageName: 'butter-sliced.jpg',
        amount: 1,
        unit: 'tbsp'
      },
      {
        id: '2',
        name: 'cauliflower florets',
        imageName: 'cauliflower.jpg',
        amount: 2,
        unit: 'cups'
      },
    ]
  },
};