package com.recipes.response;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the RecipeRequest POGO class to manage the response params from the database
 * 
 * @author ssataraj
 */

import com.fasterxml.jackson.annotation.JsonFormat;
public class RecipeResponse {
	
	
	private int id;

	private String name;

	private boolean isVegetarian;

	private int noOfServings;

	private String cookingInstructions;

	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date dateOfCreation;

	private Set<IngredientResponse> ingredients = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public int getNoOfServings() {
		return noOfServings;
	}

	public void setNoOfServings(int noOfServings) {
		this.noOfServings = noOfServings;
	}

	public String getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Set<IngredientResponse> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientResponse> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeResponse [id=" + id + ", name=" + name + ", isVegetarian=" + isVegetarian + ", noOfServings="
				+ noOfServings + ", cookingInstructions=" + cookingInstructions + ", dateOfCreation=" + dateOfCreation
				+ "]";
	}

	

}
