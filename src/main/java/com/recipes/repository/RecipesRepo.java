package com.recipes.repository;


import org.springframework.data.repository.CrudRepository;

import com.recipes.entity.Recipe;

public interface RecipesRepo extends CrudRepository<Recipe, Integer>{
	
}