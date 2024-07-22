import React from 'react';
import Header from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Header> = {
    component: Header,
  };
   
  export default meta;
  type Story = StoryObj<typeof Header>;
   
  export const Main: Story = {
    args: {}
  };