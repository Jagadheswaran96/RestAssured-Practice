package restAssured;

import org.json.JSONObject;

public class ValidateNestedJSON {
    public static void main(String[] args) {
        String jsonResponse = "{ \"user\": { \"id\": 1, \"name\": \"John\", \"address\": { \"city\": \"New York\", \"zip\": \"10001\" } } }";

        // Parse the JSON response
        JSONObject jsonObject = new JSONObject(jsonResponse); 

        // Validate nested JSON
        JSONObject user = jsonObject.getJSONObject("user");
        if (user.getInt("id") == 1 && user.getString("name").equals("John")) {
            JSONObject address = user.getJSONObject("address");
            if (address.getString("city").equals("New York") && address.getString("zip").equals("10001")) {
                System.out.println("JSON Validation Passed");
            } else {
                System.out.println("Address Validation Failed");
            }
        } else {
            System.out.println("User Validation Failed");
        }
    }
}

