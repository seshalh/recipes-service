package com.recipes.convertor;

import com.recipes.entity.Ingredient;
import com.recipes.entity.Recipe;
import com.recipes.request.RecipeRequest;
import com.recipes.response.IngredientResponse;
import com.recipes.response.RecipeResponse;



public class ConvertorUtils {
	
	
	private ConvertorUtils()
	{
		
	}
	
	/**
	 * This method is used to convert to Recipe a RecipeRequest.
	 * 
	 * @param Recipe
	 * @return RecipeResponse returns RecipeRequest.
	 */
	public static RecipeResponse convertRecipeEntityToRecipeResponse(Recipe recipe) {
		
		if (recipe == null) {
			return null;
		}
		RecipeResponse recipeResponse = new RecipeResponse();
		recipeResponse.setId(recipe.getId());
		recipeResponse.setIsVegetarian(recipe.getIsVegetarian());
		recipeResponse.setNoOfServings(recipe.getNoOfServings());
		recipeResponse.setName(recipe.getName());
		recipeResponse.setCookingInstructions(recipe.getCookingInstructions());
		recipeResponse.setDateOfCreation(recipe.getDateOfCreation());

		if (recipe.getIngredients() != null && !(recipe.getIngredients().isEmpty())) {
			
			recipe.getIngredients().stream().forEach((ingredient) -> 
						recipeResponse.getIngredients().add(convertIngredientIntoIngredientResponse(ingredient)));
		}
		return recipeResponse;
		
	}
	
	/**
	 * This method is used to convert to RecipeRequest to Recipe.
	 * 
	 * @param RecipeRequest
	 * @return Recipe  It returns recipe entity.
	 */
	
	public static Recipe convertRecipeRequestToRecipeEntity(RecipeRequest recipeRequest) {
		
		if (recipeRequest == null) {
			return null;
		}
		Recipe recipe = new Recipe();
		recipe.setId(recipeRequest.getId());
		recipe.setIsVegetarian(recipeRequest.getIsVegetarian());
		recipe.setNoOfServings(recipeRequest.getNoOfServings());
		recipe.setName(recipeRequest.getName());
		recipe.setCookingInstructions(recipeRequest.getCookingInstructions());
		recipe.setDateOfCreation(recipeRequest.getDateOfCreation());

		if (recipeRequest.getIngredients() != null && !recipeRequest.getIngredients().isEmpty()) {
			
			recipeRequest.getIngredients().stream().forEach((ingredient) -> 
				recipe.addIngredient(convertReqIngredientIntoRecipeIngredient(ingredient)));
		}

		return recipe;
	}
	
	/**
	 * This method is used to convert Ingredient into IngredientResponse.
	 * 
	 * @param Ingredient
	 * @return IngredientResponse  It returns IngredientResponse object.
	 */
	
	public static IngredientResponse convertIngredientIntoIngredientResponse(Ingredient ingredient) {
		
		IngredientResponse ingResponse = new IngredientResponse();
		ingResponse.setId(ingredient.getId());
		ingResponse.setQuantityDetails(ingredient.getQuantityDetails());
		ingResponse.setNameOfIngredient(ingredient.getNameOfIngredient());
		return ingResponse;
		
	}
	
	/**
	 * This method is used to convert ReqIngredient Into RecipeIngredient
	 * 
	 * @param Ingredient
	 * @return Ingredient  It returns recipe Ingredient.
	 */
	
	public static Ingredient convertReqIngredientIntoRecipeIngredient(Ingredient reqIngredient) {
		
		Ingredient recipeIng = new Ingredient();
		recipeIng.setId(reqIngredient.getId());
		recipeIng.setQuantityDetails(reqIngredient.getQuantityDetails());
		recipeIng.setNameOfIngredient(reqIngredient.getNameOfIngredient());
		
		return recipeIng;
	}
	/**
	 * This is a utility method to create a new recipe
	 * 
	 * @return It returns newly created Recipe entity.
	 */
	
	public static Recipe createNewRecipe()
	{
		
		Recipe recipe = new Recipe();
		recipe.setId(71);
		recipe.setName("Test Recipe");
		recipe.setNoOfServings(4);
		recipe.setCookingInstructions("medium spicy");
		Ingredient ingredient = new Ingredient("onion", "2 peices");
		recipe.addIngredient(ingredient);
		recipe.setIsVegetarian(true);
		
		return recipe;
	}
	
	/**
	 * This is a utility method to copy values to update Recipe
	 * @param Recipe  Recipe with new values
	 * @param Recipe  updated Recipe
	 * @return It returns newly created Recipe entity.
	 */
	
	public static void copyRecipeValuesToUpdate(Recipe recipeToUpdate, Recipe recipeDetails)
	{
		recipeToUpdate.setIsVegetarian(recipeDetails.getIsVegetarian());
		recipeToUpdate.setNoOfServings(recipeDetails.getNoOfServings());
		recipeToUpdate.setName(recipeDetails.getName());
		recipeToUpdate.setCookingInstructions(recipeDetails.getCookingInstructions());
		recipeToUpdate.setDateOfCreation(recipeDetails.getDateOfCreation());
		recipeToUpdate.clearIngredients();// First clear all existing ingredients
		if (recipeDetails.getIngredients() != null && !recipeDetails.getIngredients().isEmpty()) {
			
			recipeDetails.getIngredients().stream().forEach((ingredient) -> 
			recipeToUpdate.addIngredient(convertReqIngredientIntoRecipeIngredient(ingredient)));
		}

		
	
		
		
	}
		
}
