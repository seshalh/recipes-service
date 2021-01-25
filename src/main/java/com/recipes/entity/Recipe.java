package com.recipes.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *  This is the Recipe entity class to hold data from database
 * @author ssataraj
 *
 */

@Entity
@Table(name = "recipe")
public class Recipe {
	
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	
	@Column(name = "NAME")
	private String name;
	
	
	@Column(name = "DATE")
	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date dateOfCreation;

	@Column(name = "VEGETARIAN")
	private boolean isVegetarian;

	@Column(name = "NO_OF_SERVINGS")
	private int noOfServings;

	@Column(name = "INSTRUCTIONS")
	private String cookingInstructions;
	
		
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

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
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

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

		public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}
	
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void clearIngredients()
	{
		
		ingredients.clear();
	}

	@Override
	public String toString() {
		return "Recipe [ingredients=" + ingredients + ", id=" + id + ", name=" + name + ", dateOfCreation="
				+ dateOfCreation + ", isVegetarian=" + isVegetarian + ", noOfServings=" + noOfServings
				+ ", cookingInstructions=" + cookingInstructions + "]";
	}
	
	

	
	

	
}
