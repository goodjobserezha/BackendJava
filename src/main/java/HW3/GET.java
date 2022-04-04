package HW3;


import io.restassured.path.json.JsonPath;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GET {

    private final String apiKey = "5a5d73e5d8fe4f60888a3ebd64a6d1db";

    @Test
    void getTest1(){
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getTest2(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "burger")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), CoreMatchers.<Object>equalTo(0));
        assertThat(response.get("number"), CoreMatchers.<Object>equalTo(10));
    }

    @Test
    void getTest3(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine","italian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), CoreMatchers.<Object>equalTo(0));
        assertThat(response.get("number"), CoreMatchers.<Object>equalTo(10));
        assertThat(response.get("totalResults"), CoreMatchers.<Object>equalTo(263));
    }

    @Test
    void getTest4(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("excludeCuisine","greek")
                .queryParam("query","burger")
                .queryParam("maxCalories","800")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), CoreMatchers.<Object>equalTo(0));
        assertThat(response.get("number"), CoreMatchers.<Object>equalTo(10));
        assertThat(response.get("totalResults"), CoreMatchers.<Object>equalTo(8));
    }

    @Test
    void getTest5(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("diet","vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), CoreMatchers.<Object>equalTo(0));
        assertThat(response.get("number"), CoreMatchers.<Object>equalTo(10));
        assertThat(response.get("totalResults"), CoreMatchers.<Object>equalTo(2219));
    }
}

