package restAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenValidation {
	
	@Test
	public void token() {
		String baseUrl = "https://gorest.co.in/";
		String endpoint = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured
				.given()
				.baseUri(baseUrl)
				.header("Content-Type", "application/json")
				.get(endpoint);

		if (response.statusCode() == 200) {
			System.out.println("Response Body: " + response.asString());
			response.jsonPath().get("id");
		} else {
			System.out.println("Failed to fetch response: " + response.statusCode());
			System.out.println(response.asString());
		}
	}
}
