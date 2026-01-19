package restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidateJSONAttribute {

    @Test
    public void testPutAndGetApi() {
        // Base URI for the API
        RestAssured.baseURI = "https://api.example.com";

        // JSON body for PUT request
        String requestBody = """
        {
            "id": 1,
            "author": "author",
            "title": "title"
        }
        """;

        // Step 1: Send a PUT request with the JSON body
        Response putResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/endpoint")
                .then()
                .statusCode(200) // Verify status code
                .extract()
                .response();

        // Step 2: Assert that the PUT response body is not null
        assertNotNull(putResponse.getBody(), "PUT response body should not be null");

        // Step 3: Send a GET request to retrieve the updated resource
        Response getResponse = given()
                .when()
                .get("/endpoint/1") // Adjust endpoint as needed
                .then()
                .statusCode(200) // Verify status code
                .extract()
                .response();

        // Step 4: Assert that the GET response body is not null
        assertNotNull(getResponse.getBody(), "GET response body should not be null");

        // Step 5: Verify that 'author' and 'title' are not null in the GET response
        String author = getResponse.jsonPath().getString("author");
        String title = getResponse.jsonPath().getString("title");

        // Assertions to ensure 'author' and 'title' are not null
        assertNotNull(author, "Author should not be null");
        assertNotNull(title, "Title should not be null");

        // Optionally print the values for debugging
        System.out.println("Author: " + author);
        System.out.println("Title: " + title);
    }
}

