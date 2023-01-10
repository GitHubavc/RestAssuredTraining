package day2;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

/*
 * Different ways to create Post request Body
 * 1) Using Hashmap
 * 2) Using Org.json
 * 3) POJO class
 * 4) Using external JSON data 
 * */

public class DiffrentWaysToCreatePostRequestBody {
	
	//  * 1) Using Hashmap
	
	//@Test(priority=1)
	void testPostusingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
		
		}
	
	
	// * 2) Using Org.json
	
	//@Test(priority=1)
	void testPostusingOrgjson() {
		
	
		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String coursesArr[]= {"C","C++"};
		data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
		
		}
	
	//@Test(priority=1)
	void testPostusingPOJOClass() {
		
	
		Pojo_Postrequest data = new Pojo_Postrequest ();
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		
		String courseArr[]= {"C","C++"};
		data.setCourses(courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
		
		}
	
	
	
	@Test(priority=1)
	void testPostusingExternalJsonfile() throws FileNotFoundException {
		
		File f= new File("/Users/amit/eclipse-workspace/RestAssuredTraining/body.json");
		
		FileReader fr= new FileReader(f);
		
		JSONTokener jt =new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
	
	
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
		
		}
	
	//deleting student record
	@Test(priority=2)
	void testDelete()
	{
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
		
	}
	

}
