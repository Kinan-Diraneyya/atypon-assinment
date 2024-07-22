import React from 'react';
import Logo from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Logo> = {
    component: Logo,
  };
   
  export default meta;
  type Story = StoryObj<typeof Logo>;
   
  export const Main: Story = {
    args: {}
  };