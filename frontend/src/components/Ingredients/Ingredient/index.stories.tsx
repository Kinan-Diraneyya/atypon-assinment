import React from 'react';
import Ingredient from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Ingredient> = {
    component: Ingredient,
  };
   
  export default meta;
  type Story = StoryObj<typeof Ingredient>;
   
  export const Primary: Story = {
    args: {
      id: '1',
      name: 'butter',
      imageName: 'butter-sliced.jpg',
      amount: 1,
      unit: 'tbsp'
    },
  };