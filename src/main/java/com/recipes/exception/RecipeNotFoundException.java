package com.recipes.exception;
public class RecipeNotFoundException extends RuntimeException {


/**
 *  This is the Recipe Not Found exception class to handle not found case of Recipe
 * @author ssataraj
 *
 */

	private static final long serialVersionUID = 1L;

	public RecipeNotFoundException(String message) {
		super(message);
	}

	public RecipeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecipeNotFoundException(Throwable cause) {
		super(cause);
	}
}
