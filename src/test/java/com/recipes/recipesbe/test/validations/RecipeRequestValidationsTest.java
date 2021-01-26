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

		String contentToBeCreated = getNewRecipeInJson(1);
		
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

		
		
		String contentToBeCreated = getNewRecipeInJson(2);
		
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
		String contentToBeCreated = getNewRecipeInJson(3);
		
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

		
		String contentToBeCreated = getNewRecipeInJson(4);
		
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

				
		String contentToBeCreated = getNewRecipeInJson(5);
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders
				.post(api_url + "/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		
		mvc.perform(postRequestBuilder)
		   .andExpect(status().isBadRequest());

	}
	
	
	public String getNewRecipeInJson(int type) throws Exception
	{
		JSONObject input = new JSONObject();
		
		input.put("id", 2);
		
		if(type != 1)   // Bad request
			input.put("name", "Fish curry");
		
		input.put("isVegetarian", false);
		
		if(type == 2)   // no of servings 0
			input.put("noOfServings", 0);
		else
		 input.put("noOfServings", 6);
		
		if(type == 4)  // invalid cooking instructions
			input.put("cookingInstructions", "");
		else
			input.put("cookingInstructions", "spicy");
		
		if (type != 5)  // missing date of creation
		input.put("dateOfCreation", "24‐01‐2021 18:30");
		
		if(type == 3)   // invalid ingredients
		{
			input.put("ingredients", "[]");
		}
		else
		{
			JSONArray array = new JSONArray();
			array.put(new JSONObject());
			input.put("ingredients", array);
		}
		
		return input.toString();
		
	}
}
