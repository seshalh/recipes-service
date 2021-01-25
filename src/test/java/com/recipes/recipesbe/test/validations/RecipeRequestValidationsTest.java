package com.recipes.recipesbe.test.validations;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import com.recipes.controller.RecipesManagementController;
import com.recipes.entity.Recipe;
import com.recipes.service.RecipeService;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RecipesManagementController.class)
@WithMockUser(username = "admin", password = "password", roles = "USER")
class RecipeRequestValidationsTest {

	private String api_url = "/api/v1/recipes";

	@MockBean
	RecipeService mockRecipeService;
		
	RecipesManagementController recipeController;

	@Autowired
	MockMvc mvc;

	@Test
	void givenInvalidRequestWhenFoundThenReturnBadRequest() throws Exception {

		String contentToBeCreated = getNewRecipeInJson();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any()))
			   .thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}

	@Test
	void givenInvalidNoOfPPLValueWhenInvokedThenReturnBadRequest() throws Exception {

		
		
		String contentToBeCreated = getNewRecipeInJson();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any()))
		       .thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}
	@Test
	void givenInvalidIngedientsValueWhenFoundThenReturnBadRequest() throws Exception {
		String contentToBeCreated = getNewRecipeInJson();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any()))
		       .thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated)
				.contentType(MediaType.APPLICATION_JSON);

		
		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}
	
	@Test
	void givenInvalidCookingInstructionsThenReturnBadRequest() throws Exception {

		
		String contentToBeCreated = getNewRecipeInJson();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any()))
		       .thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}
	@Test
	void givenInvalidCreationDateThenReturnBadRequest() throws Exception {

				
		String contentToBeCreated = getNewRecipeInJson();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}
	
	
	public String getNewRecipeInJson() throws Exception
	{
		JSONObject input = new JSONObject();
		input.put("id", 2);
		input.put("isVegetarian", false);
		input.put("noOfServings", 1);
		input.put("ingredients", "chicken,rice");
		input.put("cookingInstructions", "spicy");
		input.put("dateOfCreation", "24‐01‐2021 18:30");
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		input.put("ingredients", array);
		
		return input.toString();
		
	}
}
