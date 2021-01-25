package com.recipes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  This is the Ingredient entity class to hold data from database
 * @author ssataraj
 *
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

	@ManyToOne
	private Recipe recipe;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	
	@Column(name = "NAME_OF_INGREDIENT")
	private String nameOfIngredient;
		
	@Column(name = "QUANTITY_DETAILS")
	private String quantityDetails;
	
	public Ingredient() {
		super();
	}
	public Ingredient(String nameOfIngredient, String quantityDetails) {
		super();
		this.nameOfIngredient = nameOfIngredient;
		this.quantityDetails = quantityDetails;
	}

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameOfIngredient() {
		return nameOfIngredient;
	}
	public void setNameOfIngredient(String nameOfIngredient) {
		this.nameOfIngredient = nameOfIngredient;
	}
	public String getQuantityDetails() {
		return quantityDetails;
	}
	public void setQuantityDetails(String quantityDetails) {
		this.quantityDetails = quantityDetails;
	}
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
}
