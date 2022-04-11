package HW4;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Gettest {
    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "5a5d73e5d8fe4f60888a3ebd64a6d1db";

    @BeforeEach
    void beforeTest(){
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(10000L))
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void getTest1(){
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTest2(){
        given().spec(requestSpecification)
                .when()
                .queryParam("query", "burger")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTest3(){
        given().spec(requestSpecification)
                .when()
                .queryParam("cuisine","italian")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTest4(){
        given().spec(requestSpecification)
                .when()
                .queryParam("excludeCuisine","greek")
                .queryParam("query","burger")
                .queryParam("maxCalories","800")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTest5(){
        given().spec(requestSpecification)
                .when()
                .queryParam("diet","vegetarian")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTest6(){
        given().spec(requestSpecification)
                .when()
                .queryParam("includeIngredients","tomato,cheese")
                .queryParam("maxCalories", "800")
                .queryParam("minCalories","300")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
}
