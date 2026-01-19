package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidateAPIResponse {
    public static void main(String[] args) {
        // Base URI of the API
        RestAssured.baseURI = "https://api.example.com";

        // Making a GET request
        Response response = RestAssured.get("/endpoint");

        // Verify HTTP status code
        if (response.getStatusCode() == 200) {
            System.out.println("Status Code: 200 OK");
        } else {
            System.out.println("Unexpected Status Code: " + response.getStatusCode());
        }

        // Verify response body content
        String responseBody = response.getBody().asString();
        if (responseBody.contains("expectedValue")) {
            System.out.println("Response Body Validation Passed");
        } else {
            System.out.println("Response Body Validation Failed");
        }

        // Verify headers
        String contentType = response.getHeader("Content-Type");
        if ("application/json".equals(contentType)) {
            System.out.println("Content-Type Validation Passed");
        } else {
            System.out.println("Unexpected Content-Type: " + contentType);
        }
    }
}
