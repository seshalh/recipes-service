package com.recipes.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.recipes.convertor.ConvertorUtils;
import com.recipes.entity.Recipe;
import com.recipes.exception.RecipeNotFoundException;
import com.recipes.repository.RecipesRepo;


/*
 *  Its a RecipeService class which works with database
 *  @author ssataraj
 */

@Service
public class RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	private RecipesRepo recipeRepo;

	/**
	 * This method returns all existing recipes.
	 * 
	 * @return List<Recipe> returns list of recipes.
	 */
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipesList = new ArrayList<>();
		recipeRepo.findAll().forEach(recipe -> recipesList.add(recipe));
		return recipesList;
	}

	/**
	 * This method returns recipe details for a particular id.
	 * 
	 * @param id
	 * @return Recipe returns recipe entity.
	 */
	public Recipe getRecipeById(int id) {
		Recipe recipeDetails = null;
		Optional<Recipe> recipe = recipeRepo.findById(id);
		if (recipe.isPresent()) {
			recipeDetails = recipe.get();
		} else {
			throw new RecipeNotFoundException("Didn't find Recipe  :" + id);
		}
		return recipeDetails;
	}

	/**
	 * This method is used to create a recipe.
	 * 
	 * @param Recipe
	 * @return Recipe returns recipe entity.
	 */
	public Recipe createRecipe(Recipe recipe) {
		Recipe recipeResponse = null;
		recipeResponse = recipeRepo.save(recipe);
		return recipeResponse;
	}

	/**
	 * This method is used to update recipe details.
	 * 
	 * @param Recipe
	 * @return Recipe returns recipe.
	 */
	public Recipe updateRecipe(Recipe recipe) {
		
		Recipe updatedRecipe = null;
		Optional<Recipe> result = recipeRepo.findById(recipe.getId());
		if (result.isPresent()) {
			 
			Recipe recipeToUpdate = result.get();
			ConvertorUtils.copyRecipeValuesToUpdate(recipeToUpdate, recipe);
			updatedRecipe = recipeRepo.save(recipeToUpdate);
			
		} else {
			throw new RecipeNotFoundException("No Recipe Found to update :" + recipe.getId());
		}
		return updatedRecipe;
	}

	/**
	 * This method is used to delete recipe details for a particular id.
	 * 
	 * @param id
	 * @return boolean returns true/false.
	 * 
	 */
	public boolean deleteRecipeById(int id) {
		boolean status = false;

		try {
			recipeRepo.deleteById(id);
			status = true;
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex.getMessage());
			throw new RecipeNotFoundException("No Recipe Found to delete :" + id);
		}

		return status;
	}

}
