package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.json.JSONObject;
import org.json.JSONTokener;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {

	//@Test(priority=1)
	void testCookies( ) {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC","ARSKqsK7JCj6cRAt0PshMJBkUxfmf1_5BO0dZY8HEIpQFaOdMo4gU107Fg")
			.log().all();
		
	}
	
	@Test(priority=2)
	void getCookiesInfo( ) {
		
		 Response res= given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		
		//String cookie_value=res.getCookie("AEC");
		//System.out.println("Value of cookie is ===> " +cookie_value);
		 
		 //get all cookies info
		 
		Map<String,String> cookie_values =res.getCookies();
		
	//	System.out.println(cookie_values.keySet());
		
		
		for(String k:cookie_values.keySet()) {
			
			String cookie_value =res.getCookie(k);
			System.out.println(k+ "              " +cookie_value);
		}
		 
	}
}
