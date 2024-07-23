/**
 * Interface representing a nutrient in a recipe.
 * 
 * @interface
 * @property {string} name - The name of the nutrient.
 * @property {string} unit - The unit of measurement for the nutrient amount.
 * @property {number} amount - The amount of the nutrient.
 */
interface Nutrient {
    name: string;
    unit: string;
    amount: number;
}

/**
 * Interface representing an ingredient in a recipe.
 * 
 * @interface
 * @property {string} id - The unique identifier for the ingredient.
 * @property {string} name - The name of the ingredient.
 * @property {string} image - The URL of the image representing the ingredient.
 * @property {string} unit - The unit of measurement for the ingredient amount.
 * @property {string} original - The original description of the ingredient.
 * @property {number} amount - The amount of the ingredient.
 */
interface Ingredient {
    id: string;
    name: string;
    image: string;
    unit: string;
    original: string;
	amount: number;
}

/**
 * Interface representing a recipe record.
 * 
 * @interface
 * @property {string} id - The recipe's ID.
 * @property {string} title - The recipe's title.
 * @property {string} image - The recipe's image full URL.
 */
interface Recipe {
    id: string;
    title: string;
    image: string;
}