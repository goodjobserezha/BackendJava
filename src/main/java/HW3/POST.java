package HW3;

import io.restassured.path.json.JsonPath;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class POST {

    private final String apiKey = "5a5d73e5d8fe4f60888a3ebd64a6d1db";

    @Test
    void postTest1() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void postTest2() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "sushi")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), CoreMatchers.<Object>equalTo("Japanese"));
    }

    @Test
    void postTest3() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "burger")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), CoreMatchers.<Object>equalTo("American"));
        assertThat(response.get("confidence"), CoreMatchers.<Object>equalTo(0.85F));
    }

    @Test
    void postTest4() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "French fries")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), CoreMatchers.<Object>equalTo("American"));
        assertThat(response.get("confidence"), CoreMatchers.<Object>equalTo(0.85F));
    }

    @Test
    void postTest5() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "Spaghetti Carbonara")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), CoreMatchers.<Object>equalTo("Mediterranean"));
        assertThat(response.get("confidence"), CoreMatchers.<Object>equalTo(0.95F));
    }
}