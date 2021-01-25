package com.recipes.request;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipes.entity.Ingredient;


/**
 * This is the RecipeRequest class to manage the request params
 * 
 * @author ssataraj
 */
public class RecipeRequest {
	private int id;

	@NotBlank(message = "Name of recipe may not be empty")
	private String name;

	private boolean isVegetarian;

	@NotNull
	@Min(1)
	private int noOfServings;

	@NotBlank(message = "Cooking instructions may not be empty")
	private String cookingInstructions;

	@NotNull
	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date dateOfCreation;

	@NotNull
	private Set<Ingredient> ingredients = new HashSet<>();

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

	public void setVegetarian(boolean isVegetarian) {
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

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeRequest [id=" + id + ", name=" + name + ", isVegetarian=" + isVegetarian + ", noOfServings="
				+ noOfServings + ", cookingInstructions=" + cookingInstructions + ", dateOfCreation=" + dateOfCreation
				+ "]";
	}

	

}
