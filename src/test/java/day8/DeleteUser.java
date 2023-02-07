package day8;



import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void test_DeleteUser(ITestContext context) {
		
		String bearerToken = "cedd86ff596a272a45db2a3d91c1c0526b6ff5b50c5d928f38d177c19d791063";
		
		//int id=(Integer) context.getAttribute("user_id");
		int id = (Integer) context.getSuite().getAttribute("user_id"); 
		
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(204)
			.log().all();
		
		
		
	}
	
	

}
