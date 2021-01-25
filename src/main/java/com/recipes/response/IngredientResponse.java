package com.recipes.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipes.entity.Recipe;

/**
 * This is the IngredientResponse POGO class to manage the response params from the database
 * 
 * @author ssataraj
 */

public class IngredientResponse {
	@JsonIgnore
	private Recipe recipe;
	private int id;
	private String quantityDetails;
	private String nameOfIngredient;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuantityDetails() {
		return quantityDetails;
	}

	public void setQuantityDetails(String quantityDetails) {
		this.quantityDetails = quantityDetails;
	}

	public String getNameOfIngredient() {
		return nameOfIngredient;
	}

	public void setNameOfIngredient(String nameOfIngredient) {
		this.nameOfIngredient = nameOfIngredient;
	}

	


}
