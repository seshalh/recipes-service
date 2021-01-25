package com.recipes.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import com.recipes.entity.Recipe;
import com.recipes.convertor.ConvertorUtils;
import com.recipes.request.RecipeRequest;
import com.recipes.response.RecipeResponse;
import com.recipes.service.RecipeService;




/**
 *  This is the controller class for the RecipeService
 * @author ssataraj
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
@Api(value ="Recipe Management Controller", tags = {"recipes"})
@Validated // class level
public class RecipesManagementController {

	

	@Autowired
	private RecipeService service;

	/**
	 * This method returns the list of recipes.
	 * 
	 * @return List<Recipe> list of recipes.
	 */
	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
		
		ArrayList<RecipeResponse> lstResponseRecipes = new ArrayList<>();
		List<Recipe> lstRecipes = service.getAllRecipes();
		Iterator<Recipe> iter = lstRecipes.iterator(); 
		
		 while (iter.hasNext()) { 
			Recipe recipe = iter.next();
			
			RecipeResponse recipeResponse = ConvertorUtils.convertRecipeEntityToRecipeResponse(recipe);
			lstResponseRecipes.add(recipeResponse);
			
		
			
		}

		return new ResponseEntity<>(lstResponseRecipes, HttpStatus.OK);
	}

	/**
	 * This method returns the recipe details of particular id.
	 * 
	 * @param id the recipe id.
	 * @return Recipe of the requested id.
	 */
	
	@GetMapping("/recipes/{id}")
	public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable("id") int id) {
		
		Recipe recipe = service.getRecipeById(id);
		RecipeResponse response = ConvertorUtils.convertRecipeEntityToRecipeResponse(recipe);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method deletes the recipe entity based on given id.
	 * 
	 * @param id recipe id.
	 * @return Boolean.
	 */
		
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<Boolean> deleteRecipe(@PathVariable("id") int id) {
		
		boolean status =  service.deleteRecipeById(id);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
		
	
	}
	
	/**
	 * This method adds new recipe and returns the same object when it is created
	 * successfully.
	 * 
	 * @param RecipeRequest recipe request.
	 * @return Recipe recipe details.
	 */
	@PostMapping("/recipes")
	public ResponseEntity<RecipeResponse> saveRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {

		Recipe recipe = service.createRecipe(ConvertorUtils.convertRecipeRequestToRecipeEntity(recipeRequest));
		RecipeResponse response = ConvertorUtils.convertRecipeEntityToRecipeResponse(recipe);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/**
	 * This method updates recipe details and returns the same object when it is
	 * updated successfully.
	 * 
	 * @param RecipeRequest recipe request.
	 * @return Recipe whcih added to DB.
	 */
	
	@PutMapping("/recipes")
	public ResponseEntity<RecipeResponse> updateRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {

		Recipe recipe = service.updateRecipe(ConvertorUtils.convertRecipeRequestToRecipeEntity(recipeRequest));
		RecipeResponse response = ConvertorUtils.convertRecipeEntityToRecipeResponse(recipe);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
