import React from 'react';
import Separator from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof Separator> = {
    component: Separator,
  };
   
  export default meta;
  type Story = StoryObj<typeof Separator>;
   
  export const Main: Story = {
    args: {}
  };