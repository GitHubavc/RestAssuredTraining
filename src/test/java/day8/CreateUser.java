package day8;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void test_CreateUser(ITestContext context) {
		
		Faker  faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "cedd86ff596a272a45db2a3d91c1c0526b6ff5b50c5d928f38d177c19d791063";
		
		int id=given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		System.out.println("Generated ID is : " +id);
		
		//context.setAttribute("user_id", id);
		context.getSuite().setAttribute("user_id", id);
		
	}
	
}
