# Recipes web service


### Project Description
```
This is a rest api backend service which provides method getAllRecipes, saveRecipe, updateRecipe , deleteRecipe.
```
### Installation

* Ensure that Java 11 and Maven 4.0 are installed.
* Repository can be cloned using the command :
    `git clone https://github.com/sesha/recipes_Repo.git`

### Technologies

* Technologies that are used are :
  `Java 11, SpringBoot, Swaggar, Mockito, Git, Embedded H2 database`


## Usage
### Running RecipesService
* Navigate to the directory into which the repository has been cloned and run this command  
`mvn clean install`
* Navigate to the target folder and run this command  
`java -jar recipesservice-0.0.1-SNAPSHOT.jar`
* Once the service is up and running. RecipesService API can be accessible on port 8080,  e.g.`http://localhost:8080/api/v1/recipe/`. Credentials are required to access RecipesService API and same will be provided over email. 
* API Documentation can be accessible on same port 8080 using this URL.   `http://localhost:8080/swagger-ui.html`
* H2 database admin console url is `http://localhost:8080/h2-console/`. Credentials can be modified in `src/main/resources/application.properties` file.

### Running Postman Requests
* Install the postman
* Navigate to postman_scripts folder
* Import the `Recipes.postman_collection` into postman. You will be able to see all requests including UnAuthorized scenarios. 
* Run the apis to test respective operation.

### References

* RecipesWebService API URLs -  
	 *  GET : `http://localhost:8080/api/v1/recipes`  
	 * 	GET : `http://localhost:8080/api/v1/recipes/{id}`  
	 * 	POST : `http://localhost:8080/api/v1/recipes`  
	 * 	PUT : `http://localhost:8080/api/v1/recipes`  
	 * 	DELETE : `http://localhost:8080/api/v1/recipes/{id}`  
	 	   
	   
	 
* API Documentation :` http://localhost:8080/swagger-ui.html`



* RecipesWebService Heroku API URLs -  
	 *  GET : `https://heroku-recipes-service.herokuapp.com/api/v1/recipes`  
	 * 	GET : `https://heroku-recipes-service.herokuapp.com/api/v1/recipes/{id}`  
	 * 	POST : `https://heroku-recipes-service.herokuapp.com/api/v1/recipes`  
	 * 	PUT : `https://heroku-recipes-service.herokuapp.com/api/v1/recipes`  
	 * 	DELETE :`https://heroku-recipes-service.herokuapp.com/api/v1/recipes/{id}`  

