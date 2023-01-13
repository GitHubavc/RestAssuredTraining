package day3;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.json.JSONTokener;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;


// https://reqres.in/api/users?page=2&id=5

public class PathAndqueryparams {
	
	@Test
	void testQueryAndPathParameters()
	{
		
		given()
			.pathParam("mypath", "users") //path param
			.queryParam("page", "2") //query param
			.queryParam("id", "5") //query param
		
		.when()
			.get("https://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all()	;
		
		
	}

}
