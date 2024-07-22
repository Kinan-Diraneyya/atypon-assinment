import React from 'react';
import Search from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Search> = {
    component: Search,
  };
   
  export default meta;
  type Story = StoryObj<typeof Search>;
   
  export const Main: Story = {
    args: {}
  };