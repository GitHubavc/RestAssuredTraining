package day8;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void test_UpdateUser(ITestContext context) {
		
		Faker  faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "cedd86ff596a272a45db2a3d91c1c0526b6ff5b50c5d928f38d177c19d791063";
		
		//int id=(Integer) context.getAttribute("user_id");
		int id = (Integer) context.getSuite().getAttribute("user_id"); 
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(200)
			.log().all();
		
	
	}

}
