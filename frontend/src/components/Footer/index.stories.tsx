import React from 'react';
import Footer from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Footer> = {
    component: Footer,
  };
   
  export default meta;
  type Story = StoryObj<typeof Footer>;
   
  export const Main: Story = {
    args: {}
  };