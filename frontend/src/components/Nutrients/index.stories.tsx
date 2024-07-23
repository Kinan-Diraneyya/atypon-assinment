import React from 'react';
import Nutrients from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Nutrients> = {
    component: Nutrients,
  };
   
  export default meta;
  type Story = StoryObj<typeof Nutrients>;
   
  export const Empty: Story = {
    args: {
      nutrients: []
    }
  };

  export const Full: Story = {
    args: {
      nutrients: [
        {
          name: 'Calories',
          amount: 543.36,
          unit: "kcal"
        },
        {
          name: 'Fat',
          amount: 16.2,
          unit: "g"
        }
      ]
    }
  };