import React from 'react';
import Spinner from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Spinner> = {
    component: Spinner,
  };
   
  export default meta;
  type Story = StoryObj<typeof Spinner>;
   
  export const Main: Story = {
    args: {}
  };