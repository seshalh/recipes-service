package com.recipes.recipesbe.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipes.convertor.ConvertorUtils;
import com.recipes.entity.Ingredient;
import com.recipes.entity.Recipe;
import com.recipes.repository.RecipesRepo;
import com.recipes.service.RecipeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RecipeServiceTest {

	@MockBean
	RecipesRepo mockRecipeRepository;

	@InjectMocks
	RecipeService recipeService;

	@Test
	void GivenValidIdWhenFoudnThenReturnDetails() throws Exception {
		
		Optional<Recipe> recipe = Optional.of(ConvertorUtils.createNewRecipe());
		
		Mockito.when(mockRecipeRepository.findById(Mockito.anyInt())).thenReturn(recipe);

		
		Recipe result = recipeService.getRecipeById(101);

		
		assertEquals("Test Recipe", result.getName());
		verify(mockRecipeRepository, times(1)).findById(Mockito.anyInt());
		verify(mockRecipeRepository, never()).findAll();

	}

	@Test
	void whenFoundThenReturnAllRecipeDetails() throws Exception {
		
		Recipe recipe = ConvertorUtils.createNewRecipe();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(recipe);

		
		Mockito.when(mockRecipeRepository.findAll()).thenReturn(recipes);

		
		List<Recipe> result = recipeService.getAllRecipes();

		
		assertEquals(1,result.size());
		verify(mockRecipeRepository, times(1)).findAll();
		verify(mockRecipeRepository, never()).findById(Mockito.anyInt());

	}

	@Test
	void givenValidInputThenAddRecipe() {
		
		Recipe recipe = ConvertorUtils.createNewRecipe();
		
		Mockito.when(mockRecipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe);

		
		Recipe actual = recipeService.createRecipe(recipe);

		assertEquals(recipe, actual);
		verify(mockRecipeRepository, times(1)).save(Mockito.any());

	}

	@Test
	void givenValidInputWhenFoundThenUpdateTheDetails() {
		
		Recipe recipe = ConvertorUtils.createNewRecipe();

		Mockito.when(mockRecipeRepository.findById((Mockito.anyInt()))).thenReturn(Optional.of(recipe));

		Mockito.when(mockRecipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe);

		
		Recipe orecipe = recipeService.updateRecipe(recipe);

		assertEquals(orecipe, recipe);
		verify(mockRecipeRepository, times(1)).save(Mockito.any());

	}

	@Test
	void givenIdWhenFoundThenDelete() {
		
		
		int id = 4;
		Optional<Recipe> recipe = Optional.of(ConvertorUtils.createNewRecipe());
	
		
		Mockito.when(mockRecipeRepository.findById(Mockito.anyInt())).thenReturn(recipe);

		
		boolean status = recipeService.deleteRecipeById(4);

		
		assertEquals(true, status);
		verify(mockRecipeRepository, times(1)).deleteById(id);
	}
	
	
}
