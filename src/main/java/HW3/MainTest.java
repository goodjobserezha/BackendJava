package HW3;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MainTest {

    private final String apiKey = "5a5d73e5d8fe4f60888a3ebd64a6d1db";

    @Test
    void addAndDeleteFromMealPlan() {
        String id = given()
                .queryParam("hash", "84514555d2ad3f1c851cf865e8835ba2ec7310a7")
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"date\": 1234567890,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name33/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        given()
                .queryParam("hash", "84514555d2ad3f1c851cf865e8835ba2ec7310a7")
                .queryParam("apiKey", apiKey)
                .delete("https://api.spoonacular.com/mealplanner/your-users-name33/items/" + id)
                .then()
                .statusCode(200);
    }
}