package restAssured;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiChainingWithAssertionsTest {

    @Test
    public void testApiChaining() {
        // Step 1: Send a GET request to the first API and capture the response
        Response firstApiResponse = RestAssured
                .given()
                .baseUri("https://api.example.com")
                .when()
                .get("/firstApiEndpoint")
                .then()
                .statusCode(200) // Validate status code using RestAssured
                .extract()
                .response();
        // Assert that the response body is not null
        assertNotNull(firstApiResponse.getBody(), "First API response body should not be null");

        // Step 2: Extract a value from the response (e.g., an ID or token)
        JsonPath jsonPathEvaluator = firstApiResponse.jsonPath();
        String extractedValue = jsonPathEvaluator.getString("data.id"); // Adjust path as needed

        // Assert that the extracted value is not null or empty
        assertNotNull(extractedValue, "Extracted value should not be null");

        // Print the extracted value for debugging
        System.out.println("Extracted Value: " + extractedValue);

        // Step 3: Use the extracted value as input for the second API request
        Response secondApiResponse = RestAssured
                .given()
                .baseUri("https://api.example.com")
                .header("Content-Type", "application/json")
                .body("{ \"inputId\": \"" + extractedValue + "\" }") // Use extracted value
                .when()
                .post("/secondApiEndpoint")
                .then()
                .statusCode(200) // Validate status code using RestAssured
                .extract()
                .response();

        // Assert that the second API response body is not null
        assertNotNull(secondApiResponse.getBody(), "Second API response body should not be null");

        // Assert that the second API returned the expected value
        JsonPath secondApiJsonPath = secondApiResponse.jsonPath();
        String expectedResponseValue = "expectedValue"; // Replace with the actual expected value
        String actualResponseValue = secondApiJsonPath.getString("data.result"); // Adjust path as needed
        assertEquals(expectedResponseValue, actualResponseValue, "Second API response value should match expected value");

        // Print the second API response
        System.out.println("Second API Response: " + secondApiResponse.asString());
    }
}

