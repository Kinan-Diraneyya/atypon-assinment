import React from 'react';
import Ad from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Ad> = {
    component: Ad,
  };
   
  export default meta;
  type Story = StoryObj<typeof Ad>;
   
  export const Main: Story = {
    args: {}
  };