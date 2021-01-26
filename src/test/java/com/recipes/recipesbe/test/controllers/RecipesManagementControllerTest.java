package com.recipes.recipesbe.test.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.recipes.controller.RecipesManagementController;
import com.recipes.convertor.ConvertorUtils;
import com.recipes.entity.Ingredient;
import com.recipes.entity.Recipe;
import com.recipes.exception.RecipeNotFoundException;
import com.recipes.service.RecipeService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RecipesManagementController.class)
@WithMockUser(username = "admin", password = "password", roles = "USER")
class RecipesManagementControllerTest {

	private String api_url = "/api/v1/recipes";

	@MockBean
	RecipeService mockRecipeService;
	
	@InjectMocks 
	RecipesManagementController recipeController;

	@Autowired
	MockMvc mvc;
	

	

	@Test
	void canRetrieveRecipeByIdWhenExists() throws Exception {

		Recipe recipe = ConvertorUtils.createNewRecipe();
		// Given
		given(mockRecipeService.getRecipeById(recipe.getId())).willReturn(recipe);
		
	    //When
		when(mockRecipeService.getRecipeById(recipe.getId())).thenReturn(recipe);
		
		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders
				.get(api_url + "/" + recipe.getId())
				.accept(MediaType.APPLICATION_JSON);
		
	   	
		// verify
		mvc.perform(getRequestBuilder).andExpect(status().isOk());
	}
	
	

	@Test
	void givenRecipeWhenNotFoundThenReturnException() throws Exception {

		// Given
		int id = 507;   //Didnt exisits
		
		// When
		Mockito.when(mockRecipeService.getRecipeById(Mockito.anyInt())).thenThrow(RecipeNotFoundException.class);

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders
				.get(api_url + "/" + id)
				.accept(MediaType.APPLICATION_JSON);

		// verify
		mvc.perform(getRequestBuilder).andExpect(status().isNotFound());
	}

	@Test
	void givenIdWhenFoundRecordThenDelete() throws Exception {

		// Given
		int id = 505;   // Recipe Exisits

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.delete(api_url + "/" + id);

		// verify
		mvc.perform(getRequestBuilder).andExpect(status().isOk());

		verify(mockRecipeService, times(1)).deleteRecipeById(Mockito.anyInt());

	}

	@Test
	void givenInputWhenAddedThenReturnOK() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 2);
		input.put("name", "Mutton Beriyani");
		input.put("isVegetarian", false);
		input.put("noOfServings", 1);
		input.put("cookingInstructions", "spicy");
		input.put("dateOfCreation", "14‐12‐2020 18:30");
		JSONArray array = new JSONArray();
		JSONObject ingredient = new JSONObject();
		ingredient.put("id", 4);
		ingredient.put("nameOfIngredient", "Onions");
		ingredient.put("quantityDetails", "7");
		array.put(ingredient);
		input.put("ingredients", array);
				
		// Given
		String contentToBeCreated = input.toString();

		//When
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		//Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(api_url+"/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);
		
		// verify
		mvc.perform(postRequestBuilder).andExpect(status().isCreated());

	}

	
	@Test
	void GivenInvalidRequesWhenTriedToAddThenReturnBadRequest() throws Exception {

		// Given
		// Did not send content

		// When
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		// Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		// verify
		mvc.perform(postRequestBuilder).andExpect(status().isBadRequest());

	}
	@Test
	void givenInvalidRequestThenTriedToUpdateThenReturnBadRequest() throws Exception {

		// Given
		// Did not send content

		// When
		Mockito.when(mockRecipeService.updateRecipe(Mockito.any())).thenReturn(null);

		// Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.put(api_url + "/")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		// verify
		mvc.perform(postRequestBuilder).andExpect(status().isBadRequest());

	}
	
	@Test
	void givenInvalidIdWhenTriedToDeleteThenReturnFalsewithOK() throws Exception {

		
		int id = 101;
		boolean status = false;

		
		Mockito.when(mockRecipeService.deleteRecipeById(id)).thenReturn(status);

		
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.delete(api_url + "/" + id);

		
		
		mvc.perform(getRequestBuilder).andExpect(status().isOk());

		verify(mockRecipeService, times(1)).deleteRecipeById(Mockito.anyInt());

	}
	
	
	
}
