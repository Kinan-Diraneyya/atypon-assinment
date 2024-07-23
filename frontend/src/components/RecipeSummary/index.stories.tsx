import React from 'react';
import FeaturedRecipe from './index';
import { Meta, StoryObj } from '@storybook/react';

const meta: Meta<typeof FeaturedRecipe> = {
    component: FeaturedRecipe,
  };
   
  export default meta;
  type Story = StoryObj<typeof FeaturedRecipe>;
   
  export const Main: Story = {
    args: {
      title: 'Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs',
      image: 'https://img.spoonacular.com/recipes/716429-556x370.jpg',
      readyInMinutes: 45,
      servings: 2,
      summary: 'You can never have too many main course recipes, so give Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs a try. One serving contains 543 calories, 17g of protein, and 16g of fat. For $1.57 per serving, this recipe covers 22% of your daily requirements of vitamins and minerals. This recipe serves 2. A mixture of butter, white wine, pasta, and a handful of other ingredients are all it takes to make this recipe so yummy. 209 people have tried and liked this recipe. It is brought to you by fullbellysisters.blogspot.com. From preparation to the plate, this recipe takes approximately 45 minutes. Taking all factors into account, this recipe earns a spoonacular score of 83%, which is tremendous. If you like this recipe, take a look at these similar recipes: Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs, Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs, and Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs.',
      dishTypes: [
        'side dish',
        'lunch',
        'main course',
        'main dish',
        'dinner'
      ],
    }
  };